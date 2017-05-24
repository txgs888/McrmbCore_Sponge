package com.mcrmb.sponge.mcrmb;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class McrmbPluginInfo {
    public final static String ID = "mcrmb";
    public final static String NAME = "MCRMB";
    public final static String VERSION = "1.0.1";
    public final static String AUTHORS = "mcrmb.com";

    public static CommentedConfigurationNode commentedConfig;

    public static class config {
        public static String sid;
        public static String key;
        public static boolean logApi;
        public static boolean renewOnJoin;
        public static List<String> opModifyWhiteList;
        public static String point;
        public static String prefix;
        public static String command;
    }


    public static void initMcrmbCore() {
        commentedConfig = ConfigManager.get().getConfig();
        config.sid = commentedConfig.getNode("sid").getString();
        if (config.sid.equalsIgnoreCase("null")) {
            config.sid = null;
        }
        config.key = commentedConfig.getNode("key").getString();
        if (config.key.equalsIgnoreCase("null")) {
            config.key = null;
        }

        config.logApi = commentedConfig.getNode("logApi").getBoolean();
        config.renewOnJoin = commentedConfig.getNode("renewOnJoin").getBoolean();
        config.opModifyWhiteList = new ArrayList<>();
        try {
            config.opModifyWhiteList.addAll(commentedConfig.getNode("opModifyWhiteList").getList(TypeToken.of(String.class)));
        } catch (ObjectMappingException e) {
        }
        config.point = commentedConfig.getNode("point").getString().replace("&", "§");
        config.prefix = commentedConfig.getNode("prefix").getString().replace("&", "§");
        config.command = commentedConfig.getNode("command").getString();

    }

    public static void save() {
        try {
            //使用反射保存config类中的所有变量
            Class<?> configClass = Class.forName("com.mcrmb.sponge.mcrmb.McrmbPluginInfo$config");
            Field[] fields = configClass.getDeclaredFields();
            CommentedConfigurationNode node = ConfigManager.get().getConfig();
            for (Field field : fields) {
                try {
                    node.getNode(field.getName()).setValue(field.get(configClass));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            ConfigManager.get().save();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
