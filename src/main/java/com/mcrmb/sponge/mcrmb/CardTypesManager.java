package com.mcrmb.sponge.mcrmb;

import com.mcrmb.sponge.McrmbCoreAPI;
import com.mcrmb.sponge.McrmbCoreMain;
import com.mcrmb.sponge.result.CardTypesResult;

/**
 * Created by txgs888 on 2017/4/14.
 */
public class CardTypesManager {
    public static void init() {
        McrmbCoreMain.info("正在获取您的服务器允许使用的卡种类以及比率...");
        result = McrmbCoreAPI.cardTypes();
        if (result == null) {
            McrmbCoreMain.info("获取卡种失败(2), 请联系MCRMB管理员!");
        }
        if (result.getCode() == 101) {
            McrmbCoreMain.info("从您的服务器设置共获取到 " + result.getItems().size() + " 种支持卡密渠道!");
        } else if (result.getCode() == 102) {
            McrmbCoreMain.info("从您的服务器设置共获取到 0 种支持卡密渠道!");
        } else if (result.getCode() == 444) {
            McrmbCoreMain.info("§c签名错误!§r您当前sid=" + McrmbPluginInfo.config.sid + ", key=" + McrmbPluginInfo.config.key + ", 请检查是否正确, 用/b setup <sid> <key> 可以重设.");
        } else {
            McrmbCoreMain.info("获取卡种失败(2), 请联系MCRMB管理员!");
        }
    }

    public static CardTypesResult.CardType getItem(String key) {
        return result.getItem(key);
    }

    public static boolean containsItem(String key) {
        return result.containsItem(key);
    }

    private static CardTypesResult result;
}
