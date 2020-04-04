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
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.music.GuildMusicManager;
import me.rizen.jda.bot.music.TrackScheduler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;
import static me.rizen.jda.bot.functions.MusicFunctions.*;

public class StopCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Guild guild = ctx.getGuild();
        GuildMusicManager musicManager = getGuildMusicManager(guild);
        AudioPlayer player = musicManager.player;
        TextChannel channel = ctx.getEvent().getChannel();
        TrackScheduler scheduler = getScheduler(guild);
        AudioManager audioManager = getAudioManager(guild);
        VoiceChannel voiceChannel = audioManager.getConnectedChannel();

        if(player.getPlayingTrack() == null) {
            channel.sendMessage("No song is currently playing!").queue();
            return;
        }

        if (!voiceChannel.getMembers().contains(ctx.getEvent().getMember())) {
            sendMessage(channel, "You have to be in the same voice channel as me to use this");
            return;
        }

        getQueue(guild).clear();
        musicManager.player.stopTrack();
        musicManager.player.setPaused(false);
        scheduler.setRepeating(false);

        sendMessage(channel, "Stopping the player and clearing the queue!");

    }

    @Override
    public String getHelp() {
        return "Stops the music" +
                "Usage: "+Config.getInstance().getString("prefix")+"stop";
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getName() {
        return "stop";
    }
}
