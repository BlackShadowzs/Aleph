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

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;
import static me.rizen.jda.bot.functions.MusicFunctions.getPlayingTrack;

public class SeekCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Guild guild = ctx.getGuild();
        TextChannel channel = ctx.getChannel();
        List<String> args = ctx.getArgs();
        AudioTrack playingTrack = getPlayingTrack(guild);
        final Language language = ctx.getGuildLanguage();
        if (args.isEmpty()) {
            sendMessage(channel, language.ERROR_NAN());
            return;
        }
        if (!args.toString().contains(":")) {
            sendMessage(channel, getHelp(guild.getId()));
            return;
        }
        String[] split = args.get(0).split(":");

        if (Double.isNaN(Double.parseDouble(split[0]))) {
            sendMessage(channel, language.ERROR_NAN());
            return;
        }
        double min = Double.parseDouble(split[0]) * 60;
        double secs = Double.parseDouble(split[1]) * 60;
        playingTrack.setPosition((long) (min+secs));
    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_SEEK();
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getName() {
        return "seek";
    }
}
