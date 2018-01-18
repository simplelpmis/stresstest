package org.simple.invoke.api;

import org.simple.classhelper.ApiClassLoader;
import org.simple.config.AppConfig;
import org.simple.invoke.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @Date 2018/1/10      @Author Simba
 * @Description:
 */
public class ApiTestCase implements TestCase {

    private final static Logger logger = LoggerFactory.getLogger(ApiTestCase.class);
    private final static String API_CLASS_LOADER_NAME = "apiTestClassLoader";
    private static volatile ApiClassLoader apiClassLoader = null;

    private AppConfig.Api api;
    private TestApi testApi;

    public ApiTestCase(AppConfig.Api api) {
        this.api = api;
        try {
            Class clz = getApiClassLoader(api.getApiClassPath()).loadClass(api.getClassName());
            this.testApi = (TestApi) clz.newInstance();
        } catch (Exception e) {
            logger.error("create ApiTestCase occur an error with className={}, system exit! ", api.getClassName(), e);
            System.exit(1);
        }
    }

    private static ApiClassLoader getApiClassLoader(String path) {
        if (apiClassLoader == null) {
            synchronized (ApiTestCase.class) {
                if (apiClassLoader == null) {
                    apiClassLoader = new ApiClassLoader(API_CLASS_LOADER_NAME, path);
                    logger.info("apiClassLoader={}, urls={}", apiClassLoader, Arrays.toString(apiClassLoader.getURLs()));
                }
            }
        }
        return apiClassLoader;
    }

    @Override
    public boolean execute() {
        return testApi.execute(api.getArgs());
    }
}