package com.mcrmb.sponge.command;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;

/**
 * Created by txgs888 on 2017/4/29.
 */
public interface CommandHandler {
    /**
     * 获取子命令名字
     *
     * @return 子命令名字
     */
    String getName();

    /**
     * 获取子命令介绍
     *
     * @return 介绍文本
     */
    String getDescribe();

    /**
     * 判断命令来源是否有该命令权限
     *
     * @param source 命令来源
     * @return 是否拥有权限
     */
    boolean hasPermission(CommandSource source);

    /**
     * 执行命令
     *
     * @param source  命令来源
     * @param context 命令参数
     * @return 是否执行成功
     */
    boolean execute(CommandSource source, CommandContext context);

}
