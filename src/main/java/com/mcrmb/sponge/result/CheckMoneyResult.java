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
            this.allcharge = getData().get("allcharge").getAsInt();
            this.allpay = getData().get("allpay").getAsInt();
        }

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

    private int money = -1;
    private int allcharge = -1;
    private int allpay = -1;
}
