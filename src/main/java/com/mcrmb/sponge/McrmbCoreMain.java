package com.mcrmb.sponge;

import com.google.inject.Inject;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import java.util.logging.Logger;

/**
 * Created by txgs888 on 2017/4/12.
 */

@Plugin(id = "com.mcrmb.sponge", name = "Mcrmb", version = "1.0.1")
public class McrmbCoreMain extends PluginLogger {
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        getLogger().info("HelloWorld");
    }
}
