package sdk.security.service;


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

}