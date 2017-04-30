package com.mcrmb.sponge.command.module;

import com.mcrmb.sponge.McrmbCoreMain;
import com.mcrmb.sponge.command.CommandHandler;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import com.mcrmb.sponge.utils.TextUtil;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txgs888 on 2017/4/29.
 */
public class HelpCommand implements CommandHandler {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescribe() {
        return "获取所有命令帮助";
    }

    @Override
    public boolean hasPermission(CommandSource source) {
        return true;
    }

    @Override
    public boolean execute(CommandSource source, String[] args) {
        List<Text> messages = new ArrayList<>();
        for (CommandHandler handler : McrmbCoreMain.instance().getCommandProxy().getHandlerList()) {
            if (handler.hasPermission(source)) {
                messages.add(TextUtil.of("§7/" + McrmbPluginInfo.config.command + " " + handler.getName() + "  §8-  §f" + handler.getDescribe()));
            }
        }
        source.sendMessages(messages);
        return true;
    }
}
