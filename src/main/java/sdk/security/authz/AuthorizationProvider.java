package sdk.security.authz;

import java.util.List;
import java.util.Map;

import sdk.security.service.IAuthorizationProvider;
import sdk.security.util.SDKFactory;

/**
 * 授权
 *
 */
public class AuthorizationProvider {
	private static IAuthorizationProvider authorizationProvider = SDKFactory.getAuthorizationProviderImpl();

	/**
	 * 判断当前登录用户对指定资源是否有权限
	 * 
	 * @param resourceId
	 *            [资源标识]
	 *
	 * @return true[有权限]，false[无权限]
	 */

	public static boolean hasPermission(String resourceId) {
		return authorizationProvider.hasPermission(resourceId);
	}

	/**
	 * 获取当前登录用户有权限的资源信息
	 * 
	 * @param type
	 *            [资源类型，可为空]
	 *
	 * @return List，内容为Map，key分别为：rid[资源ID]，url[资源url]，type[资源类型]，.
	 *         ..
	 */
	public static List<Map<String, String>> getResources(String type) {
		return authorizationProvider.getResources(type);
	}
}
