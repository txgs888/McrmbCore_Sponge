package com.mcrmb.sponge;

import com.mcrmb.sponge.command.BasicsCommand;
import com.mcrmb.sponge.hook.PlaceholderExpansion;
import com.mcrmb.sponge.mcrmb.CardTypesManager;
import com.mcrmb.sponge.mcrmb.ConfigManager;
import com.mcrmb.sponge.mcrmb.JavaPlugin;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import me.rojo8399.placeholderapi.PlaceholderAPIPlugin;
import me.rojo8399.placeholderapi.PlaceholderService;
import me.rojo8399.placeholderapi.expansions.Expansion;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void onServerStart(GameStartedServerEvent event) { //加载插件
        instance = this;
        super.onServerStart(event);
        getLogger().info("正在加载...");
        ConfigManager.init();
        McrmbPluginInfo.initMcrmbCore();
        registerCommand();
        getLogger().info("加载完成!");
        CardTypesManager.init();
        if (Sponge.getPluginManager().isLoaded("placeholderapi")) {
            PlaceholderExpansion.register();
            getLogger().info("检测到PlaceholderAPI, 已注册变量%mcrmb%");
        } else {
            getLogger().info("未检测到PlaceholderAPI");
        }
        Task.builder().delayTicks(0).intervalTicks(400).name("Check-SID-And-KEY").execute(task -> {
            if (McrmbPluginInfo.config.key == null || McrmbPluginInfo.config.sid == null) {
                Text waring = Text.of("§c§l当前服务器未设置SID和KEY, 请输入/b setup <sid> <key>进行设置.\n§c§l您可以前往 MCRMB后台->服务器管理 查看服务器的SID和KEY");
                Sponge.getServer().getConsole().sendMessage(waring);
                for (Player player : Sponge.getServer().getOnlinePlayers()) {
                    if (player.hasPermission("mcrmb.admin")) {
                        player.sendMessage(waring);
                    }
                }
            } else {
                task.cancel();
            }
        }).submit(this);
    }

    private void registerCommand() {
        CommandSpec basics = CommandSpec.builder()
                .description(Text.of("§2mcrmb.com"))
                .executor(new BasicsCommand())
                .build();
        Sponge.getCommandManager().register(this, basics, "mcrmb", "rmb", "b", "points");
    }
}
