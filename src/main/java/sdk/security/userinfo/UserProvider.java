package sdk.security.userinfo;

import java.util.Map;

import sdk.security.service.IUserProvider;
import sdk.security.util.SDKFactory;

public class UserProvider {
	private static IUserProvider userProvider = SDKFactory.getUserProviderImpl();

	/**
	 * 获取当前登录用户的详细信息
	 * 
	 * @return Map，key分别为：userName[用户名]，email[邮箱地址]，...
	 */
	public static Map<String, String> getUserDetails() {
		return userProvider.getUserDetails();
	}

}
