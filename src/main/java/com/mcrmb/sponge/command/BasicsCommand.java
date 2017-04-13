package com.mcrmb.sponge.command;

import com.mcrmb.sponge.McrmbCoreMain;
import com.mcrmb.sponge.mcrmb.McrmbPluginInfo;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

/**
 * Created by txgs888 on 2017/4/13.
 */
public class BasicsCommand implements CommandExecutor {
    /***
     *
     * @param source 命令发起者
     * @param context https://docs.spongepowered.org/stable/zh-CN/plugin/commands/arguments.html?highlight=commandcontext
     * @return
     * @throws CommandException
     */
    @Override
    public CommandResult execute(CommandSource source, CommandContext context) throws CommandException {
        return CommandResult.success();
    }
}
