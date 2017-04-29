package com.mcrmb.sponge.command;

import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import com.mcrmb.sponge.utils.TextUtil;
import com.mcrmb.sponge.utils.Util;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txgs888 on 2017/4/13.
 */
public class CommandProxy implements CommandExecutor {
    public CommandProxy() {
        this.handlerList = new ArrayList<>();
    }

    private List<CommandHandler> handlerList;

    public List<CommandHandler> getHandlerList() {
        return handlerList;
    }

    public void register(CommandHandler handler) {
        if (!this.handlerList.contains(handler)) this.handlerList.add(handler);
    }

    public void unregister(CommandHandler handler) {
        if (this.handlerList.contains(handler)) this.handlerList.remove(handler);
    }

    @Override
    public CommandResult execute(CommandSource source, CommandContext context) throws CommandException {
        //https://docs.spongepowered.org/stable/zh-CN/plugin/commands/arguments.html?highlight=commandcontext
        if (context.hasAny("arg")) {
            String[] args = context.<String>getOne("arg").get().split(" ");
            for (CommandHandler handler : this.handlerList) {
                if (args[0].equalsIgnoreCase(handler.getName()) && handler.hasPermission(source)) {
                    if (!handler.allowConsole() && !(source instanceof Player)) {//如果命令已禁用后台执行并且执行者是后台
                        source.sendMessage(TextUtil.of("§c后台无法执行该命令."));
                    } else {//否则
                        boolean success = handler.execute(source, args.length == 1 ? new String[0] : Util.subArray(args, 1, args.length));
                        if (!success) {
                            //命令没有执行成功
                        }
                    }
                    return CommandResult.success();
                }
            }
        }
        source.sendMessage(TextUtil.of("§2输入/" + McrmbPluginInfo.config.command + " help §2来查看帮助"));

        return CommandResult.success();
    }
}
