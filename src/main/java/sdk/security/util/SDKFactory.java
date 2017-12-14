package sdk.security.util;

import sdk.security.service.IAuthenticationProvider;
import sdk.security.service.IAuthorizationProvider;
import sdk.security.service.IUserProvider;

public class SDKFactory {
	// 认证实现类
	private static IAuthenticationProvider authenticationProvider;
	// 授权实现类
	private static IAuthorizationProvider authorizationProvider;
	// 用户实现类
	private static IUserProvider userProvider;

	static {
		try {
			String authenticationProviderClass = PropertyUtil.getProperty("securitySDK.AuthenticationProviderImpl");
			if (authenticationProviderClass == null || "".equals(authenticationProviderClass))
				authenticationProviderClass = "sdk.security.service.impl.AuthenticationProviderImpl";
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Class<?> clazz = classLoader.loadClass(authenticationProviderClass);
			authenticationProvider = (IAuthenticationProvider) clazz.newInstance();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	static {
		try {
			String authorizationProviderClass = PropertyUtil.getProperty("securitySDK.AuthorizationProviderImpl");
			if (authorizationProviderClass == null || "".equals(authorizationProviderClass))
				authorizationProviderClass = "sdk.security.service.impl.AuthorizationProviderImpl";
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Class<?> clazz = classLoader.loadClass(authorizationProviderClass);
			authorizationProvider = (IAuthorizationProvider) clazz.newInstance();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	static {
		try {
			String userProviderClass = PropertyUtil.getProperty("securitySDK.UserProviderImpl");
			if (userProviderClass == null || "".equals(userProviderClass))
				userProviderClass = "sdk.security.service.impl.UserProviderImpl";
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Class<?> clazz = classLoader.loadClass(userProviderClass);
			userProvider = (IUserProvider) clazz.newInstance();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得认证实现类
	 * 
	 * @return
	 */
	public static IAuthenticationProvider getAuthenticationProviderImpl() {
		return authenticationProvider;
	}

	/**
	 * 获得授权实现类
	 * 
	 * @return
	 */
	public static IAuthorizationProvider getAuthorizationProviderImpl() {
		return authorizationProvider;
	}

	/**
	 * 获得用户实现类
	 * 
	 * @return
	 */
	public static IUserProvider getUserProviderImpl() {
		return userProvider;
	}

}
