package sdk.security.util;

import java.io.InputStream;
import java.util.Properties;


public class PropertyUtil {
	
    private static Properties properties = new Properties();

    static {
        try {
        	ClassLoader ccl = Thread.currentThread().getContextClassLoader();
        	InputStream inputInner = ccl.getResourceAsStream("securitySDK.properties");
            properties.load(inputInner);
        } catch (Exception e) {
        	properties.put("securitySDK.AuthenticationProviderImpl", "sdk.security.service.impl.AuthenticationProviderImpl");
        	properties.put("securitySDK.AuthorizationProviderImpl", "sdk.security.service.impl.AuthorizationProviderImpl");
        	properties.put("securitySDK.UserProviderImpl", "sdk.security.service.impl.UserProviderImpl");
        }
    }

    /**
     * 获得指定属性的属性值
     * 
     * @param key 属性key
     * @return 属性值
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
