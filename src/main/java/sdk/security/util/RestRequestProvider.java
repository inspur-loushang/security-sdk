package sdk.security.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sdk.security.authc.AuthenticationProvider;

public class RestRequestProvider {

    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * GET 请求
     * @param endpoint[请求URL，支持占位符]
     * @param responseType[返回值类型]
     * @param uriVariables[URI中占位符变量，Map<String,String>类型]
     * @param queryVariables[请求参数，Map]
     * @return
     */
    public static <T> T get(String endpoint, Class<T> responseType, Map<String, String> uriVariables,
            Map<String, Object> queryVariables) {

        if (uriVariables == null) {
            uriVariables = new HashMap<String, String>();
        }

        HttpEntity<Map> entity = new HttpEntity<Map>(buildAuthorizationHeader(queryVariables));

        if (queryVariables != null && !queryVariables.isEmpty()) {
            MultiValueMap map = new LinkedMultiValueMap();
            for (String key : queryVariables.keySet()) {
                map.add(key, queryVariables.get(key));
            }
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint);
            builder.queryParams(map);
            endpoint = builder.build().toUriString();
        }

        HttpEntity<T> response =
                restTemplate.exchange(endpoint, HttpMethod.GET, entity, responseType, uriVariables);

        return response.getBody();
    }
    
    /**
     * POST 请求
     *      若请求URL需要参数，则需要手工拼接参数
     * @param endpoint[请求URL，支持占位符]
     * @param responseType[返回值类型]
     * @param uriVariables[URI中占位符变量，Map<String,String>类型]
     * @param bodyVariables[BODY内容，Map<String,Object>类型]
     * @return
     */
    public static <T> T post(String endpoint, Class<T> responseType, Map<String, String> uriVariables,
            Map<String, Object> bodyVariables) {

        if (uriVariables == null) {
            uriVariables = new HashMap<String, String>();
        }

        HttpEntity<Map> entity =
                new HttpEntity<Map>(bodyVariables, buildAuthorizationHeader(bodyVariables));

        HttpEntity<T> response = restTemplate
                        .exchange(endpoint, HttpMethod.POST, entity, responseType, uriVariables);

        return response.getBody();
    }

    /**
     * PUT 请求
     * @param endpoint[请求URL，支持占位符]
     * @param responseType[返回值类型]
     * @param uriVariables[URI中占位符变量，Map<String,String>类型]
     * @param bodyVariables[BODY内容，Map<String,Object>类型]
     * @return
     */
    public static <T> T put(String endpoint, Class<T> responseType, Map<String, String> uriVariables,
            Map<String, Object> bodyVariables) {

        if (uriVariables == null) {
            uriVariables = new HashMap<String, String>();
        }

        HttpEntity<Map> entity =
                new HttpEntity<Map>(bodyVariables, buildAuthorizationHeader(bodyVariables));

        HttpEntity<T> response =
                restTemplate.exchange(endpoint, HttpMethod.PUT, entity, responseType, uriVariables);

        return response.getBody();
    }

    /**
     * DELETE 请求
     * @param endpoint[请求URL，支持占位符]
     * @param responseType[返回值类型]
     * @param uriVariables[URI中占位符变量，Map<String,String>类型]
     * @param bodyVariables[BODY内容，Map<String,Object>类型]
     * @return
     */
    public static <T> T delete(String endpoint, Class<T> responseType, Map<String, String> uriVariables,
            Map<String, Object> bodyVariables) {

        if (uriVariables == null) {
            uriVariables = new HashMap<String, String>();
        }

        HttpEntity<Map> entity =
                new HttpEntity<Map>(bodyVariables, buildAuthorizationHeader(bodyVariables));

        HttpEntity<T> response =
                restTemplate.exchange(endpoint, HttpMethod.DELETE, entity, responseType,
                        uriVariables);

        return response.getBody();
    }

    /**
     * 构建HTTP Header Authorization
     * 
     * @return HttpHeaders
     */
    private static HttpHeaders buildAuthorizationHeader(Map<String, Object> bodyVariables) {
    	
    	String accessToken = AuthenticationProvider.getToken();

		if (bodyVariables != null && !bodyVariables.isEmpty() && bodyVariables.containsKey("accessToken")) {
			if (bodyVariables.get("accessToken") != null && !"".equals(bodyVariables.get("accessToken"))) {
				accessToken = bodyVariables.get("accessToken").toString();
			}else{
				bodyVariables.remove("accessToken");
			}
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "bearer " + accessToken);

		return headers;
    }
    
}