package sdk.security.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletThreadLocal {

	private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();
	private static ThreadLocal<Map<String, Object>> securityContext = new ThreadLocal<Map<String, Object>>();

	public static HttpServletRequest getRequest() {
		return currentRequest.get();
	}

	public static void setRequest(HttpServletRequest servletRequest) {
		currentRequest.set(servletRequest);
	}

	public static void clearRequest() {
		currentRequest.set(null);
	}

	public static HttpServletResponse getResponse() {
		return currentResponse.get();
	}

	public static void setResponse(HttpServletResponse servletResponse) {
		currentResponse.set(servletResponse);
	}

	public static void clearResponse() {
		currentResponse.set(null);
	}
	
	public static Object getSecurityContext(String key) {
		Map<String, Object> context = securityContext.get();
		if(context == null) {
			return null;
		}
		return context.get(key);
	}
	
	public static void setSecurityContext(String key, Object value) {
		Map<String, Object> context = securityContext.get();
		if(context == null) {
			context = new HashMap<String, Object>();
		}
		context.put(key, value);
		securityContext.set(context);
	}
	
	public static void clearSecurityContext() {
		securityContext.set(null);
	}

}
