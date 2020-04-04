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

import me.rizen.jda.bot.functions.APIFunctions.htmlToPdf
import me.rizen.jda.bot.functions.MessageFunctions.sendMessage
import me.rizen.jda.bot.command.CommandContext
import me.rizen.jda.bot.command.ICommand
import me.rizen.jda.bot.config.Config

class HtmlToPdfCommand : ICommand {
    override fun handle(ctx: CommandContext?) {
        val channel = ctx?.channel
        val args = ctx?.args

        if (args?.isEmpty()!!) return sendMessage(channel, help)
        val html = args.subList(0, args.size).toString().replace("[", "").replace("]", "").replace(",", "")
        if (!html.startsWith("<")) return sendMessage(channel, "You must supply proper HTML!")
        htmlToPdf(channel, html)
    }

    override fun getHelp(): String {
        return "Turns HTML \"code\" into PDF.\nUsage: "+Config.getInstance().getString("prefix")+"htmltopdf <HTML>\nExample: "+Config.getInstance().getString("prefix")+"htmltopdf <h1>Hello</h1>"
    }

    override fun getCategory(): String {
        return "Util"
    }

    override fun getName(): String {
        return "htmltopdf"
    }
}