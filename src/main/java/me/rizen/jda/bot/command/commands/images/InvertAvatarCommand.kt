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

package me.rizen.jda.bot.command.commands.images

import me.rizen.jda.bot.command.CommandContext
import me.rizen.jda.bot.command.ICommand
import me.rizen.jda.bot.config.Config
import me.rizen.jda.bot.functions.APIFunctions.invertImage
import me.rizen.jda.bot.functions.MessageFunctions.sendMessage
import java.io.IOException

class InvertAvatarCommand : ICommand {
    override fun handle(ctx: CommandContext) {
        val channel = ctx.channel
        val message = ctx.message
        val author = ctx.author
        val target = if (!message.mentionedMembers.isEmpty()) message.mentionedMembers[0].user else author

        try {
            invertImage(channel, target)
        } catch (e: IOException) {
            sendMessage(channel, "Error occurred. If this keep on happening\nplease contact a bot-developer!")
        }

    }

    override fun getHelp(): String? {
        return "Returns your or another user's avatar with inverted colours.\n" +
                "Usage: "+ Config.getInstance().getString("prefix")+"invert [@Member]\nA mention isn't required!"
    }

    override fun getCategory(): String {
        return "Image"
    }

    override fun getName(): String {
        return "invert"
    }

}
