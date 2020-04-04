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
import me.rizen.jda.bot.music.TrackScheduler;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import static me.duncte123.botcommons.messaging.MessageUtils.sendSuccess;
import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;
import static me.rizen.jda.bot.functions.MusicFunctions.*;

public class LeaveCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Guild guild = ctx.getGuild();
        TextChannel channel = ctx.getEvent().getChannel();
        Member member = ctx.getMember();
        AudioManager audioManager = ctx.getEvent().getGuild().getAudioManager();

        BlockingQueue<AudioTrack> queue = getQueue(guild);
        TrackScheduler scheduler = getScheduler(guild);


        if (!botInVoiceChannel(guild)) {
            sendMessage(channel,"I'm not connected to a voice channel");
            return;
        }

        if (!inSameVoiceChannel(guild, member)) {
            sendMessage(channel, "You have to be in the same voice channel as me to use this");
            return;
        }


        if (!inSameVoiceChannel(guild, member)) {
            sendMessage(channel, "You have to be in the same voice channel as me to use this");
            return;
        }

        audioManager.closeAudioConnection();
        sendMessage(channel, "I've left your voice channel!");
        sendSuccess(ctx.getMessage());

        scheduler.setRepeating(false);
        queue.clear();
    }

    @Override
    public String getHelp() {
        return "Makes the bot leave your channel";
    }


    @Override
    public List<String> getAliases() {
        return List.of("getout");
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getName() {
        return "leave";
    }
}
