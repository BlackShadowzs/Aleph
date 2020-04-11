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

import me.rizen.jda.bot.database.CaseObject;
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.concurrent.ExecutionException;

import static me.rizen.jda.bot.database.DatabaseFunctions.createCase;
import static me.rizen.jda.bot.functions.MessageFunctions.createEmbed;
import static me.rizen.jda.bot.functions.MessageFunctions.sendEmbed;
import static me.rizen.jda.bot.functions.MiscFunctions.getLogs;
import static me.rizen.jda.bot.functions.MiscFunctions.getPrefix;
import static me.rizen.jda.bot.functions.RandomNumber.generateRandomString;

public class ModerationFunctions {
    public static void handleBan (TextChannel eventChannel, User admin, Member target, String reason) throws ExecutionException, InterruptedException {
        final Language language = GuildLanguage.GuildLanguage.get(eventChannel.getGuild().getId());
        String s = generateRandomString(7);
        CaseObject caseObject = new CaseObject(target.getUser().getAsTag(), target.getId(), admin.getAsTag(), admin.getId(),reason, "Ban");
        createCase(eventChannel.getGuild().getId(), "Case: "+s, caseObject);

        final String logs = getLogs(eventChannel.getGuild().getId());

        EmbedBuilder embed = createEmbed(target).setAuthor(language.BAN_ACTION(), null,
                "https://toppng.com/uploads/preview/they-won-the-ban-hammer-contest-second-place-was-eldritch-emojis-para-discord-ba-11563216647xxwraoqmuj.png")
                .setColor(Color.red)
                .addField(language.PUNISHER(), admin.getAsTag(), true)
                .addField(language.TARGET(), target.getUser().getAsTag(), true)
                .setFooter(language.CASE_ID()+s);

        TextChannel logsChannel = eventChannel.getGuild().getTextChannelById(logs);

        if (logsChannel != null) {
            target.ban(7, reason).queue();
            sendEmbed(logsChannel, embed);
        } else {
            target.ban(7, reason).queue();
            sendEmbed(eventChannel, embed.addField(language.LOGS_NOT_SET_FIELD_NAME(),
                    language.LOGS_NOT_SET_FIELD_VALUE(), false));
        }
    }
    public static void handleKick (TextChannel eventChannel, User admin, Member target, String reason) throws ExecutionException, InterruptedException {
        final Language language = GuildLanguage.GuildLanguage.get(eventChannel.getGuild().getId());
        String s = generateRandomString(7);
        CaseObject caseObject = new CaseObject(target.getUser().getAsTag(), target.getId(), admin.getAsTag(), admin.getId(),reason, "Ban");
        createCase(eventChannel.getGuild().getId(), "Case: "+s, caseObject);

        final String logs = getLogs(eventChannel.getGuild().getId());
        EmbedBuilder embed = createEmbed(target).setAuthor(language.KICK_ACTION(), null,
                "https://media4.giphy.com/avatars/mikemaese/A0EW3SwjHwHX.gif")
                .setColor(Color.red)
                .addField(language.PUNISHER(), admin.getAsTag(), true)
                .addField(language.TARGET(), target.getUser().getAsTag(), true)
                .setFooter(language.CASE_ID()+s);

        TextChannel logsChannel = eventChannel.getGuild().getTextChannelById(logs);

        if (logsChannel != null) {
            target.kick(reason).queue();
            sendEmbed(logsChannel, embed);
        } else {
            target.kick(reason).queue();
            sendEmbed(eventChannel, embed.addField(language.LOGS_NOT_SET_FIELD_NAME(),
                    language.LOGS_NOT_SET_FIELD_VALUE(), false));
        }
    }
}