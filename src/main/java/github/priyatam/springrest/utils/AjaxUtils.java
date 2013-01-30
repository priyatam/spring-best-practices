package github.priyatam.springrest.utils;

import org.springframework.web.context.request.WebRequest;

/**
 * From:
 * https://github.com/SpringSource/spring-mvc-showcase
 */
public class AjaxUtils {

	public static boolean isAjaxRequest(WebRequest webRequest) {
		String requestedWith = webRequest.getHeader("X-Requested-With");
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	public static boolean isAjaxUploadRequest(WebRequest webRequest) {
		return webRequest.getParameter("ajaxUpload") != null;
	}

	private AjaxUtils() {}

}