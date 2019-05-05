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
	 * 获取当前登录用户Access Token
	 * 
	 * @return String，token信息
	 */
	public String getToken();
	
	/**
	 * 获取当前登录用户的ID Token
	 * 
	 * @return
	 */
	public String getIDToken();
	
	/**
	 * 获取当前登录用户userId-realmname
	 * @return
	 */
	public String getKrbPrincipalName();

	/**
	 * 获取当前登录用户的详细信息
	 * 
	 * @return Map，key分别为：userName[用户名]，email[邮箱地址]，...
	 */
	public Map<String, String> getLoginUserInfo();
	
	/**
	 * 设置自定义会话信息
	 * 
	 * @param key
	 * @param value
	 */
	public void setCustomSessionInfo(String key, Object value);
	
	/**
	 * 获取自定义会话信息
	 * 
	 * @param key
	 * @return
	 */
	public Object getCustomSessionInfo(String key);
}
