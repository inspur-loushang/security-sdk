package sdk.security.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sdk.security.rest.RestRequestProvider;
import sdk.security.service.IUserProvider;
import sdk.security.util.KeycloakUtil;

/**
 * 
 * Keycloak 实现类
 */
public class UserProviderImpl implements IUserProvider {

    /**
     * 根据用户ID获取详细信息
     * @param userId[用户标识]
     * @return Map,key分别为：
     *          userId[用户ID]
     *          userName[用户名]
     *          email[邮箱]
     */
    public Map<String, Object> getUserInfo(String userId) {
        String realm = KeycloakUtil.getRealm();
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", userId);
        // KeyCloak默认按照用户名模糊查找，可能结果是多个，根据入参userName过滤
        Map<String, Object> result = null;
        List<Map> users = queryUsers(params, realm);
        if (users != null && users.size() > 0) {
            for (Map user : users) {
                if (user.get("username").equals(userId)) {
                    result = user;
                    break;
                }
            }
        }
        if(result!=null){
            // 组装为通用的信息
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("userId", result.get("username"));
            returnMap.put("userName", result.get("firstName"));
            returnMap.put("email", result.get("email"));
            return returnMap;
        }
        return null;
    }
    
    /**
     * 查询用户
     * 
     * @param queryParams
     *              username 类型List<String> 模糊查找
     * @param tenantRealm
     * @param request
     * @return
     */
    public List queryUsers(Map<String, String> queryParams, String realm) {
        Map<String, String> uriVariables = null;
        if(realm != null && !"".equals(realm)){
            uriVariables = new HashMap<String, String>();
            uriVariables.put("realm", realm);
        }
        String queryApi = KeycloakUtil.getSecurityContextUrl()+"/admin/realms/{realm}/users";
        List list = RestRequestProvider.get(queryApi, List.class, uriVariables, queryParams);
        return list;
    }

}
