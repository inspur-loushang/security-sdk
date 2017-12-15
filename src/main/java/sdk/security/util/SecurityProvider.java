package sdk.security.util;

public class SecurityProvider {

	/**
	 * 获取当前应用所属的域信息
	 * 
	 * @return String realm[域名称]
	 */
	public static String getRealmInfo() {

		return KeycloakUtil.getRealm();
	}

	/**
	 * 获取安全中心的服务根地址
	 * 
	 * @return String 服务根URL
	 */
	public static String getSecurityContextUrl() {

		return KeycloakUtil.getSecurityContextUrl();
	}

	/**
	 * 获取注销url
	 * 
	 * @param redirectUrl
	 * @return
	 */
	public static String getLogoutUrl(String redirectUrl) {
		
		return KeycloakUtil.getLogoutUrl(redirectUrl);
	}
}
