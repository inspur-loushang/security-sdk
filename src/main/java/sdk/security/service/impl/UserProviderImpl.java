package sdk.security.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.keycloak.representations.AccessToken;

import sdk.security.service.IUserProvider;
import sdk.security.util.KeycloakUtil;

public class UserProviderImpl implements IUserProvider {

	/**
	 * 获取当前登录用户的详细信息
	 * 
	 * @return Map，key分别为：userId[用户标识]，userName[用户名]，email[邮箱地址]，...
	 */
	public Map<String, String> getUserDetails() {
		AccessToken token = KeycloakUtil.getAccessToken();
		if (token != null) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userId", token.getPreferredUsername());
			map.put("userName", token.getPreferredUsername());
			map.put("email", token.getEmail());
			return map;
		} else {
			return null;
		}
	}
}
