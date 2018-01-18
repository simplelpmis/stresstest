package org.simple.invoke;

import org.simple.config.AppConfig;
import org.simple.invoke.api.ApiTestCase;
import org.simple.invoke.http.HttpTestCase;

/**
 * @Date 2018/1/8      @Author Simba
 * @Description:
 */
public class TestCaseFactory {
    public static TestCase newTestCase(AppConfig config) {
        if (config.getHttp() != null) {
            return new HttpTestCase(config.getHttp());
        } else if (config.getApi() != null) {
            return new ApiTestCase(config.getApi());
        }
        return new DefaultTestCase();
    }
}