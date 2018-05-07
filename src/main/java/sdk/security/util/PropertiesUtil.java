package sdk.security.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class PropertiesUtil {

    private static Log logger = LogFactory.getLog(PropertiesUtil.class);

    private static Properties properties = new Properties();

    public static Properties getInstance(String location) {
    	InputStream in = null;
        String temLocation=location;
        try {
        	
            if (temLocation.startsWith("/")) {
                temLocation = temLocation.substring(1);
            }
            // 每次都从磁盘加载文件
            String path = PropertiesUtil.class.getClassLoader().getResource(location).getPath();
            in = new FileInputStream(path);
            properties.load(in);
            in.close();
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
            throw new RuntimeException("can't find the " + temLocation);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        }
        return properties;
    }

    public static String getValue(String filename, String key) {
        String value = getInstance(filename).getProperty(key);
        
        // 如果获取到key，但key值为空字符串，则重新加载配置文件
    	if (properties.isEmpty() || "".equals(value)) {
    		getInstance(filename);
        	value = properties.getProperty(key);
		}
        // 如果获取不到key，则读取环境变量
        if (StringUtil.isEmptyString(value)) {
            /*
             * 将配置项的key中含有的英文小数点改为下划线，linux下环境变量名不支持包含小数点
             */
            String newKey = key.replace(".", "_").replace("-", "_");
            value = System.getenv(newKey);
        }
        return value;
    }

    public static String getValue(String filename, String key, String defaultValue) {
        String value = getValue(filename, key);
        if (StringUtil.isEmptyString(value)) {
            return defaultValue;
        }
        return value;
    }

}
