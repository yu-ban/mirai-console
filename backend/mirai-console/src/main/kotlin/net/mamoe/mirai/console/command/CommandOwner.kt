/*
 * Copyright 2019-2020 Mamoe Technologies and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AFFERO GENERAL PUBLIC LICENSE version 3 license that can be found via the following link.
 *
 * https://github.com/mamoe/mirai/blob/master/LICENSE
 */

package net.mamoe.mirai.console.command

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregisterAllCommands
import net.mamoe.mirai.console.permission.*
import net.mamoe.mirai.console.plugin.jvm.JvmPlugin

/**
 * 指令的所有者. 目前仅作为标识作用.
 *
 * @see CommandManager.unregisterAllCommands 取消注册所有属于一个 [CommandOwner] 的指令
 * @see CommandManager.registeredCommands 获取已经注册了的属于一个 [CommandOwner] 的指令列表.
 *
 * @see JvmPlugin 是一个 [CommandOwner]
 */
@OptIn(ExperimentalPermission::class)
public interface CommandOwner : PermissionIdNamespace {
    /**
     * 此 [PermissionIdNamespace] 拥有的指令都默认将 [parentPermission] 作为父权限.
     *
     * TODO document
     */
    @ExperimentalPermission
    public val parentPermission: Permission
}

/**
 * 代表控制台所有者. 所有的 mirai-console 内建的指令都属于 [ConsoleCommandOwner].
 */
internal object ConsoleCommandOwner : CommandOwner {
    @ExperimentalPermission
    override val parentPermission: Permission
        get() = RootPermission

    @ExperimentalPermission
    override fun permissionId(id: String): PermissionId = PermissionId("console", id)
}