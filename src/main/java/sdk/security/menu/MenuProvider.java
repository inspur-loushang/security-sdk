package sdk.security.menu;

import sdk.security.service.IMenuProvider;
import sdk.security.service.factory.SDKFactory;

public class MenuProvider {

    private static IMenuProvider menuProvider = SDKFactory.getMenuProviderImpl();

	/**
	 * 获取当前登录用户有权限的菜单
	 * 
	 * @param parentId[父级菜单id,可以为空]
	 * @return json
	 */
	public static String getAuthzMenu(String parentId) {
		return menuProvider.getAuthzMenu(parentId);
	}

	/**
	 * 获取菜单
	 * 
	 * @param parentId[父级菜单id,可以为空]
	 * @return
	 */
	public static String getMenu(String parentId) {
		return menuProvider.getMenu(parentId);
	}
}
