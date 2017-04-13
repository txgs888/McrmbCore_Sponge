package com.mcrmb.sponge;

import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import com.mcrmb.sponge.result.CheckMoney;
import com.mcrmb.sponge.result.Manual;
import com.mcrmb.sponge.type.ManualType;
import com.mcrmb.sponge.utils.HttpUtil;
import com.mcrmb.sponge.utils.Util;

import java.net.URLEncoder;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class McrmbAPI {
    public static CheckMoney checkMoney(String playerName) {
        try {
            playerName = playerName.toLowerCase();
            String time = String.valueOf(System.currentTimeMillis() / 1000);
            String sign = Util.md5(McrmbPluginInfo.config.sid + playerName + time + McrmbPluginInfo.config.key);
            return new CheckMoney(HttpUtil.get("CheckMoney?sign=" + sign + "&sid=" + McrmbPluginInfo.config.sid + "&wname=" + playerName + "&time=" + time));
        } catch (Exception e) {
            return null;
        }
    }

    public static Manual manual(String playerName, ManualType type, int money, String reason) {
        try {
            playerName = playerName.toLowerCase();
            String time = String.valueOf(System.currentTimeMillis() / 1000);
            String sign = Util.md5(McrmbPluginInfo.config.sid + playerName + type + URLEncoder.encode(reason, "UTF-8") + money + time + McrmbPluginInfo.config.key);
            return new Manual(HttpUtil.get("Manual?sign=" + sign + "&sid=" + McrmbPluginInfo.config.sid
                    + "&wname=" + playerName + "&type=" + type + "&text=" + URLEncoder.encode(reason, "UTF-8") + "&money=" + money + "&time=" + time));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}