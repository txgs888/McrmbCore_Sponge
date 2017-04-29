package com.mcrmb.sponge.command.module;

import com.mcrmb.sponge.McrmbCoreAPI;
import com.mcrmb.sponge.command.CommandHandler;
import com.mcrmb.sponge.result.CheckMoneyResult;
import com.mcrmb.sponge.utils.TextUtil;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.scheduler.Task;

/**
 * Created by txgs888 on 2017/4/29.
 */
public class MoneyCommand implements CommandHandler {
    @Override
    public String getName() {
        return "money";
    }

    @Override
    public String getDescribe() {
        return "查询余额";
    }

    @Override
    public boolean hasPermission(CommandSource source) {
        return true;
    }

    @Override
    public boolean execute(CommandSource source, CommandContext context) {
        Task.builder().async().name("check-money").execute(() -> {
            CheckMoneyResult result = McrmbCoreAPI.checkMoney(source.getName());
            if (result == null) {
                source.sendMessage(TextUtil.of("§c查询余额失败."));
                return;
            }
            source.sendMessages(
                    TextUtil.of("§2当前余额: " + result.getMoney()),
                    TextUtil.of("§2历史消费: " + result.getAllCharge()),
                    TextUtil.of("§2历史充值: " + result.getAllPay())
            );
        });

        return true;
    }
}
