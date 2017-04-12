package com.mcrmb.sponge;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;

import java.nio.file.Path;

/**
 * Created by txgs888 on 2017/4/12.
 */
public class JavaPlugin {
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        this.logger = Sponge.getPluginManager().getPlugin(McrmbPluginInfo.ID).get().getLogger();
    }

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path path;
    @Inject
    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public Path getPath() {
        return path;
    }
}
