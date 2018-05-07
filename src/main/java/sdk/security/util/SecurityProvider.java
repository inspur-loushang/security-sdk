package sdk.security.util;

import java.util.Map;

import sdk.security.service.ISecurityProvider;
import sdk.security.service.factory.SDKFactory;

public class SecurityProvider {

    private static ISecurityProvider securityProvider = SDKFactory.getSecurityProviderImpl();
	
    /**
	 * 获取安全中心的服务根地址
	 * 
	 * @return String 服务根URL
	 */
	public static String getSecurityContextUrl(){
	    return securityProvider.getSecurityContextUrl();
	}

	/**
	 * 获取注销url
	 * 
	 * @param backUrl
	 * @return
	 */
	public static String getLogoutUrl(String backUrl){
	    return securityProvider.getLogoutUrl(backUrl);
	}
	
	public static String getRealmInfo(){
		return securityProvider.getRealmInfo();
	}
	
	public static String getTenantRealm(){
		return securityProvider.getTenantRealm();
	}
	
	public static Map<String, String> getTenantAdminUser(){
		return securityProvider.getTenantAdminUser();
	}
	
	public static Map<String, String> getTenantAdminUser(String tenantRealm) {
		return securityProvider.getTenantAdminUser(tenantRealm);
	}
	
}
