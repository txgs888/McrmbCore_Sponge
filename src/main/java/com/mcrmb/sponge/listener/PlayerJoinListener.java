package com.mcrmb.sponge.listener;

import com.mcrmb.sponge.McrmbCoreAPI;
import com.mcrmb.sponge.mcrmb.ConfigManager;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

/**
 * Created by txgs888 on 2017/4/29.
 */
public class PlayerJoinListener {
    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event) {
        if (McrmbPluginInfo.config.renewOnJoin) {
            McrmbCoreAPI.checkMoney(event.getTargetEntity().getName());
        }
    }
}
