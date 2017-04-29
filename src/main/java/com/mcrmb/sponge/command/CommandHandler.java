package com.mcrmb.sponge.command;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;

/**
 * Created by txgs888 on 2017/4/29.
 */
public interface CommandHandler {
    String getName();

    String getDescribe();

    boolean hasPermission(CommandSource source);

    boolean execute(CommandSource source, CommandContext context);

}
