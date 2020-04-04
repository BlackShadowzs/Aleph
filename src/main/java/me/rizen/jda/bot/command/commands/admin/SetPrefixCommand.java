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

package me.rizen.jda.bot.command.commands.admin;

import com.google.cloud.firestore.DocumentReference;
import me.rizen.jda.bot.misc.Prefixes;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static me.rizen.jda.bot.database.DatabaseFunctions.getDatabase;
import static me.rizen.jda.bot.functions.MessageFunctions.*;
import static me.rizen.jda.bot.functions.PermissionFunctions.isAdmin;


public class SetPrefixCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
            Member executor = ctx.getMember();
            List<String> args = ctx.getArgs();
            TextChannel channel = ctx.getChannel();
            String guildId = ctx.getGuild().getId();
            try {
            if (!isAdmin(executor)) {
                sendMessage(channel, "You do not have permission to execute this command.");
                return;
            }
        if (args.isEmpty()) {
                sendMessage(channel, "You must supply a prefix.");
                return;
            }
            String prefix = String.join("", args);
            sendEmbed(channel, createEmbed(executor).setAuthor("Success!", null, executor.getUser().getEffectiveAvatarUrl())
                    .setColor(Color.green).addField("Prefix has been updated.", "New prefix: `"+prefix+"`", false));

                DocumentReference guildConfig = getDatabase().collection(ctx.getGuild().getId()).document("guildConfig");
                guildConfig.update("prefix", prefix);
                Prefixes.PREFIXES.replace(guildId, prefix);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String getName() {
        return "setprefix";
    }

    @Override
    public String getCategory() {
        return "Admin";
    }

    @Override
    public List<String> getAliases() {
        return List.of("prefix", "editprefix", "changeprefix");
    }

}
