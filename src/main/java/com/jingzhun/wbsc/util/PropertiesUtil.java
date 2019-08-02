package com.jingzhun.wbsc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 读取配置文件工具类
 *
 * @author hushuang
 */
public class PropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class.getName());

    /**
     * 根据key读取value
     * //     * @param filePath
     *
     * @param key
     * @return Properties prop=new Properties();
     * prop.load(new InputStreamReader(Client.class.getClassLoader().getResourceAsStream("config.properties"), "UTF-8"));
     */
    public static String readValue(String filePath1, String key) throws FileNotFoundException {
        String resourceLocation = "classpath:" + filePath1;
        File file = ResourceUtils.getFile(resourceLocation);
        String filePath = file.getAbsolutePath();
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
            if (in != null) {
                prop.load(in);
            }
//            字符串重构保证  不乱码
            String username = prop.getProperty(key);
            String resultName = new String(username.getBytes("ISO-8859-1"), "UTF-8");
            return resultName;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }


    /**
     * 获取配置文件
     *
     * @param filePath
     * @return
     */
    public static Map<String,String> getProperties(String filePath) {
        HashMap<String, String> map = new HashMap<>();
        Properties prop = new Properties();
        InputStream in = null;
        try {
            File file = new File(filePath);
//          直接读取文件
            if (file.canRead()) {
                in = new BufferedInputStream(new FileInputStream(file));
//          从当前路径中获取文件流
            } else {
                in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
            }
            if (in != null) {
                prop.load(in);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }

        Set<Map.Entry<Object, Object>> entrySet = prop.entrySet();
        for (Map.Entry<Object, Object> entry : entrySet) {
            map.put((String) entry.getKey(), (String) entry.getValue());
        }
        return map;
    }

}
