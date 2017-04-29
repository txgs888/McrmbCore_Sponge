package com.mcrmb.sponge;

import com.mcrmb.sponge.command.CommandProxy;
import com.mcrmb.sponge.command.module.HelpCommand;
import com.mcrmb.sponge.command.module.MoneyCommand;
import com.mcrmb.sponge.hook.PlaceholderExpansion;
import com.mcrmb.sponge.listener.PlayerJoinListener;
import com.mcrmb.sponge.mcrmb.CardTypesManager;
import com.mcrmb.sponge.mcrmb.ConfigManager;
import com.mcrmb.sponge.mcrmb.JavaPlugin;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;


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
        Sponge.getEventManager().registerListeners(this, new PlayerJoinListener());
        if (Sponge.getPluginManager().isLoaded("placeholderapi")) {
            PlaceholderExpansion.register();
            getLogger().info("检测到PlaceholderAPI, 已注册变量%mcrmb%");
        } else {
            getLogger().info("未检测到PlaceholderAPI");
        }
        getLogger().info("加载完成!");
        CardTypesManager.init();

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

    private CommandProxy commandProxy;

    public CommandProxy getCommandProxy() {
        return commandProxy;
    }

    private void registerCommand() {
        this.commandProxy = new CommandProxy();
        CommandSpec basics = CommandSpec.builder()
                .description(Text.of("§2mcrmb.com"))
                .executor(commandProxy)
                .arguments(
                        GenericArguments.remainingRawJoinedStrings(Text.of("arg")))
                .build();
        Sponge.getCommandManager().register(this, basics, McrmbPluginInfo.config.command);

        //注册命令
        getCommandProxy().register(new HelpCommand());
        getCommandProxy().register(new MoneyCommand());
    }
}
