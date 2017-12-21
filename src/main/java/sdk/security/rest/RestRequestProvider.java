package sdk.security.rest;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class RestRequestProvider {

	private static Properties properties = new Properties();
	private static Log logger = LogFactory.getLog(RestRequestProvider.class);
	private static RestTemplate restTemplate = new RestTemplate();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T get(String url, Class<T> responseType, Map uriVariables, Map queryVariables,
			HttpServletRequest request) {

		if (uriVariables == null) {
			uriVariables = new HashMap();
		}

		String endpoint = buildUrl(url, request);

		HttpEntity<Map> entity = new HttpEntity<Map>(buildAuthorizationHeader(request, queryVariables));

		if (queryVariables != null && !queryVariables.isEmpty()) {
			MultiValueMap map = new LinkedMultiValueMap();
			map.putAll(queryVariables);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint);
			builder.queryParams(map);
			endpoint = builder.build().toUriString();
		}

		HttpEntity<T> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, responseType, uriVariables);

		return response.getBody();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T post(String url, Class<T> responseType, Map uriVariables, Map bodyVariables,
			HttpServletRequest request) {

		if (uriVariables == null) {
			uriVariables = new HashMap();
		}

		String endpoint = buildUrl(url, request);
		HttpEntity<Map> entity = new HttpEntity<Map>(bodyVariables, buildAuthorizationHeader(request, bodyVariables));

		HttpEntity<T> response = restTemplate.exchange(endpoint, HttpMethod.POST, entity, responseType, uriVariables);

		return response.getBody();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T put(String url, Class<T> responseType, Map uriVariables, Map bodyVariables,
			HttpServletRequest request) {

		if (uriVariables == null) {
			uriVariables = new HashMap();
		}

		String endpoint = buildUrl(url, request);
		HttpEntity<Map> entity = new HttpEntity<Map>(bodyVariables, buildAuthorizationHeader(request, bodyVariables));

		HttpEntity<T> response = restTemplate.exchange(endpoint, HttpMethod.PUT, entity, responseType, uriVariables);

		return response.getBody();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T delete(String url, Class<T> responseType, Map uriVariables, Map bodyVariables,
			HttpServletRequest request) {

		if (uriVariables == null) {
			uriVariables = new HashMap();
		}

		String endpoint = buildUrl(url, request);
		HttpEntity<Map> entity = new HttpEntity<Map>(bodyVariables, buildAuthorizationHeader(request, bodyVariables));

		HttpEntity<T> response = restTemplate.exchange(endpoint, HttpMethod.DELETE, entity, responseType, uriVariables);

		return response.getBody();
	}

	private static String buildUrl(String url, HttpServletRequest request) {
		if (url.startsWith("http") || url.startsWith("https")) {
			return url;
		} else {
			try {
				ClassLoader ccl = Thread.currentThread().getContextClassLoader();
				InputStream inputInner = ccl.getResourceAsStream("conf.properties");
				properties.load(inputInner);
			} catch (Exception e) {
				String cururlstr = "";
				try {
					URL cururl = new URL(request.getRequestURL().toString());
					String protocol = cururl.getProtocol();
					String host = cururl.getHost();
					int port = cururl.getPort();
					cururlstr += protocol + "://" + host;
					if (port > 0) {
						cururlstr += ":" + port;
					}
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				properties.put("domain", cururlstr);

			}
			if (url.startsWith("/")) {
				url = properties.getProperty("domain") + url;
			} else {
				url = properties.getProperty("domain") + "/" + url;
			}
			return url;
		}
	}

	/**
	 * 构建HTTP Header Authorization
	 * 
	 * @param httpServletRequest
	 * @return HttpHeaders
	 */
	@SuppressWarnings("rawtypes")
	private static HttpHeaders buildAuthorizationHeader(HttpServletRequest httpServletRequest, Map bodyVariables) {
		RefreshableKeycloakSecurityContext context = (RefreshableKeycloakSecurityContext) httpServletRequest
				.getAttribute(KeycloakSecurityContext.class.getName());

		if (context == null) {
			if (logger.isErrorEnabled()) {
				logger.error("RestRequestSecurelyUtils context is null.");
			}
		}
		String accessToken = context.getTokenString();

		if (bodyVariables != null && !bodyVariables.isEmpty() && bodyVariables.containsKey("accessToken")) {
			if (bodyVariables.get("accessToken") != null && !"".equals(bodyVariables.get("accessToken"))) {
				accessToken = bodyVariables.get("accessToken").toString();
			}
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "bearer " + accessToken);

		return headers;
	}

}
