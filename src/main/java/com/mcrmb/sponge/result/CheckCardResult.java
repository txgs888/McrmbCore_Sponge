package com.mcrmb.sponge.result;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txgs888 on 2017/4/13.
 */
public class CheckCardResult extends Result {
    public CheckCardResult(JsonObject json) {
        super(json);
        this.items = new ArrayList<>();
        if (getData() != null) {
            for (JsonObject object : getDataList()) {
                this.items.add(new CheckCardItem(object));
            }
        }
    }

    public List<CheckCardItem> getItems() {
        return items;
    }

    private List<CheckCardItem> items;

    public class CheckCardItem {
        public CheckCardItem(JsonObject object) {
            this.date = object.get("date").getAsLong();
            this.num = object.get("num").getAsString();
            this.money = object.get("money").getAsInt();
            this.status = object.get("status").getAsInt();
        }

        public long getDate() {
            return date;
        }

        public String getNum() {
            return num;
        }

        public int getMoney() {
            return money;
        }

        public int getStatus() {
            return status;
        }

        private long date;
        private String num;
        private int money;
        private int status;
    }

}
