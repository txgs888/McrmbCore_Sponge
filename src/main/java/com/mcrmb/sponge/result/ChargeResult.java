package com.mcrmb.sponge.result;

import com.google.gson.JsonObject;

/**
 * Created by txgs888 on 2017/4/13.
 */
public class ChargeResult extends Result {
    public ChargeResult(JsonObject json) {
        super(json);
        if (getData() != null) {
            this.wmsg = getData().get("wmsg").getAsString();
        }
    }

    public String getWmsg() {
        return wmsg;
    }

    private String wmsg;

}
