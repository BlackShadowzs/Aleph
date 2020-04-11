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
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import static me.rizen.jda.bot.functions.MessageFunctions.sendEmbed;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import static me.rizen.jda.bot.functions.MessageFunctions.createEmbed;
import static me.rizen.jda.bot.functions.MiscFunctions.randomColour;
import static me.rizen.jda.bot.functions.MusicFunctions.getQueue;

public class QueueCommand implements ICommand {
     @Override
    public void handle(CommandContext ctx) {
         Guild guild = ctx.getGuild();
         TextChannel channel = ctx.getEvent().getChannel();
         Member member = ctx.getMember();
         BlockingQueue<AudioTrack> queue = getQueue(guild);
         final Language language = ctx.getGuildLanguage();
         if (queue.isEmpty()) {
             channel.sendMessage(language.QUEUE_IS_EMPTY()).queue();

             return;
         }

         if (ctx.getArgs().toString().toLowerCase().contains("clear")) {
             queue.clear();
             channel.sendMessage(language.QUEUE_WAS_CLEARED()).queue();
             return;
         }

         int trackCount = Math.min(queue.size(), 20);
         List<AudioTrack> tracks = new ArrayList<>(queue);
         EmbedBuilder builder = createEmbed(member)
                 .setTitle(language.SONG_QUEUE().replace("REPLACE", String.valueOf(queue.size())))
                 .setColor(randomColour());

         for (int i = 0; i < trackCount; i++) {
             AudioTrack track = tracks.get(i);
             AudioTrackInfo info = track.getInfo();

             builder.appendDescription(String.format(
                     "`%s` by %s\n",
                     info.title,
                     info.author
             ));
         }
        sendEmbed(channel, builder);
    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_QUEUE();
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public List<String> getAliases() {
        return List.of("q");
    }


    @Override
    public String getName() {
        return "queue";
    }
}
