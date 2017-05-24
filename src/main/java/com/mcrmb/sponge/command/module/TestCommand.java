package com.mcrmb.sponge.command.module;

import com.mcrmb.sponge.McrmbCoreMain;
import com.mcrmb.sponge.command.CommandHandler;
import com.mcrmb.sponge.utils.HttpUtil;
import com.mcrmb.sponge.utils.TextUtil;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.scheduler.Task;

import java.io.IOException;

/**
 * Created by 94913 on 2017/5/24.
 */
public class TestCommand implements CommandHandler {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getDescribe() {
        return "测试服务器是否能正常连接mcrmb服务器.";
    }

    @Override
    public boolean hasPermission(CommandSource source) {
        return source.hasPermission("mcrmb.admin");
    }

    @Override
    public boolean execute(CommandSource source, String[] args) {
        Task.builder().name("test-api")
                .execute(() -> {
                    source.sendMessage(TextUtil.of("§a正在连接§e百度"));
                    try {
                        HttpUtil.get("http://www.baidu.com");
                    } catch (IOException e) {
                        e.printStackTrace();
                        source.sendMessage(TextUtil.of("§c连接百度错误,你的服务器网络有故障,请联系你的服务商"));
                        return;
                    }
                    source.sendMessage(TextUtil.of("§a正在连接§eMCRMB"));
                    try {
                        HttpUtil.get("CheckMoney", "测试连接");
                    } catch (IOException e) {
                        e.printStackTrace();
                        source.sendMessage(TextUtil.of("§c连接MCRMB错误,请联系MCRMB管理员."));
                        return;
                    }
                    source.sendMessage(TextUtil.of("§2你的网络环境正常."));
                }).submit(McrmbCoreMain.instance());

        return true;
    }
}
