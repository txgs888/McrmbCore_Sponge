package com.mcrmb.sponge.command.module;

import com.mcrmb.sponge.McrmbCoreAPI;
import com.mcrmb.sponge.McrmbCoreMain;
import com.mcrmb.sponge.command.CommandHandler;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import com.mcrmb.sponge.result.ManualResult;
import com.mcrmb.sponge.type.ManualType;
import com.mcrmb.sponge.utils.TextUtil;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.scheduler.Task;

/**
 * Created by txgs888 on 2017/4/30.
 */
public class GiveCommand implements CommandHandler {
    @Override
    public String getName() {
        return "give";
    }

    @Override
    public String getDescribe() {
        return "增加玩家点券";
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
        if ((args.length != 2 && args.length != 3) || !StringUtils.isNumeric(args[1])) {
            source.sendMessage(TextUtil.of("§c/" + McrmbPluginInfo.config.command + " give <玩家> <增加数量> [理由]"));
            return true;
        }
        if (source instanceof Player && !McrmbPluginInfo.config.opModifyWhiteList.contains(source.getName())) {
            source.sendMessage(TextUtil.of("§c你无法执行该操作,你可以在后台输入/" + McrmbPluginInfo.config.command + " adminwhite add <你的ID> 来添加你到操作点券白名单"));
            return true;
        }
        Task.builder().name("manual-give")
                .execute(() -> {
                    ManualResult result = McrmbCoreAPI.manual(args[0], ManualType.ADD, Integer.parseInt(args[1]), args.length == 3 ? args[2] : source.getName() + "加款");
                    if (result == null) {
                        source.sendMessage(TextUtil.of("§c加款失败"));
                        return;
                    }
                    source.sendMessage(TextUtil.of("§2加款成功,返回信息: §a" + result.getMsg()));
                }).submit(McrmbCoreMain.instance());
        return true;
    }
}
