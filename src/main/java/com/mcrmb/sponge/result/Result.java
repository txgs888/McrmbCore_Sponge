package com.mcrmb.sponge.result;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class Result {
    public Result(JsonObject json) {
        this.code = json.get("code").getAsInt();
        this.msg = json.get("msg").getAsString();
        this.data = new ArrayList<>();
        if (json.get("data") instanceof JsonArray) {
            for (JsonElement element : json.get("data").getAsJsonArray()) {
                this.data.add(element.getAsJsonObject());
            }
        }
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public JsonObject getData() {
        if (data.size() > 0) {
            return data.get(0);
        }
        return null;
    }

    public List<JsonObject> getDataList() {
        return data;
    }

    private int code;
    private String msg;
    private List<JsonObject> data;

    @Override
    public String toString() {
        return getMsg();
    }
}
