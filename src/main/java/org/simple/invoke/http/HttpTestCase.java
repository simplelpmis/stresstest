package org.simple.invoke.http;

import org.simple.config.AppConfig;
import org.simple.invoke.TestCase;

/**
 * @Date 2018/1/9      @Author Simba
 * @Description:
 */
public class HttpTestCase implements TestCase {

    private static final String POST_METHOD = "post";

    private boolean isPostMethod;
    private String url;
    private String params;
    private String successResult;
    private boolean isContain;

    public HttpTestCase(AppConfig.Http http) {
        if (POST_METHOD.equalsIgnoreCase(http.getMethod())) {
            this.isPostMethod = true;
        }
        this.url = http.getUrl();
        this.params = http.getArgs();
        this.successResult = http.getSuccessResult();
        this.isContain = http.isContain();
    }

    @Override
    public boolean execute() {
        String result;
        if (isPostMethod) {
            result = OkHttpUtil.postJson(url, params);
        } else {
            result = OkHttpUtil.get(url);
        }
        if (!isContain) {
            if (successResult.equals(result)) {
                return true;
            }
            return false;
        }
        if (result.contains(successResult)) {
            return true;
        }
        return false;
    }
}