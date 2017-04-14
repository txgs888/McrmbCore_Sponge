package com.mcrmb.sponge;

import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import com.mcrmb.sponge.result.*;
import com.mcrmb.sponge.type.ManualType;
import com.mcrmb.sponge.utils.HttpUtil;
import com.mcrmb.sponge.utils.Util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by txgs888 on 2017/4/12.
 * 核心API
 */
public class McrmbCoreAPI {

    public static CheckMoneyResult checkMoney(String playerName) {
        try {
            playerName = playerName.toLowerCase();
            long time = System.currentTimeMillis() / 1000;
            String sign = Util.md5(McrmbPluginInfo.config.sid + playerName + time + McrmbPluginInfo.config.key);
            return new CheckMoneyResult(HttpUtil.get("CheckMoneyResult?sign=" + sign + "&sid=" + McrmbPluginInfo.config.sid + "&wname=" + playerName + "&time=" + time, "查询余额"));
        } catch (Exception e) {
            return null;
        }
    }

    public static ManualResult manual(String playerName, ManualType type, int money, String reason) {
        try {
            playerName = playerName.toLowerCase();
            long time = System.currentTimeMillis() / 1000;
            String sign = Util.md5(McrmbPluginInfo.config.sid + playerName + type + URLEncoder.encode(reason, "UTF-8") + money + time + McrmbPluginInfo.config.key);
            String typeName = (type.equals(ManualType.ADD) ? "加款" : type.equals(ManualType.TAKE) ? "扣款" : type.equals(ManualType.RESET) ? "重设点券" : "未知") + "(" + reason + ")";
            return new ManualResult(HttpUtil.get("ManualResult?sign=" + sign + "&sid=" + McrmbPluginInfo.config.sid
                    + "&wname=" + playerName + "&type=" + type + "&text=" + URLEncoder.encode(reason, "UTF-8") + "&money=" + money + "&time=" + time, typeName));
        } catch (Exception e) {
            return null;
        }

    }

    public static PayResult pay(String playerName, int money, String reason) {
        try {
            playerName = playerName.toLowerCase();
            long time = System.currentTimeMillis() / 1000;
            String sign = Util.md5(McrmbPluginInfo.config.sid + playerName + URLEncoder.encode(reason, "UTF-8") + money + time + McrmbPluginInfo.config.key);
            return new PayResult(HttpUtil.get("PayResult?sign=" + sign + "&sid=" + McrmbPluginInfo.config.sid
                    + "&wname=" + playerName + "&use=" + URLEncoder.encode(reason, "UTF-8") + "&money=" + money + "&time=" + time, "支付(" + reason + ")"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CheckRecordResult checkRecord(String playerName) {
        try {
            long time = System.currentTimeMillis() / 1000;
            String sign = Util.md5(McrmbPluginInfo.config.sid + playerName + time + McrmbPluginInfo.config.key);
            return new CheckRecordResult(HttpUtil.get("CheckRecord?sign=" + sign + "&sid=" + McrmbPluginInfo.config.sid + "&wname=" + playerName + "&time=" + time, "查询流水"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CheckCardResult checkCard(String playerName) {
        return checkCard(playerName, null);
    }

    public static CheckCardResult checkCard(String playerName, String number) {
        try {
            long time = System.currentTimeMillis() / 1000;
            String sign = Util.md5(McrmbPluginInfo.config.sid + playerName + (number == null ? "" : number) + time + McrmbPluginInfo.config.key);
            return new CheckCardResult(HttpUtil.get("CheckCard?sign=" + sign + "&sid=" + McrmbPluginInfo.config.sid + "&wname=" + playerName + (number == null ? "" : "&cnum=" + number) + "&time=" + time, "查询充值卡状态"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CardTypesResult cardTypes() {
        try {
            long time = System.currentTimeMillis() / 1000;
            String sign = Util.md5(McrmbPluginInfo.config.sid + "Console" + time + McrmbPluginInfo.config.key);
            return new CardTypesResult(HttpUtil.get("CardTypes?sign=" + sign + "&sid=" + McrmbPluginInfo.config.sid + "&wname=Console&time=" + time, "查询卡种类"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ChargeResult charge(String playerName, CardTypesResult.CardType type, String number, String password) {
        try {
            long time = System.currentTimeMillis() / 1000;
            String sign = Util.md5(McrmbPluginInfo.config.sid + playerName + type.getKey() + number + password + time + McrmbPluginInfo.config.key);
            return new ChargeResult(HttpUtil.get("Charge?sign=" + sign + "&sid=" + McrmbPluginInfo.config.sid + "&wname=" + playerName + "&ctype=" + type.getKey() + "&cnum=" + number + "&cpwd=" + password + "&time=" + time, "充值(" + type.getName() + ")"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}