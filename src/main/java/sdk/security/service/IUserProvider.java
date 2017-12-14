package sdk.security.service;

import java.util.Map;

public interface IUserProvider {
	/**
	 * 获取当前登录用户的详细信息
	 * 
	 * @return Map，key分别为：userName[用户名]，email[邮箱地址]，...
	 */
	public Map<String, String> getUserDetails();
}
