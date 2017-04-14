package com.mcrmb.sponge.result;

import com.google.gson.JsonObject;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class CheckMoneyResult extends Result {

    public CheckMoneyResult(JsonObject json) {
        super(json);
        if (getData() != null) {
            this.money = getData().get("money").getAsInt();
            this.allCharge = getData().get("allcharge").getAsInt();
            this.allPay = getData().get("allpay").getAsInt();
        }

    }

    public int getMoney() {
        return money;
    }

    public int getAllCharge() {
        return allCharge;
    }

    public int getAllPay() {
        return allPay;
    }

    private int money = -1;
    private int allCharge = -1;
    private int allPay = -1;
}
