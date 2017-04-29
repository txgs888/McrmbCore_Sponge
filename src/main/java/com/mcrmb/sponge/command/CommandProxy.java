package com.mcrmb.sponge.command;

import com.mcrmb.sponge.McrmbCoreMain;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

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

    public void register(CommandHandler handler) {
        if (!this.handlerList.contains(handler)) this.handlerList.add(handler);
    }

    public void unregister(CommandHandler handler) {
        if (this.handlerList.contains(handler)) this.handlerList.remove(handler);
    }

    @Override
    public CommandResult execute(CommandSource source, CommandContext context) throws CommandException {
        //https://docs.spongepowered.org/stable/zh-CN/plugin/commands/arguments.html?highlight=commandcontext
        String subcommand = context.<String>getOne("string").get();
        for (CommandHandler handler : this.handlerList) {
            if (subcommand.equalsIgnoreCase(handler.getName()) && handler.hasPermission(source)) {
                boolean success = handler.execute(source, context);
                if (!success) {
                    //命令没有执行成功
                }
                return CommandResult.success();
            }
        }
        return CommandResult.success();
    }
}
