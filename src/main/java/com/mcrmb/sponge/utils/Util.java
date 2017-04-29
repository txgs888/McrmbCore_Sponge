package com.mcrmb.sponge.utils;

import java.security.MessageDigest;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class Util {
    public static String md5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    public static String[] subArray(String[] src, int begin, int count) {
        String[] bs = new String[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }
}
