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
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;

import static me.duncte123.botcommons.messaging.MessageUtils.sendSuccess;
import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;
import static me.rizen.jda.bot.functions.MusicFunctions.*;

public class JoinCommand implements ICommand {


    @Override
    public void handle(CommandContext ctx) {
        Guild guild = ctx.getGuild();
        TextChannel channel = ctx.getEvent().getChannel();
        Member member = ctx.getMember();
        AudioManager audioManager = ctx.getEvent().getGuild().getAudioManager();
        GuildVoiceState memberVoiceState = member.getVoiceState();
        VoiceChannel voiceChannel = memberVoiceState.getChannel();
        Member selfMember = ctx.getEvent().getGuild().getSelfMember();

        if (botInVoiceChannel(guild)) {
            sendMessage(channel, "I'm already connected  to a voice channel.");
            return;
        }

        if (!memberInVoiceChannel(member)) {
            sendMessage(channel, "You're not in a voice channel.");
            return;
        }

        if (!canJoinVoiceChannel(selfMember, voiceChannel)) {
            sendMessage(channel, "I can't join that voice channel!");
            return;
        }

        audioManager.openAudioConnection(voiceChannel);
        getAudioManager(guild).setSelfDeafened(true);
        sendSuccess(ctx.getMessage());

    }

    @Override
    public String getHelp() {
        return "Makes the bot join your channel";
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getName() {
        return "join";
    }

}
