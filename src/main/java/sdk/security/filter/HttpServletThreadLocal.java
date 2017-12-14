package sdk.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletThreadLocal {

	private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();

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

	public static void setRequest(HttpServletResponse servletResponse) {
		currentResponse.set(servletResponse);
	}

	public static void clearResponse() {
		currentResponse.set(null);
	}

}
