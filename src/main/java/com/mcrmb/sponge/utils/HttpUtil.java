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
        return response.body().string();
    }

    /***
     * get请求mcrmbAPI并且转换成Object
     * @param url API
     * @param formatClass 要转换的对象class
     * @return Object
     * @throws IOException HTTP请求异常
     */
    public static Object get(String url, Class formatClass) throws IOException {
        String json = get(url);
        return format(json, formatClass);
    }

    /***
     * 将json转换为Object
     * @param json json字符串
     * @param formatClass 要转换的对象class
     * @return Object
     */
    public static Object format(String json, Class formatClass) {
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
