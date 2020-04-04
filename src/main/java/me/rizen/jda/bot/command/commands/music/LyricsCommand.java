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

package me.rizen.jda.bot.command.commands.music;

import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import java.util.List;

import static me.rizen.jda.bot.functions.APIFunctions.getLyrics;
import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;

public class LyricsCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        Member member = ctx.getMember();
        List<String> args = ctx.getArgs();

        if (args.isEmpty()) {
            sendMessage(channel, "Please provide a song name or artist.");
        }
        String text = args.toString().replace("[", "").replace("]", "").replace(",", "").replace("\\s", "%20");
        getLyrics(channel, member, text);
    }

    @Override
    public String getHelp() {
        return "Displays the first lines of the currently playing song's lyrics.\nUsage: "+Config.getInstance().getString("prefix")+"lyrics <Song Name>";
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getName() {
        return "lyrics";
    }
}
