package com.mcrmb.sponge.mcrmb;

import com.mcrmb.sponge.McrmbCoreMain;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class ConfigManager {

    private Path path;
    private CommentedConfigurationNode config;
    private ConfigurationLoader<CommentedConfigurationNode> loader;

    private static ConcurrentHashMap<String, ConfigManager> configManagers = new ConcurrentHashMap<>();

    private ConfigManager(String configName) {
        try {
            path = McrmbCoreMain.instance().getPath().resolve(configName + ".conf");

            if (!Files.exists(path)) {
                Files.createFile(path);
                McrmbCoreMain.instance().getLogger().info("创建新的配置文件: " + path.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        load();
    }

    public static ConfigManager get(String configName) {
        return configManagers.get(configName);
    }

    public static ConfigManager get() {
        return configManagers.get("config");
    }

    public static ConfigManager init() {
        return init("config");
    }

    private static void setDefaultValue(CommentedConfigurationNode config, Object value, Object... key) {
        if (config.getNode(key).isVirtual()) {
            config.getNode(key).setValue(value);
        }
    }

    public static ConfigManager init(String configName) {
        ConfigManager configManager = new ConfigManager(configName);
        CommentedConfigurationNode config = configManager.getConfig();

        if (configName.equalsIgnoreCase("config")) {
            // config.conf 配置文件设置
            setDefaultValue(config, "null", "sid");
            setDefaultValue(config, "null", "key");
            setDefaultValue(config, true, "logApi"); //key尽量不要包括下划线! 否则在配置文件会被加上引号,显示为 "key"=xxx
            setDefaultValue(config, true, "renewOnJoin");
            setDefaultValue(config, new ArrayList<String>(), "opModifyWhite-list");
            setDefaultValue(config, "点券", "point");
            setDefaultValue(config, "§a[§e点券中心§a] §2", "prefix");
        }

        configManager.save();

        configManagers.put(configName, configManager);

        return configManager;
    }

    public ConfigurationLoader<CommentedConfigurationNode> getLoader() {
        return loader;
    }

    public CommentedConfigurationNode getConfig() {
        return config;
    }

    public void save() {
        try {
            loader.save(config);
        } catch (IOException e) {
            McrmbCoreMain.instance().getLogger().error("保存配置文件时出错!");
            e.printStackTrace();
        }
    }

    private void load() {
        loader = HoconConfigurationLoader.builder().setPath(path).build();
        try {
            config = loader.load();
        } catch (IOException e) {
            McrmbCoreMain.instance().getLogger().error("加载配置文件时出错!");
            e.printStackTrace();
        }
    }
}