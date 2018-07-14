package org.simple.invoke.http;


import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Date 2018/5/25      @Author Simba
 * @Description:
 */
public final class OkHttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    private static OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");

    public static String get(String url, Map<String, String> params) {
        String query = params.entrySet().stream().map(e -> String.format("%s=%s", e.getKey(), e.getValue()))
                .reduce((s1, s2) -> s1 + "&" + s2).orElse("");
        String finalUrl = String.format("%s?%s", url, query);
        return get(finalUrl);
    }

    public static String get(String url) {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
//            logger.info("get with url={}, result={}", url, result);
            return result;
        } catch (Exception e) {
            logger.error("execute get with url={} occur an exception", url, e);
            return null;
        }
    }

    public static String postJson(String url, String json) {
        RequestBody body = RequestBody.create(JSON, json);
        String result = post(url, body);
//        logger.info("postJson with url={}, json={}, result={}", url, json, result);
        return result;
    }

    public static String postXml(String url, String xml) {
        RequestBody body = RequestBody.create(XML, xml);
        String result = post(url, body);
        logger.info("postXml with url={}, xml={}, result={}", url, xml, result);
        return result;
    }

    public static String postForm(String url, Map<String, String> form) {
        FormBody.Builder builder = new FormBody.Builder();
        form.entrySet().forEach(e -> builder.add(e.getKey(), e.getValue()));
        String result = post(url, builder.build());
        logger.info("postForm with url={}, form={}, result={}", url, form, result);
        return result;
    }

    private static String post(String url, RequestBody body) {
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (Exception e) {
            logger.error("execute url={}, occur an exception", url, e);
            return null;
        }
    }
}
