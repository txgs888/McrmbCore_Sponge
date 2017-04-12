package com.mcrmb.sponge;

import com.sun.javaws.security.Resource;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

/**
 * Created by txgs888 on 2017/4/12.
 */

@Plugin(id = McrmbPluginInfo.ID, name = McrmbPluginInfo.NAME, version = McrmbPluginInfo.VERSION, authors = {McrmbPluginInfo.AUTHORS})
public class McrmbCoreMain extends JavaPlugin {

    @Override
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        super.onServerStart(event);
        getLogger().info("HelloWorld");

    }
}
