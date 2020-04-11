/*
 * Aleph, Advanced Discord bot
 *      Copyright (C) 2020 "R1zeN" Jonas Schiøtt
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the free Software foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTAbILITY or fITNESS fOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.rizen.jda.bot.command.commands.utility

import me.rizen.jda.bot.functions.APIFunctions.checkWebsiteState
import me.rizen.jda.bot.command.CommandContext
import me.rizen.jda.bot.command.ICommand
import me.rizen.jda.bot.misc.GuildLanguage

class WebsiteStatusCommand : ICommand {
    override fun handle(ctx: CommandContext?) {
        checkWebsiteState(ctx?.channel, ctx?.member, ctx?.args?.get(0))
    }

    override fun getHelp(guildId: String?): String? {
        return GuildLanguage.GuildLanguage[guildId]!!.COMMAND_HELP_CHECKWEB()
    }

    override fun getCategory(): String {
        return "Util"
    }

    override fun getName(): String {
        return "checkweb"
    }
}