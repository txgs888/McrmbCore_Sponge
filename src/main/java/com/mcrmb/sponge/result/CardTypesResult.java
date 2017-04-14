package com.mcrmb.sponge.result;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by txgs888 on 2017/4/14.
 */
public class CardTypesResult extends Result {
    public CardTypesResult(JsonObject json) {
        super(json);
        this.items = new ArrayList<>();
        this.itemMap = new HashMap<>();
        if (getData() != null) {
            for (Map.Entry<String, JsonElement> item : getData().entrySet()) {
                CardType cardType = new CardType(item.getKey(), item.getValue().getAsJsonObject());
                this.items.add(cardType);
                this.itemMap.put(item.getKey(), cardType);
            }
        }
    }

    public List<CardType> getItems() {
        return items;
    }

    public CardType getItem(String key) {
        return this.itemMap.get(key);
    }

    public boolean containsItem(String key) {
        return this.itemMap.containsKey(key);
    }

    private List<CardType> items;
    private HashMap<String, CardType> itemMap;

    public class CardType {
        public CardType(String key, JsonObject object) {
            this.key = key;
            this.id = object.get("id").getAsInt();
            this.name = object.get("name").getAsString();
            this.num = object.get("num").getAsInt();
            this.pwd = object.get("pwd").getAsInt();
            this.rate = object.get("rate").getAsInt();
        }

        public String getKey() {
            return key;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getNum() {
            return num;
        }

        public int getPwd() {
            return pwd;
        }

        public int getRate() {
            return rate;
        }

        private String key;
        private int id;
        private String name;
        private int num;
        private int pwd;
        private int rate;
    }
}
