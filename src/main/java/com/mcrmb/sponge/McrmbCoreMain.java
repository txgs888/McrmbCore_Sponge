package com.mcrmb.sponge;

import com.mcrmb.sponge.mcrmb.ConfigManager;
import com.mcrmb.sponge.mcrmb.JavaPlugin;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import com.mcrmb.sponge.type.ManualType;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

/**
 * Created by txgs888 on 2017/4/12.
 */

@Plugin(id = McrmbPluginInfo.ID, name = McrmbPluginInfo.NAME, version = McrmbPluginInfo.VERSION, authors = {McrmbPluginInfo.AUTHORS})
public class McrmbCoreMain extends JavaPlugin {
    private static McrmbCoreMain instance;

    public static void info(String log) {
        instance().getLogger().info(log);
    }

    public static McrmbCoreMain instance() {
        return instance;
    }

    @Override
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        instance = this;
        super.onServerStart(event);
        getLogger().info("正在加载插件...");
        ConfigManager.init();
        McrmbPluginInfo.initMcrmbCore();
        getLogger().info("加载完成!");

        info("test: " + McrmbAPI.pay("txgs888", 100, "test"));
    }
}
