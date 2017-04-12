package com.mcrmb.sponge;

import com.google.gson.JsonObject;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import com.mcrmb.sponge.result.CheckMoney;
import com.mcrmb.sponge.utils.HttpUtil;
import com.mcrmb.sponge.utils.Util;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class McrmbAPI {
    public static CheckMoney checkMoney(String playerName) {
        try {
            playerName = playerName.toLowerCase();
            String time = String.valueOf(System.currentTimeMillis() / 1000);
            String sign = Util.md5(McrmbPluginInfo.config.sid + playerName + time + McrmbPluginInfo.config.key);
            CheckMoney result = new CheckMoney(HttpUtil.get("CheckMoney?sign=" + sign + "&sid=" + McrmbPluginInfo.config.sid + "&wname=" + playerName + "&time=" + time));
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
