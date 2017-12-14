package sdk.security.service;

import java.util.Map;

public interface IAuthenticationProvider {
	/**
	 * 获取当前登录用户标识
	 * 
	 * @return String userId[用户ID]
	 */
	public String getLoginUserId();

	/**
	 * 获取当前登录用户信息
	 * 
	 * @return Map，key分别为：userId[用户ID]，userName[用户名]
	 */
	public Map<String, String> getLoginUserInfo();

	/**
	 * 获取当前登录用户Token
	 * 
	 * @return String，token信息
	 */
	public String getToken();
	
	/**
	 * 获取当前登录用户userId-realmname
	 * @return
	 */
	public String getKrbPrincipalName();

}
