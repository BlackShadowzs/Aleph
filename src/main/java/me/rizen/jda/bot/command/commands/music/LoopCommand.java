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

import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.music.TrackScheduler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;
import static me.rizen.jda.bot.functions.MusicFunctions.*;

public class LoopCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Guild guild = ctx.getGuild();
        Member member = ctx.getMember();
        TextChannel channel = ctx.getEvent().getChannel();
        AudioTrackInfo info = getPlayingTrack(guild).getInfo();

        TrackScheduler scheduler = getScheduler(guild);


        if (!inSameVoiceChannel(guild, member)) {
            channel.sendMessage("You have to be in the same voice channel as me to use this").queue();
            return;
        }

        String action = scheduler.isRepeating() ? "Stopped" : "Now";
        boolean newRepeatingState = !scheduler.isRepeating();

        if (!scheduler.isRepeating()) {
            scheduler.setRepeating(newRepeatingState);
            sendMessage(channel, String.format(action + " looping ``%s``", info.title));
        }
    }

    @Override
    public String getHelp() {
        return "Toggles the looping state of the currently playing track.\nUsage: "+ Config.getInstance().getString("prefix") +"loop";
    }


    @Override
    public List<String> getAliases() {
        return List.of("repeat");
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getName() {
        return "loop";
    }
}
