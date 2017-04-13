package com.mcrmb.sponge.result;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txgs888 on 2017/4/13.
 */
public class CheckRecordResult extends Result {

    public CheckRecordResult(JsonObject json) {
        super(json);
        this.items = new ArrayList<>();
        if (getData() != null) {
            for (JsonObject object : getDataList()) {
                this.items.add(new CheckRecordItem(object));
            }
        }
    }

    public List<CheckRecordItem> getItems() {
        return items;
    }

    private List<CheckRecordItem> items;

    public class CheckRecordItem {
        public CheckRecordItem(JsonObject object) {
            this.date = object.get("date").getAsLong();
            this.text = object.get("text").getAsString();
            this.money = object.get("money").getAsInt();
        }

        public long getDate() {
            return date;
        }

        public String getText() {
            return text;
        }

        public int getMoney() {
            return money;
        }

        private long date;
        private String text;
        private int money;
    }
}
