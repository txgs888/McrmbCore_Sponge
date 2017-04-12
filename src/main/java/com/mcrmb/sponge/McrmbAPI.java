package com.mcrmb.sponge;

import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import com.mcrmb.sponge.utils.HttpUtil;
import com.mcrmb.sponge.utils.Util;

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
            String[] args;
            args = HttpUtil.get("CheckMoney?sign=" + sign + "&sid=" + McrmbPluginInfo.config.sid + "&wname=" + playerName + "&time=" + time, String[].class);
            if (args[0].equals("101")) {
                Map<String, String> data = HttpUtil.format(args[2], Map.class);
                return Integer.parseInt(data.get("money"));
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
}
