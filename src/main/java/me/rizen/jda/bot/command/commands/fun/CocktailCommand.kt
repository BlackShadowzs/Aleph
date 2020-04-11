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

package me.rizen.jda.bot.command.commands.`fun`

import me.rizen.jda.bot.command.CommandContext
import me.rizen.jda.bot.command.ICommand
import me.rizen.jda.bot.config.Config
import me.rizen.jda.bot.functions.APIFunctions.getRandomCocktail
import me.rizen.jda.bot.functions.APIFunctions.searchCocktail
import me.rizen.jda.bot.misc.GuildLanguage

class CocktailCommand : ICommand {
    override fun handle(ctx: CommandContext?) {
        val member = ctx?.member
        val channel = ctx?.channel
        val args = ctx?.args!!

        if (args.isEmpty()) return getRandomCocktail(channel, member)
        val cocktailName = args.subList(0, args.size).toString().replace("[", "").replace("]", "")
                .replace(",", "").replace("\\s", "%20")
        searchCocktail(channel, member, cocktailName)
    }

    override fun getHelp(guildId: String): String? {
        return GuildLanguage.GuildLanguage[guildId]!!.COMMAND_HELP_COCKTAIL()
    }

    override fun getCategory(): String {
        return "Fun"
    }

    override fun getName(): String {
        return "cocktail"
    }

}
