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

package me.rizen.jda.bot.command.commands.utility;

import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static me.rizen.jda.bot.functions.APIFunctions.postToHastebin;
import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;

public class PasteCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        if (ctx.getArgs().isEmpty()) {
            sendMessage(channel, "You must supply some text!");
            return;
        }

        final String name = this.getName();
        final String contentRaw = ctx.getMessage().getContentRaw();
        final int index = contentRaw.indexOf(name) + name.length() + 2;
        final String body = contentRaw.substring(index).trim();

        postToHastebin(body, (text) -> sendMessage(channel, text));
    }

    @Override
    public String getHelp() {
        return "Posts some text/code to Hastebin.\nUsage: "+ Config.getInstance().getString("prefix")+"paste <Text>";
    }

    @Override
    public String getName() {
        return "haste";
    }

    @Override
    public String getCategory() {
        return "Util";
    }

    @Override
    public List<String> getAliases() {
        return List.of("paste");
    }
}
