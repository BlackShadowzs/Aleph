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
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.List;

import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;
import static me.rizen.jda.bot.functions.MusicFunctions.*;

public class SkipCommand implements ICommand {
    private int votes;

    public int getVotes() {
        return votes;
    }



    public void setVotes(int votes) {
        this.votes = votes;
    }

    ArrayList<String> al=new ArrayList<String>();

    @Override
    public void handle(CommandContext ctx) {
        Guild guild = ctx.getGuild();
        Member member = ctx.getMember();
        TextChannel channel = ctx.getEvent().getChannel();
        AudioPlayer player = getPlayer(guild);
        AudioTrack track = player.getPlayingTrack();
        final Language language = ctx.getGuildLanguage();

        if(getPlayingTrack(guild) == null) {
            channel.sendMessage(language.ERROR_NOT_PLAYING()).queue();
            return;
        }

        if (!inSameVoiceChannel(guild, member)) {
            sendMessage(channel, language.ERROR_NOT_IN_SAME_VOICE_CHANNEL());
            return;
        }

        int members = member.getVoiceState().getChannel().getMembers().size()-1; //Minus the bot
        int amtMembersNeededToVote = members / 2;

        if (members == 1) {
            track.setPosition(track.getDuration());
            sendMessage(channel, language.SUCCESS_SKIP());
            return;
        }

        if (al.contains(member.getId())) {
            sendMessage(channel, language.ERROR_ALREADY_VOTE_SKIPPED());
            return;
        }

        setVotes(getVotes()+1);
        al.add(member.getId());



        channel.sendMessage(language.SUCCESS_VOTE_SKIP()+"\n`" + getVotes() + "/" + amtMembersNeededToVote).queue();
        System.out.print(al);
        if (getVotes() == amtMembersNeededToVote) {
            track.setPosition(track.getDuration());
            sendMessage(channel, language.SUCCESS_SKIP());
            setVotes(0);
            al.clear();
        }
    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_SKIP();
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public List<String> getAliases() {
        return List.of("skip", "next", "voteskip");
    }

    @Override
    public String getName() {
        return "skip";
    }
}
