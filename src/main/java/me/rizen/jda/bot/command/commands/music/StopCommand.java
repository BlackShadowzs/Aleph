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
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
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
        final Language language = ctx.getGuildLanguage();

        if(player.getPlayingTrack() == null) {
            channel.sendMessage(language.ERROR_NOT_PLAYING()).queue();
            return;
        }

        if (!voiceChannel.getMembers().contains(ctx.getEvent().getMember())) {
            sendMessage(channel, language.ERROR_NOT_IN_SAME_VOICE_CHANNEL());
            return;
        }

        getQueue(guild).clear();
        musicManager.player.stopTrack();
        musicManager.player.setPaused(false);
        scheduler.setRepeating(false);

        sendMessage(channel, language.SUCCESS_STOPPED_SONG());

    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_STOP();
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
