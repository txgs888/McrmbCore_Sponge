package com.mcrmb.sponge.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mcrmb.sponge.McrmbCoreMain;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


/**
 * Created by txgs888 on 2017/4/12.
 */
public class HttpUtil {
    private final static String api = "http://api.mcrmb.com/Api/";
    private final static String java_version = "Java_" + System.getProperty("java.version");
    private final static String os = System.getProperty("os.name") + "_" + System.getProperty("os.arch") + "_" + System.getProperty("os.version");

    /***
     * get请求mcrmbAPI
     * @param url API
     * @param reason 理由
     * @return json
     * @throws IOException HTTP请求异常
     */
    public static JsonObject get(String url, String reason) throws IOException {
        if (McrmbPluginInfo.config.logApi) {
            McrmbCoreMain.info("发起" + reason + "请求: " + api + url);
        }
        OkHttpClient client = getHttpClient();
        Request request = new Request.Builder()
                .url(api + url)
                .header("User-Agent", java_version)
                .header("OS-Info", os)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return new JsonParser().parse(response.body().string()).getAsJsonObject();
    }

    /***
     * 获取一个OkHttpClient 这个方法主要是为了以后万一添加代理选项
     * @return OkHttpClient
     */
    private static OkHttpClient getHttpClient() {
        return new OkHttpClient();
    }
}
