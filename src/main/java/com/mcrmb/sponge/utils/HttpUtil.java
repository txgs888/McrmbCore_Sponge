package com.mcrmb.sponge.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mcrmb.sponge.McrmbCoreAPI;
import com.mcrmb.sponge.McrmbCoreMain;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


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
     * @throws Exception HTTP请求异常
     */
    public static JsonObject get(String url, String reason) throws Exception {
        if (McrmbPluginInfo.config.logApi) {
            McrmbCoreMain.info("发起" + reason + "请求: " + api + url);
        }
        StringBuilder builder = new StringBuilder();
        URLConnection con = new URL(api + url).openConnection();
        con.setRequestProperty("User-Agent", java_version);
        con.setRequestProperty("OS-Info", os);
        con.setConnectTimeout(25000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        reader.close();
        McrmbCoreMain.info("返回:" + builder.toString());
        return new JsonParser().parse(builder.toString()).getAsJsonObject();
    }

}
