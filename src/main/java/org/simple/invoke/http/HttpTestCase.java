package org.simple.invoke.http;

import org.simple.config.AppConfig;
import org.simple.invoke.TestCase;

/**
 * @Date 2018/1/9      @Author Simba
 * @Description:
 */
public class HttpTestCase implements TestCase {

    private boolean isPostMethod;
    private String url;
    private String params;
    private String successResult;
    private boolean isContain;

    public HttpTestCase(AppConfig.Http http) {
        if (HttpHelper.POST_METHOD.equalsIgnoreCase(http.getMethod())) {
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
            result = HttpHelper.doPost(url, params);
        } else {
            result = HttpHelper.doGet(url);
        }
//        System.out.println(result);
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