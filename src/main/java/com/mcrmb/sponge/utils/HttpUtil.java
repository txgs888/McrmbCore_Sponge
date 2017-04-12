package com.mcrmb.sponge.utils;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


/**
 * Created by txgs888 on 2017/4/12.
 */
public class HttpUtil {
    private final static String api = "http://api.mcrmb.com/Api/";

    /***
     * get请求mcrmbAPI
     * @param url API
     * @return json
     * @throws IOException HTTP请求异常
     */
    public static String get(String url) throws IOException {
        OkHttpClient client = getHttpClient();
        Request request = new Request.Builder()
                .url(api + url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        System.out.println("请求:" + api + url);
        System.out.println("返回:" + response.body().string());
        return response.body().string();
    }

    /***
     * get请求mcrmbAPI并且转换成Object
     * @param url API
     * @param formatClass 要转换的对象class
     * @return Object
     * @throws IOException HTTP请求异常
     */
    public static <T> T get(String url, Class<T> formatClass) throws IOException {
        String json = get(url);
        return format(json, formatClass);
    }

    /***
     * 将json转换为Object
     * @param json json字符串
     * @param formatClass 要转换的对象class
     * @return Object
     */
    public static <T> T format(String json, Class<T> formatClass) {
        return new Gson().fromJson(json, formatClass);
    }

    /***
     * 获取一个OkHttpClient 这个方法主要是为了以后万一添加代理选项
     * @return OkHttpClient
     */
    private static OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }
}
