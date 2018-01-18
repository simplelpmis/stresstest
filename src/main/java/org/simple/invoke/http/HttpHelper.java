package org.simple.invoke.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class HttpHelper {

    public static final String UTF_8 = "utf-8";
    public final static String POST_METHOD = "POST";

    public static String doPost(String serverUrl, String params) {
        HttpURLConnection connection = null;
        StringBuilder result = new StringBuilder();
        try {
            // 创建连接
            URL url = new URL(serverUrl);
            connection = (HttpURLConnection) url.openConnection();

            // 设置http连接属性
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod(POST_METHOD);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);

            // 设置http头 消息
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // 连接
            connection.connect();

            // 获取结果
            OutputStream out = connection.getOutputStream();
            out.write(params.getBytes(UTF_8));
            out.flush();
            out.close();

            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), UTF_8));
            String lines;
            while ((lines = reader.readLine()) != null) {
                result.append(lines);
            }
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return result.toString();
    }

    public static String doGet(String serverUrl) {
        HttpURLConnection connection = null;
        StringBuilder result = new StringBuilder();
        try {
            // 创建连接
            URL url = new URL(serverUrl);
            connection = (HttpURLConnection) url.openConnection();

            // 连接
            connection.connect();

            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), UTF_8));
            String lines;
            while ((lines = reader.readLine()) != null) {
                result.append(lines);
            }
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(doGet("http://localhost:18888/system/user/list?currentPage=0&pageSize=2&userCode=1001"));
    }
}
