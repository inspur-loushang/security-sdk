package sdk.security.userinfo;

import java.util.Map;

import sdk.security.service.IUserProvider;
import sdk.security.service.factory.SDKFactory;

public class UserProvider {
    
	private static IUserProvider userProvider = SDKFactory.getUserProviderImpl();

	/**
     * 根据用户ID获取详细信息
     * @param userId[用户标识]
     * @return Map,key分别为：
     *          userId[用户ID]
     *          userName[用户名]
     *          email[邮箱]
     *          userType[用户类型]
     */
	public static Map<String, String> getUserInfo(String userId) {
		return userProvider.getUserInfo(userId);
	}
	
	public static Map<String, String> getUserInfo(String userId, String realm) {
		return userProvider.getUserInfo(userId, realm);
	}

}
