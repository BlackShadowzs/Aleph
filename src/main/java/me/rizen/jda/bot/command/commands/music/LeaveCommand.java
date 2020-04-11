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
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
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

        final Language language = ctx.getGuildLanguage();

        if (!botInVoiceChannel(guild)) {
            sendMessage(channel,language.ERROR_BOT_NOT_IN_VOICE_CHANNEL());
            return;
        }

        if (!inSameVoiceChannel(guild, member)) {
            sendMessage(channel, language.ERROR_NOT_IN_SAME_VOICE_CHANNEL());
            return;
        }

        audioManager.closeAudioConnection();
        sendMessage(channel, language.SUCCESS_LEAVE_VOICE_CHANNEL());
        sendSuccess(ctx.getMessage());

        scheduler.setRepeating(false);
        queue.clear();
    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_LEAVE();
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
