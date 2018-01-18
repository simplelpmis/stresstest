package org.simple.config;

import lombok.Data;

/**
 * @Date 2018/1/8      @Author Simba
 * @Description: 应用配置类
 */
@Data
public class AppConfig {

    private int threadCount;
    private int requestCount;
    private Api api;
    private Http http;

    @Data
    public static class Api {
        private String apiClassPath;
        private String className;
        private String args;
    }

    @Data
    public static class Http {
        private String url;
        private String method;
        private String args;
        private String successResult;
        private boolean contain;
    }
}