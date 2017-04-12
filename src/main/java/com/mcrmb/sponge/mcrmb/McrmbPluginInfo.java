package com.mcrmb.sponge.mcrmb;

import com.google.common.reflect.TypeToken;
import com.mcrmb.sponge.McrmbCoreMain;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class McrmbPluginInfo {
    public final static String ID = "mcrmb";
    public final static String NAME = "MCRMB";
    public final static String VERSION = "1.0.0";
    public final static String AUTHORS = "mcrmb.com";

    public static CommentedConfigurationNode commentedConfig;

    public static class config {
        public static String sid;
        public static String key;
        public static boolean logApi;
        public static boolean renewOnJoin;
        public static List<String> opModifyWhiteList;
        public static Text point;
        public static Text prefix;
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
        if (config.key == null || config.sid == null) {
            McrmbCoreMain.info("当前服务器未设置SID和KEY, 请输入/b setup <sid> <key>进行设置.");
            McrmbCoreMain.info("您可以前往 MCRMB后台->服务器管理 查看服务器的SID和KEY");
        }
        config.logApi = commentedConfig.getNode("logApi").getBoolean();
        config.renewOnJoin = commentedConfig.getNode("renewOnJoin").getBoolean();
        try {
            config.opModifyWhiteList = commentedConfig.getNode("opModifyWhiteList").getList(TypeToken.of(String.class));
        } catch (ObjectMappingException e) {
            config.opModifyWhiteList = new ArrayList<>();
        }
        // Sponge中使用Text类来管理信息内容
        // Text.join(McrmbPluginInfo.config.prefix, Text.of("你的"), McrmbPluginInfo.config.point, Text.of("不足"))
        config.point = Text.of(commentedConfig.getNode("point").getString().replace("&", "§"));
        config.prefix = Text.of(commentedConfig.getNode("prefix").getString().replace("&", "§"));


    }
}
