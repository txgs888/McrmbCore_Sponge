package com.mcrmb.sponge.result;

import com.google.gson.JsonObject;

/**
 * Created by txgs888 on 2017/4/13.
 */
public class PayResult extends Result {

    public PayResult(JsonObject json) {
        super(json);
        if (getData() != null) {
            this.money = getData().get("money").getAsInt();
            this.need = getData().get("need").getAsInt();
        }
    }

    public int getMoney() {
        return money;
    }

    public int getNeed() {
        return need;
    }

    private int money = -1;
    private int need = -1;
}
