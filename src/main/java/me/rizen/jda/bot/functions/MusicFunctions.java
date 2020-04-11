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

package me.rizen.jda.bot.functions;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import me.rizen.jda.bot.music.GuildMusicManager;
import me.rizen.jda.bot.music.PlayerManager;
import me.rizen.jda.bot.music.TrackScheduler;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.BlockingQueue;

import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;

public class MusicFunctions {
    public static boolean inSameVoiceChannel (Guild guild, Member member) {
        AudioManager audioManager = guild.getAudioManager();
        VoiceChannel voiceChannel = audioManager.getConnectedChannel();

        return voiceChannel.getMembers().contains(member);

    }
    public static boolean isPlaying(Guild guild) {
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild);
        AudioPlayer player = musicManager.player;

        return player.getPlayingTrack() != null;
    }
    public static void forwardSong(Guild guild, TextChannel channel, @Nullable String timeToForward) {
        final Language language = GuildLanguage.GuildLanguage.get(guild.getId());
        int seconds = 10;
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild);
        AudioPlayer player = musicManager.player;
        AudioTrack track = player.getPlayingTrack();
        if(timeToForward != null) {
            seconds = Integer.parseInt(timeToForward);
        } else {
            try {
                seconds = Integer.parseInt(String.valueOf(timeToForward));
                if(seconds == 0 || Double.isNaN(seconds)) {
                    throw new NumberFormatException();
                }
            } catch(NumberFormatException e) {
                sendMessage(channel, language.ERROR_NAN());
            }
        }
        track.setPosition(track.getPosition() + (1000*seconds)); // Lavaplayer handles values < 0 or > track length
        sendMessage(channel, language.SUCCESS_FORWARD().replace("REPLACE", String.valueOf(seconds)));

    }

    public static boolean botInVoiceChannel(Guild guild) {
        AudioManager audioManager = guild.getAudioManager();

        return audioManager.isConnected();
    }

    public static boolean memberInVoiceChannel(Member member) {
        GuildVoiceState memberVoiceState = member.getVoiceState();

        return memberVoiceState.inVoiceChannel();
    }

    public static boolean canJoinVoiceChannel (Member member, VoiceChannel voiceChannel) {
        return member.hasPermission(voiceChannel, Permission.VOICE_CONNECT);
    }

    public static BlockingQueue<AudioTrack> getQueue(Guild guild) {
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild);

        return musicManager.scheduler.getQueue();
    }

    public static TrackScheduler getScheduler (Guild guild) {
        PlayerManager playerManager = PlayerManager.getInstance();
        return playerManager.getGuildMusicManager(guild).scheduler;
    }

    public static AudioTrack getPlayingTrack(Guild guild) {
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild);
        AudioPlayer player = musicManager.player;
        return player.getPlayingTrack();
    }

    public static AudioPlayer getPlayer(Guild guild) {
        PlayerManager playerManager = PlayerManager.getInstance();
        return playerManager.getGuildMusicManager(guild).player;
    }

    public static AudioManager getAudioManager (Guild guild) {
        return guild.getAudioManager();
    }

    public static GuildMusicManager getGuildMusicManager (Guild guild) {
        PlayerManager manager = PlayerManager.getInstance();
        return manager.getGuildMusicManager(guild);
    }
    public static void rewindSong(Guild guild, TextChannel channel, @Nullable String timeToForward) {
        final Language language = GuildLanguage.GuildLanguage.get(guild.getId());
        int seconds = 10;
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(guild);
        AudioPlayer player = musicManager.player;
        AudioTrack track = player.getPlayingTrack();
        if(timeToForward != null) {
            seconds = Integer.parseInt(timeToForward);
        } else {
            try {
                seconds = Integer.parseInt(String.valueOf(timeToForward));
                if(seconds == 0 || Double.isNaN(seconds)) {
                    throw new NumberFormatException();
                }
            } catch(NumberFormatException e) {
                sendMessage(channel, language.ERROR_NAN());
            }
        }
        track.setPosition(track.getPosition() - (1000*seconds)); // Lavaplayer handles values < 0 or > track length
        sendMessage(channel, language.SUCCESS_REWIND().replace("REPLACE", String.valueOf(seconds)));
    }

    public static PlayerManager getPlayerManager() {
        return PlayerManager.getInstance();
    }
}
