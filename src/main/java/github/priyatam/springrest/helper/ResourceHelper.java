package github.priyatam.springrest.helper;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

@Service
public class ResourceHelper {

    public static enum ProcessingStatus {
        PROCESSING, COMPLETED, ERROR
    }

	protected static final Map<String, String> RESOURCE_STATUS = Maps.newConcurrentMap();
	protected static final Map<String, String> RESOURCE_STATUS_ERRORS = Maps.newConcurrentMap();
	protected static final Map<String, Long> RESOURCE_LAST_MODIFIED = Maps.newConcurrentMap();

	final static Logger logger = LoggerFactory.getLogger(ResourceHelper.class);

	@Value("${wara.ws.virtualhost}")
	private String vhost;
	
	public String generateFutureKey() {
		String futureKey = UUID.randomUUID().toString();
		RESOURCE_STATUS.put(futureKey, ProcessingStatus.PROCESSING.toString());
		logger.debug("Generated futureKey: " + futureKey);
		return futureKey;
	}

	public ProcessingStatus createPermLocation(String requestMapping, String futureKey, String permKey) {
		if (RESOURCE_STATUS.get(futureKey) == ProcessingStatus.PROCESSING.toString()) {
			String location = buildLocationUrl(requestMapping, permKey);
			logger.debug("Creating Perm location and updating resource status cache: " + location);
			RESOURCE_STATUS.put(futureKey, location);
			
			// Update timestamp
			RESOURCE_LAST_MODIFIED.put(permKey, new Date().getTime());
			
			return ProcessingStatus.COMPLETED;
		}
		return ProcessingStatus.PROCESSING;
	}

	@Cacheable(value = "resource-status", key = "#futureKey")
	public String getPermLocation(String futureKey) {
		return RESOURCE_STATUS.get(futureKey);
	}

	public String createFutureLocation(String requestMapping, String futureKey) {
		return buildLocationUrl(requestMapping + "/future", futureKey);
	}

	public String parseKey(String location) {
		String[] parts = location.split("/");
		return parts[parts.length - 1];
	}

	@Cacheable(value = "resource-status", key = "#futureKey")		
	private String buildLocationUrl(String requestMapping, String uri) {
		logger.debug(String.format("uri [%s], requestMapping [%s]", uri, requestMapping));
		String location = vhost + requestMapping + "/" + uri;
		logger.debug("Built LocationUri: " + location);
		return location;
	}

	public void createError(String futureKey, Exception e) {
		RESOURCE_STATUS.put(futureKey, ProcessingStatus.ERROR.toString());
		RESOURCE_STATUS_ERRORS.put(futureKey, e.getMessage());
	}

	public String getError(String futureKey) {
		return RESOURCE_STATUS_ERRORS.get(futureKey);
	}

	public long lastModified(String permKey) {
		return RESOURCE_LAST_MODIFIED.get(permKey);
	}

}