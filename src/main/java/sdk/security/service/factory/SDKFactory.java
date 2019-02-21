package sdk.security.service.factory;

import java.text.MessageFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sdk.security.service.IAuthenticationProvider;
import sdk.security.service.IAuthorizationProvider;
import sdk.security.service.IMenuProvider;
import sdk.security.service.ISecurityProvider;
import sdk.security.service.IUserProvider;
import sdk.security.util.PropertiesUtilEnhance;

public class SDKFactory {
	
	private static Log logger = LogFactory.getLog(SDKFactory.class);
	
	// 认证实现类
	private static IAuthenticationProvider authenticationProvider;
	// 授权实现类
	private static IAuthorizationProvider authorizationProvider;
	// 菜单实现类
    private static IMenuProvider menuProvider;
	// 用户实现类
	private static IUserProvider userProvider;
	
    private static ISecurityProvider securityProvider;
	
    // provider获取
	private static String authenticationProviderClass = PropertiesUtilEnhance.getValue("conf.properties",
			"securitysdk.provider.authentication", "sdk.security.service.impl.AuthenticationProviderImpl");
	
	private static String authorizationProviderClass = PropertiesUtilEnhance.getValue("conf.properties",
			"securitysdk.provider.authorization", "sdk.security.service.impl.AuthorizationProviderImpl");
	
	private static String userProviderClass = PropertiesUtilEnhance.getValue("conf.properties",
			"securitysdk.provider.user", "sdk.security.service.impl.UserProviderImpl");
	
	private static String menuProviderClass = PropertiesUtilEnhance.getValue("conf.properties",
			"securitysdk.provider.menu", "sdk.security.service.impl.MenuProviderImpl");
	
	private static String securityProviderClass = PropertiesUtilEnhance.getValue("conf.properties",
			"securitysdk.provider.security", "sdk.security.service.impl.SecurityProviderImpl");

	static {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Class<?> clazz = classLoader.loadClass(authenticationProviderClass);
			authenticationProvider = (IAuthenticationProvider) clazz.newInstance();
			if(logger.isDebugEnabled()) {
				logger.debug(MessageFormat.format("IAuthenticationProvider [{0}] instance success.",
						authenticationProviderClass));
			}
		} catch (Exception e) {
			logger.info(MessageFormat.format("IAuthenticationProvider [{0}] instance failure because of: {1}",
					authenticationProviderClass, e.getMessage()));
		}
		
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Class<?> clazz = classLoader.loadClass(authorizationProviderClass);
			authorizationProvider = (IAuthorizationProvider) clazz.newInstance();
			if(logger.isDebugEnabled()) {
				logger.debug(MessageFormat.format("IAuthorizationProvider [{0}] instance success.",
						authorizationProviderClass));
			}
		} catch (Exception e) {
			logger.info(MessageFormat.format("IAuthorizationProvider [{0}] instance failure because of: {1}",
					authorizationProviderClass, e.getMessage()));
		}

		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Class<?> clazz = classLoader.loadClass(userProviderClass);
			userProvider = (IUserProvider) clazz.newInstance();
			if(logger.isDebugEnabled()) {
				logger.debug(MessageFormat.format("IUserProvider [{0}] instance success.",
						userProviderClass));
			}
		} catch (Exception e) {
			logger.info(MessageFormat.format("IUserProvider [{0}] instance failure because of: {1}",
					userProviderClass, e.getMessage()));
		}

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Class<?> clazz = classLoader.loadClass(menuProviderClass);
            menuProvider = (IMenuProvider) clazz.newInstance();
            if(logger.isDebugEnabled()) {
				logger.debug(MessageFormat.format("IMenuProvider [{0}] instance success.",
						menuProviderClass));
			}
        } catch (Exception e) {
        	logger.info(MessageFormat.format("IMenuProvider [{0}] instance failure because of: {1}",
        			menuProviderClass, e.getMessage()));
        }

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Class<?> clazz = classLoader.loadClass(securityProviderClass);
            securityProvider = (ISecurityProvider) clazz.newInstance();
            if(logger.isDebugEnabled()) {
				logger.debug(MessageFormat.format("ISecurityProvider [{0}] instance success.",
						securityProviderClass));
			}
        } catch (Exception e) {
        	logger.info(MessageFormat.format("ISecurityProvider [{0}] instance failure because of: {1}",
        			securityProviderClass, e.getMessage()));
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

	/**
     * 获得菜单实现类
     * 
     * @return
     */
    public static IMenuProvider getMenuProviderImpl() {
        return menuProvider;
    }
    
    /**
     * 获得认证中心实现类
     * 
     * @return
     */
    public static ISecurityProvider getSecurityProviderImpl() {
        return securityProvider;
    }
}
