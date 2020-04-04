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

        if (args.isEmpty()) {
            sendMessage(channel, getHelp());
            return;
        }

        List<String> cases = new ArrayList<>();
        EmbedBuilder embed = createEmbed(ctx.getMember())
                .setAuthor("Case List")
                .setColor(randomColour());


        if (args.get(0).equalsIgnoreCase("list")) {
            database.collection(guild.getId()).listDocuments().forEach(
                    (doc) -> {
                        if (doc.getId().startsWith("Case:")) {
                            cases.add(doc.getId().replace("Case: ", ""));
                        }
                    }
            );
                    embed.appendDescription("```yaml\n");
            cases.forEach(
                    (caseList) -> {
                        if (cases.isEmpty()) {
                            embed.setDescription("No cases registered in this guild.");
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
                sendMessage(channel, "This case isn't registered.\nDo `"+Config.getInstance().getString("prefix")+"case list` to get a list of cases.");
                return;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        try {
            sendEmbed(channel, createEmbed(ctx.getMember())
            .setColor(Color.ORANGE)
            .setAuthor("Case Info", null, ctx.getAuthor().getEffectiveAvatarUrl())
            .addField("Punisher", document.get().get().getString("punisherName")+"\n"+document.get().get().getString(("punisherId")), true)
            .addField("Target", document.get().get().getString("targetName")+"\n"+document.get().get().getString("targetId"), true)
            .addField("Action type", document.get().get().getString("action"), true)
            .setDescription("```ini\nReason for action:\n"+document.get().get().getString("reasonForWarn")+"```"));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getHelp() {
        return "Displays info on a case or sends a list of cases.\nUsage: "+ Config.getInstance().getString("prefix")+"case <Case ID|List>";
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
