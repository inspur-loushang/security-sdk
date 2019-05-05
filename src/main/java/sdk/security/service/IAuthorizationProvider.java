package sdk.security.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IAuthorizationProvider {
	/**
	 * 判断当前登录用户对指定资源是否有权限
	 * 
	 * @param resourceId
	 *            [资源标识]
	 *
	 * @return true[有权限]，false[无权限]
	 */
	public boolean hasPermission(String resourceId);

	/**
	 * 获取当前登录用户有权限的资源信息
	 * 
	 * @param type
	 *            [资源类型，可为空]
	 *
	 * @return List，内容为Map，key分别为：rid[资源ID]，url[资源url]，type[资源类型]，.
	 *         ..
	 */
	public List<Map<String, String>> getResources(String type);

	public List<Map> getPermissions();
	
	public Set<String> getPermissionResourceNames();
}
