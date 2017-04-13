package com.mcrmb.sponge.result;

import com.google.gson.JsonObject;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class CheckMoney extends Result {

    public CheckMoney(JsonObject json) {
        super(json);
        this.money = getData().get("money").getAsInt();
        this.allcharge = getData().get("allcharge").getAsInt();
        this.allpay = getData().get("allpay").getAsInt();
    }

    public int getMoney() {
        return money;
    }

    public int getAllcharge() {
        return allcharge;
    }

    public int getAllpay() {
        return allpay;
    }

    private int money;
    private int allcharge;
    private int allpay;

    @Override
    public String toString() {
        return getMsg();
    }
}
