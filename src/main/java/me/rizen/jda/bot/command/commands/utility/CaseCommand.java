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

package me.rizen.jda.bot.command.commands.utility;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static me.rizen.jda.bot.database.DatabaseFunctions.getDatabase;
import static me.rizen.jda.bot.functions.MessageFunctions.*;
import static me.rizen.jda.bot.functions.MiscFunctions.randomColour;

public class CaseCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Guild guild = ctx.getGuild();
        TextChannel channel = ctx.getChannel();
        List<String> args = ctx.getArgs();
        Firestore database = getDatabase();
        final Language language = ctx.getGuildLanguage();

        if (args.isEmpty()) {
            sendMessage(channel, getHelp(guild.getId()));
            return;
        }

        List<String> cases = new ArrayList<>();
        EmbedBuilder embed = createEmbed(ctx.getMember())
                .setAuthor(language.CASE_LIST())
                .setColor(randomColour());


        if (args.get(0).equalsIgnoreCase("list")) {
            database.collection(guild.getId()).listDocuments().forEach(
                    (doc) -> {
                        if (doc.getId().startsWith(language.CASE())) {
                            cases.add(doc.getId().replace("Case: ", ""));
                        }
                    }
            );
                    embed.appendDescription("```yaml\n");
            cases.forEach(
                    (caseList) -> {
                        if (cases.isEmpty()) {
                            embed.setDescription(language.NO_CASES_REGISTERED_IN_GUILD());
                        }
                        embed.appendDescription(caseList+"\n");
            }
            );
            embed.appendDescription("```");
            sendEmbed(channel, embed);
            return;

        }

        DocumentReference document = database.collection(guild.getId()).document("Case: "+args.get(0));

        try {
            if (!document.get().get().exists()) {
                sendMessage(channel, language.CASE_NOT_FOUND());
                return;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        try {
            sendEmbed(channel, createEmbed(ctx.getMember())
            .setColor(Color.ORANGE)
            .setAuthor(language.CASE_INFO(), null, ctx.getAuthor().getEffectiveAvatarUrl())
            .addField(language.PUNISHER(), document.get().get().getString("punisherName")+"\n"+document.get().get().getString(("punisherId")), true)
            .addField(language.TARGET(), document.get().get().getString("targetName")+"\n"+document.get().get().getString("targetId"), true)
            .addField(language.ACTION_TYPE(), document.get().get().getString("action"), true)
            .setDescription("```ini\n"+language.REASON_FOR_ACTION()+"\n"+document.get().get().getString("reasonForWarn")+"```"));
        } catch (InterruptedException | ExecutionException e) {
            sendMessage(channel, language.BOT_ERROR());
            e.printStackTrace();
        }
    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_CASE();
    }

    @Override
    public String getCategory() {
        return "Util";
    }

    @Override
    public String getName() {
        return "case";
    }

    @Override
    public List<String> getAliases() {
        return List.of("caseinfo", "ci", "cases", "caselist");
    }
}
