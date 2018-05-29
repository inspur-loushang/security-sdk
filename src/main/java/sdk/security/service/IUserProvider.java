package sdk.security.service;

import java.util.List;
import java.util.Map;

public interface IUserProvider {

    /**
     * 根据用户ID获取详细信息
     * 
     * @param userId[用户标识]
     * @return Map,key分别为：
     *          userId[用户ID]
     *          userName[用户名]
     *          email[邮箱]
     */
	public Map<String, String> getUserInfo(String userId);

	/**
	 * 查询用户列表
	 * 
	 * @param queryParams
	 *            可用于查询的参数，类型Map，key分别为：userId[用户ID] userName[用户名]
	 * @return
	 */
	public List queryUsers(Map queryParams);
}