package com.mcrmb.sponge;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import com.mcrmb.sponge.utils.HttpUtil;
import com.mcrmb.sponge.utils.Util;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class McrmbAPI {
    public static int look(String playerName) {
        try {
            playerName = playerName.toLowerCase();
            String time = String.valueOf(System.currentTimeMillis() / 1000);
            String sign = Util.md5(McrmbPluginInfo.config.sid + playerName + time + McrmbPluginInfo.config.key);
            JsonObject json = HttpUtil.get("CheckMoney?sign=" + sign + "&sid=" + McrmbPluginInfo.config.sid + "&wname=" + playerName + "&time=" + time);
            if (json.get("code").getAsString().equals("101")) {
                JsonObject data = json.getAsJsonArray("data").get(0).getAsJsonObject();
                return Integer.parseInt(data.get("money").getAsString());
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
}
