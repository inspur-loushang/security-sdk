package sdk.security.service;

public interface IMenuProvider {

	/**
	 * 获取当前登录用户有权限的菜单
	 * 
	 * @param parentId[父级菜单id,可以为空]
	 * @return JSON
	 */
	public String getAuthzMenu(String parentId);

	/**
	 * 获取菜单
	 * 
	 * @param parentId[父级菜单id,可以为空]
	 * @return JSON
	 */
	public String getMenu(String parentId);
	
}
