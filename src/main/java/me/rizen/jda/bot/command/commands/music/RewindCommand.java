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
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;
import static me.rizen.jda.bot.functions.MusicFunctions.*;

public class RewindCommand implements ICommand
{
    @Override
    public void handle(CommandContext ctx) {
        Guild guild = ctx.getGuild();
        TextChannel channel = ctx.getEvent().getChannel();
        Member member = ctx.getMember();
        List<String> args = ctx.getArgs();

        if (!isPlaying(guild)) {
            sendMessage(channel, "I'm currently not playing in this guild.");
            return;
        }

        if (!inSameVoiceChannel(guild, member)) {
            sendMessage(channel, "You're not in my voice channel.");
            return;
        }

        rewindSong(guild, channel, args.get(0));
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getHelp() {
        return String.format("Forwards into the song\nUsage: %srewind [seconds] (If no seconds given seconds=10)", Config.getInstance().getString("prefix"));
    }

    @Override
    public String getName() {
        return "rewind";
    }
}
