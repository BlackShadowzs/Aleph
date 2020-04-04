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

import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import java.util.concurrent.TimeUnit;

import static me.rizen.jda.bot.functions.MiscFunctions.randomColour;

public class MessageFunctions {
    /**
     * Send text message
     * Example:
     * sendMessage(event.getChannel(), "Hello, world!");
     */
    public static void sendMessage (TextChannel channel, String message) {
        channel.sendMessage(message).queue();
    }

    /**
     * Send message embed
     * Example:
     * sendEmbed(event.getChannel(), Embed);
     */
    public static void sendEmbed (TextChannel channel, EmbedBuilder embed) {
        channel.sendMessage(embed.build()).queue();
    }

    /**
     * Create message embed
     * Example:
     * createEmbed().addField("Hi", "How are you?").build();
     */

    public static EmbedBuilder createEmbed (Member member) {

        return new EmbedBuilder()
                .setAuthor(member.getUser().getAsTag(), member.getUser().getAvatarUrl())
                .setColor(randomColour())
                .setFooter("Provided to you by "+Config.getInstance().getString("ownerTag"));
    }

    /**
     * Create message embed
     * Example:
     * sendMessageAndEdit(event.getChannel(), "my original message", "my new message", 5);
     */
    public static void sendMessageAndEdit (TextChannel channel, String originalMessage, String updatedMessage, Long updateDelayInSeconds) {
        channel.sendMessage(originalMessage).queue(
                (message) -> message.editMessage(updatedMessage).queueAfter(updateDelayInSeconds, TimeUnit.SECONDS)
        );
    }
}
