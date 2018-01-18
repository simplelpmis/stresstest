package org.simple.config;

import org.ho.yaml.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @Date 2018/1/8      @Author Simba
 * @Description:
 */
public class AppConfigUtil {

    private final static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    private AppConfigUtil() {
    }

    public static AppConfig getAppConfig() {
        return AppConfigHolder.appConfig;
    }

    private static class AppConfigHolder {
        public static AppConfig appConfig = loadAppConfig();

        private static AppConfig loadAppConfig() {
            String configPath = System.getProperty("config");
            AppConfig appConfig = null;
            try {
                if (configPath == null || "".equals(configPath)) {
                    appConfig = Yaml.loadType(AppConfig.class.getResourceAsStream("/config.yml"), AppConfig.class);
                } else {
                    appConfig = Yaml.loadType(new File(configPath), AppConfig.class);
                }

                logger.info("successfully read configPath={}, config={}", configPath, appConfig);
                return appConfig;
            } catch (Exception e) {
                logger.error("AppConfigUtil occur an error with", e);
                return null;
            }
        }
    }
}