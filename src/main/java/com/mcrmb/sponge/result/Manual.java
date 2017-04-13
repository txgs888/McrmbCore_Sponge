package com.mcrmb.sponge.result;

import com.google.gson.JsonObject;

/**
 * Created by txgs888 on 2017/4/13.
 */
public class Manual extends Result {

    public Manual(JsonObject json) {
        super(json);
    }

    @Override
    public String toString() {
        return getMsg();
    }
}
