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

package me.rizen.jda.bot.command.commands.utility

import me.rizen.jda.bot.functions.MessageFunctions.sendMessage
import me.rizen.jda.bot.command.CommandContext
import me.rizen.jda.bot.command.ICommand
import me.rizen.jda.bot.config.Config
import me.rizen.jda.bot.languages.Language
import me.rizen.jda.bot.languages.en_GB
import me.rizen.jda.bot.misc.GuildLanguage

class PingCommand : ICommand {
    override fun handle(ctx: CommandContext?) {
        val pingMsg = GuildLanguage.GuildLanguage[ctx?.guild?.id]?.SUCCESS_PING()
        val channel = ctx?.channel
        val ping = ctx?.jda?.gatewayPing
        sendMessage(channel,  pingMsg +" `"+ping+"ms`")
    }

    override fun getHelp(guildId: String?): String? {
        return GuildLanguage.GuildLanguage[guildId]!!.COMMAND_HELP_PING()
    }

    override fun getName(): String {
        return "ping"
    }

    override fun getCategory(): String {
        return "Util"
    }

    override fun getAliases(): List<String> {
        return listOf("pong")
    }
}