/*
 * Aleph, Advanced Discord Bot
 *      Copyright (C) 2020 "R1zeN" Jonas Schiøtt
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the free Software foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or fITNESS fOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.rizen.jda.bot.command.commands.utility

import me.rizen.jda.bot.functions.APIFunctions.yodanize
import me.rizen.jda.bot.functions.MessageFunctions.sendMessage
import me.rizen.jda.bot.command.CommandContext
import me.rizen.jda.bot.command.ICommand
import me.rizen.jda.bot.config.Config

class YodaCommand : ICommand {
    override fun handle(ctx: CommandContext?) {
        val args = ctx?.args!!
        val channel = ctx.channel
        if (args.isEmpty()) return sendMessage(channel, "Please put in a sentence for Yoda to say.")
        val text = args.toString().replace("[", "").replace("]", "").replace(",", "")

        yodanize(channel, text)
    }

    override fun getHelp(): String {
        return "Returns how Master Yoda would say the sentence.\nUsage: "+ Config.getInstance()["prefix"]+"yoda <Sentence to convert>"
    }

    override fun getCategory(): String {
        return "Util"
    }

    override fun getName(): String {
        return "yoda"
    }
}