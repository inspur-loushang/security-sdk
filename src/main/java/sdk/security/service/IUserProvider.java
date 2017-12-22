package sdk.security.service;

import java.util.Map;

public interface IUserProvider {

    /**
     * 根据用户ID获取详细信息
     * @param userId[用户标识]
     * @return Map,key分别为：
     *          userId[用户ID]
     *          userName[用户名]
     *          email[邮箱]
     */
	public Map<String, Object> getUserInfo(String userId);

}