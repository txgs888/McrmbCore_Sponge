package com.mcrmb.sponge.type;

/**
 * Created by txgs888 on 2017/4/13.
 */
public enum ManualType {
    ADD(1),
    TAKE(2),
    RESET(3);

    ManualType(int i) {
        this.typeID = i;
    }

    private int typeID;

    public int getID() {
        return this.typeID;
    }

    @Override
    public String toString() {
        return String.valueOf(getID());
    }
}
