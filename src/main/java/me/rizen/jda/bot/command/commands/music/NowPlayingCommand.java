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

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import static me.rizen.jda.bot.functions.MessageFunctions.createEmbed;
import static me.rizen.jda.bot.functions.MessageFunctions.sendEmbed;
import static me.rizen.jda.bot.functions.MiscFunctions.*;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.music.GuildMusicManager;
import me.rizen.jda.bot.music.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static me.rizen.jda.bot.functions.MusicFunctions.getPlayingTrack;

public class NowPlayingCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Guild guild = ctx.getGuild();
        TextChannel channel = ctx.getEvent().getChannel();
        Member member = ctx.getMember();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(ctx.getEvent().getGuild());
        AudioPlayer player = musicManager.player;

        if(player.getPlayingTrack() == null) {
            channel.sendMessage("No song is currently playing!").queue();
            return;
        }


        AudioTrackInfo info = getPlayingTrack(guild).getInfo();
            sendEmbed(channel, createEmbed(member)
                    .setTitle("Now playing.")
                    .setColor(randomColour())
                    .setDescription(String.format("**Playing** [%s] (%s)\n%s - `%s`:`%s`",
                            info.title,
                            info.uri,
                            player.isPaused() ? "\u23F8" : "▶",
                            formatTime(player.getPlayingTrack().getPosition()),
                            formatTime(player.getPlayingTrack().getDuration())))
                    .setThumbnail("https://i.ytimg.com/vi/"+player.getPlayingTrack().getInfo().identifier+"/hq720.jpg"));
    }


    @Override
    public List<String> getAliases() {
        return List.of("np");
    }


    @Override
    public String getHelp() {
        return "Displays the song that is currently playing\n" +
                "Usage: " + Config.getInstance().getString("prefix") + getName();
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getName() {
        return "nowplaying";
    }

    private String formatTime(long timeInMillis) {
        final long hours = timeInMillis / TimeUnit.HOURS.toMillis(1);
        final long minutes = timeInMillis / TimeUnit.MINUTES.toMillis(1);
        final long seconds = timeInMillis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}