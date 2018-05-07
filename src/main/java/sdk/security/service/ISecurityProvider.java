package sdk.security.service;

import java.util.Map;

public interface ISecurityProvider {

    /**
     * 获取安全中心的服务根地址
     * 
     * @return String 服务根URL
     */
    public String getSecurityContextUrl();

    /**
     * 获取注销url
     * 
     * @param redirectUrl
     * @return
     */
    public String getLogoutUrl(String redirectUrl);
    
    /**
     * 获取当前应用所属的Realm
     * 
     * @return
     */
    public String getRealmInfo();

    /**
     * 获取当前租户对应的Realm
     * 
     * @return
     */
    public String getTenantRealm();
    
    /**
     * 获取当前租户的管理员信息
     * 
     * @return
     */
    public Map<String, String> getTenantAdminUser();
    
    /**
     * 获取指定租户的管理员信息
     * 
     * @param tenantRealm 租户的Realm
     * @return
     */
    public Map<String, String> getTenantAdminUser(String tenantRealm);
}