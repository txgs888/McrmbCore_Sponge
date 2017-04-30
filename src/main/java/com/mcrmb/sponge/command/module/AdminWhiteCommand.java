package com.mcrmb.sponge.command.module;

import com.mcrmb.sponge.command.CommandHandler;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import com.mcrmb.sponge.utils.TextUtil;
import com.mcrmb.sponge.utils.Util;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.entity.living.player.Player;

/**
 * Created by txgs888 on 2017/4/30.
 */
public class AdminWhiteCommand implements CommandHandler {
    @Override
    public String getName() {
        return "adminwhite";
    }

    @Override
    public String getDescribe() {
        return "允许使用操作点券命令的ID白名单";
    }

    @Override
    public boolean hasPermission(CommandSource source) {
        return source instanceof ConsoleSource;
    }

    @Override
    public boolean execute(CommandSource source, String[] args) {
        if ((args.length == 1 && args[0].equalsIgnoreCase("list"))) {
            source.sendMessage(TextUtil.of("§2白名单中共有§a" + McrmbPluginInfo.config.opModifyWhiteList.size() + "§2个ID"));
            for (String id : McrmbPluginInfo.config.opModifyWhiteList) {
                source.sendMessage(TextUtil.of("§f - §e" + id));
            }
            return true;
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("add")) {
                if (Util.arrayEqualsIgnoreCase(McrmbPluginInfo.config.opModifyWhiteList, args[1])) {
                    source.sendMessage(TextUtil.of("§c白名单中已经有了这个ID,请勿重复添加"));
                    return true;
                }
                McrmbPluginInfo.config.opModifyWhiteList.add(args[1]);
                McrmbPluginInfo.save();
                source.sendMessage(TextUtil.of("§2添加ID§e" + args[1] + "§2到白名单成功,该玩家已允许使用操作点券命令."));
                return true;
            } else if (args[0].equalsIgnoreCase("remove")) {
                if (Util.arrayRemoveIgnoreCase(McrmbPluginInfo.config.opModifyWhiteList, args[1])) {
                    McrmbPluginInfo.save();
                    source.sendMessage(TextUtil.of("§2从白名单删除ID§e" + args[1] + "§2成功,该玩家已禁止操作点券."));
                    return true;
                } else {
                    source.sendMessage(TextUtil.of("§c白名单中没有找到这个ID,无法删除"));
                    return true;
                }
            }
        }
        source.sendMessages(
                TextUtil.of("§c/" + McrmbPluginInfo.config.command + " adminwhite add <玩家名字>"),
                TextUtil.of("§c/" + McrmbPluginInfo.config.command + " adminwhite remove <玩家名字>"),
                TextUtil.of("§c/" + McrmbPluginInfo.config.command + " adminwhite list")
        );

        return true;
    }
}
