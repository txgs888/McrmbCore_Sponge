package com.mcrmb.sponge.command.module;

import com.mcrmb.sponge.McrmbCoreAPI;
import com.mcrmb.sponge.McrmbCoreMain;
import com.mcrmb.sponge.command.CommandHandler;
import com.mcrmb.sponge.result.CheckMoneyResult;
import com.mcrmb.sponge.utils.TextUtil;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
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
        return source instanceof Player;
    }

    @Override
    public boolean execute(CommandSource source, String[] args) {
        Task.builder().name("check-money")
                .execute(() -> {
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
                }).submit(McrmbCoreMain.instance());


        return true;
    }
}
