package com.mcrmb.sponge.command.module;

import com.mcrmb.sponge.McrmbCoreMain;
import com.mcrmb.sponge.command.CommandHandler;
import com.mcrmb.sponge.mcrmb.CardTypesManager;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import com.mcrmb.sponge.utils.TextUtil;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.scheduler.Task;

/**
 * Created by txgs888 on 2017/4/29.
 */
public class SetupCommand implements CommandHandler {
    @Override
    public String getName() {
        return "setup";
    }

    @Override
    public String getDescribe() {
        return "配置MCRMB的SID和KEY";
    }

    @Override
    public boolean hasPermission(CommandSource source) {
        return source.hasPermission("mcrmb.admin");
    }

    @Override
    public boolean allowConsole() {
        return true;
    }

    @Override
    public boolean execute(CommandSource source, String[] args) {
        if (args.length != 2) {
            source.sendMessage(TextUtil.of("§c/" + McrmbPluginInfo.config.command + " setup <SID> <KEY>"));
            return true;
        }
        String sid = args[0];
        String key = args[1];
        McrmbPluginInfo.config.sid = sid;
        McrmbPluginInfo.config.key = key;
        McrmbPluginInfo.save();
        source.sendMessages(
                TextUtil.of("§2已将SID设置为§a" + sid + ", §2KEY设置为§a" + key + "."),
                TextUtil.of("§2正在为您重新获取卡种...")
        );
        Task.builder().name("reload-card-types").execute(() -> {
            CardTypesManager.init();
            source.sendMessage(TextUtil.of("§2卡种重新获取完成, 详情请查看后台输出."));
        }).submit(McrmbCoreMain.instance());

        return true;
    }
}
