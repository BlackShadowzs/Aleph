/*
 * Aleph, Advanced Discord Bot
 *      Copyright (C) 2020 "R1zeN" Jonas Schiøtt
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.rizen.jda.bot.command.commands.owner

import me.rizen.jda.bot.command.CommandContext
import me.rizen.jda.bot.command.ICommand
import me.rizen.jda.bot.config.Config

import me.rizen.jda.bot.functions.MessageFunctions.sendMessage
import me.rizen.jda.bot.functions.PermissionFunctions.*

class DevCommand : ICommand {
    override fun handle(ctx: CommandContext) {
        val args = ctx.args
        val channel = ctx.channel
        val member = ctx.member
        val mentionedMembers = ctx.message.mentionedMembers
        if (member.id != Config.getInstance().getString("ownerID")) {
            sendMessage(channel, "This is an owner-only command.")
            return
        }
        if (mentionedMembers.isEmpty()) {
            sendMessage(channel, "You must mention a user!")
            return
        }
        val target = mentionedMembers[0]
        if (args[0] == "add") {
            addDev(target.id)
            sendMessage(channel, "Developer list now looks like this:\n```ini\n" + getDevs() + "```")
        } else if (args[0] == "rem") {
            removeDev(target.id)
            sendMessage(channel, "Developer list now looks like this:\n```ini\n" + getDevs() + "```")
        } else {
            sendMessage(channel, help)
        }


    }

    override fun getHelp(): String {
        return "Adds member to list of developers.\nUsage: " + Config.getInstance().getString("prefix") + "dev <add|rem> <@Member>\n[Owner Only]"
    }

    override fun getCategory(): String {
        return "Bot Owner"
    }

    override fun getName(): String {
        return "dev"
    }
}
