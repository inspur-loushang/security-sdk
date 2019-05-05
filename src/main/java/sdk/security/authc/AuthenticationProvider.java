package sdk.security.authc;

import java.util.Map;

import sdk.security.service.IAuthenticationProvider;
import sdk.security.service.factory.SDKFactory;

/**
 * 认证
 *
 */
public class AuthenticationProvider {

	private static IAuthenticationProvider authenticationProvider = SDKFactory.getAuthenticationProviderImpl();

	/**
	 * 获取当前登录用户标识
	 * 
	 * @return String userId[用户ID]
	 */
	public static String getLoginUserId() {
		return authenticationProvider.getLoginUserId();
	}

	/**
	 * 获取当前登录用户Token
	 * 
	 * @return String，token信息
	 */
	public static String getToken() {
		return authenticationProvider.getToken();
	}
	
	public static String getIDToken() {
		return authenticationProvider.getIDToken();
	}

	/**
	 * 获取当前登录用户userId-realmname
	 * 
	 * @return
	 */
	public static String getKrbPrincipalName() {
		return authenticationProvider.getKrbPrincipalName();
	}

	/**
	 * 获取当前登录用户的详细信息
	 * 
	 * @return Map，key分别为：userName[用户名]，email[邮箱地址]，...
	 */
	public static Map<String, String> getLoginUserInfo() {
		return authenticationProvider.getLoginUserInfo();
	}
	
	/**
	 * 设置自定义会话信息
	 * 
	 * @param key
	 * @param value
	 */
	public static void setCustomSessionInfo(String key, Object value) {
		authenticationProvider.setCustomSessionInfo(key, value);
	}
	
	/**
	 * 获取自定义会话信息
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCustomSessionInfo(String key) {
		return authenticationProvider.getCustomSessionInfo(key);
	}

}
