package sdk.security.menu;

import java.util.List;

import sdk.security.menu.data.Menu;

public class MenuProvider {

	private static MenuResourceFactory factory = new MenuResourceFactory("securitySDKMenu.json");

	/**
	 * 获取当前登录用户有权限的菜单
	 * 
	 * @param parentId[父级菜单id,可以为空]
	 * @return
	 */
	public static List<Menu> getAuthzMenu(String parentId) {
		return factory.getAuthzMenu(parentId);
	}

	/**
	 * 获取菜单
	 * 
	 * @param parentId[父级菜单id,可以为空]
	 * @return
	 */
	public static List<Menu> getMenu(String parentId) {
		return factory.getMenu(parentId);
	}
}
