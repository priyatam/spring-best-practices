package github.priyatam.springrest.utils;

import github.priyatam.springrest.domain.BaseDomain;
import github.priyatam.springrest.exception.InvalidTagException;
import github.priyatam.springrest.helper.ETagHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class EtagGeneratorAspect {

	final static Logger logger = LoggerFactory.getLogger(EtagGeneratorAspect.class);

	@Autowired
	private ETagHelper eTagHelper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object handlePOST(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		Object retVal = pjp.proceed();

		ResponseEntity entity = (ResponseEntity) retVal;
		HttpHeaders headers = new HttpHeaders();
		BaseDomain resource = (BaseDomain) entity.getBody();
		if (resource == null) { // async operation?
			return retVal;
		}

		Object key = ReflectionUtils.findMethod(resource.getClass(), "getPolicy").invoke(resource);
		headers.add("Location",
				ServletUriComponentsBuilder.fromRequest(request).path("/{policyNum}").build().expand(key.toString())
						.toString());

		return new ResponseEntity(null, headers, entity.getStatusCode());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object handleGET(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String url1 = ServletUriComponentsBuilder.fromRequest(request).build().toString();

		// Check eTags
		String clientEtag = request.getHeader("If-None-Match");
		String latestEtag = null;
		try {
			latestEtag = eTagHelper.get(url1);
		} catch (InvalidTagException e) {
			// ignore
		}
		if (latestEtag != null && clientEtag != null && clientEtag.equals(latestEtag)) {
			return new ResponseEntity(null, new HttpHeaders(), HttpStatus.NOT_MODIFIED);
		}

		Object retVal = pjp.proceed();
		ResponseEntity entity = (ResponseEntity) retVal;
		HttpHeaders headers = new HttpHeaders();
		String url = ServletUriComponentsBuilder.fromRequest(request).build().toString();
		String tag = null;
		try {
			tag = eTagHelper.get(url);
		} catch (InvalidTagException e) {
			tag = eTagHelper.generate(url, entity.getBody());
		}
		headers.add("Etag", tag);
		return new ResponseEntity(entity.getBody(), headers, entity.getStatusCode());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object handlePUT(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		ResponseEntity responseEntity = (ResponseEntity) pjp.proceed();
		String url = ServletUriComponentsBuilder.fromRequest(request).build().toString();
		HttpHeaders headers = new HttpHeaders();
		BaseDomain domain = (BaseDomain) responseEntity.getBody();
		String newTag = eTagHelper.update(url, domain);
		headers.add("Etag", newTag);
		return new ResponseEntity(responseEntity.getBody(), headers, responseEntity.getStatusCode());
	}

	private boolean checkEtag() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String url = ServletUriComponentsBuilder.fromRequest(request).build().toString();
		String storedTag = null;
		try {
			storedTag = eTagHelper.get(url);
			String providedTag = request.getHeader("If-Match");
			if (providedTag == null || !storedTag.equals(providedTag)) {
				logger.debug("Etag didn't match ... " + providedTag);
				return false;
			} else {
				logger.debug("Etag matched ... " + providedTag);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	private Object handleDELETE(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String url = ServletUriComponentsBuilder.fromRequest(request).build().toString();
		pjp.proceed();
		eTagHelper.remove(url);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@Around("execution(public org.springframework.http.ResponseEntity github.priyatam.springrest.resource.*.*(..)) && @annotation(requestMapping)")
	@SuppressWarnings({ "rawtypes" })
	public Object handleMethod(ProceedingJoinPoint pjp, RequestMapping requestMapping) throws Throwable {
		logger.info("Handling Etag ...");

		Object retVal = null;
		switch (requestMapping.method()[0]) {
		case GET:
			retVal = handleGET(pjp);
			break;

		case POST:
			retVal = handlePOST(pjp);
			break;

		case PUT:
			if (checkEtag()) {
				retVal = handlePUT(pjp);
			} else {
				retVal = new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
			}
			break;

		case DELETE:
			if (checkEtag()) {
				retVal = handleDELETE(pjp);
			} else {
				retVal = new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
			}
			break;

		case HEAD:
			retVal = pjp.proceed();

		case OPTIONS:
			retVal = pjp.proceed();

		default:
			break;

		}
		return retVal;
	}

}
