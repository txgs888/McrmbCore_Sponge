package com.mcrmb.sponge.type;

import java.util.HashMap;

/**
 * Created by txgs888 on 2017/4/13.
 */
public class CardStatus {


    private static HashMap<Integer, String> status;

    static {
        status = new HashMap<>();
        status.put(0, "卡异常，联系服主！");
        status.put(100, "卡异常，联系服主！");
        status.put(101, "卡异常，联系服主！");
        status.put(102, "卡异常，联系服主！");
        status.put(103, "卡异常，联系服主！");
        status.put(104, "卡异常，联系服主！");
        status.put(105, "卡异常，联系服主！");
        status.put(106, "卡异常，联系服主！");
        status.put(200, "卡密处理中！");
        status.put(201, "卡密不正确！");
        status.put(202, "不支持这种卡！");
        status.put(203, "处理异常，联系服主！");
        status.put(204, "不支持这种面值的卡！");
        status.put(205, "处理异常，联系服主！");
        status.put(206, "运营商维护中！");
        status.put(208, "卡已经被使用过了！");
        status.put(209, "卡提交过于频繁！");
        status.put(210, "处理异常，联系服主！");
        status.put(300, "卡已经处理成功！");
        status.put(302, "卡密处理失败！");
        status.put(303, "卡号无效！");
        status.put(304, "卡密错误！");
        status.put(305, "卡上余额不足！");
        status.put(306, "卡的状态错误！");
    }

    public static String valueOf(int code) {
        if(status.containsKey(code)){
            return status.get(code);
        }else{
            return "未知状态！";
        }

    }
}
