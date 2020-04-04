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
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static me.rizen.jda.bot.functions.PermissionFunctions.isAdmin;
import static me.rizen.jda.bot.database.DatabaseFunctions.getDatabase;
import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;

public class SetLogsCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        try {
            TextChannel channel = ctx.getChannel();
            Member member = ctx.getMember();
            final List<String> args = ctx.getArgs();
            final Message message = ctx.getMessage();
            DocumentReference guildConfig = getDatabase().collection(ctx.getGuild().getId()).document("guildConfig");
            if (!isAdmin(member)) {
                sendMessage(channel, "You must be an admin in order to run this command!");
                return;
            }

            if (args.isEmpty()) {
                guildConfig.update("logs", channel.getId());
                sendMessage(channel, "Successfully set the logs-channel to " + channel.getAsMention());
                return;
            }
            if (!message.getMentionedChannels().isEmpty()) {
                guildConfig.update("logs", message.getMentionedChannels().get(0).getId());
                sendMessage(channel, "Successfully set the logs-channel to " + message.getMentionedChannels().get(0).getAsMention());
                return;
            }

            guildConfig.update("logs", ctx.getArgs().get(0));
            sendMessage(channel, "Successfully set the logs-channel to <@" + ctx.getArgs().get(0)+">");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getHelp() {
        return "Sets the log-channel.\n"+ Config.getInstance().getString("prefix") +"setlogs (@TextChannel|ChannelId)\nDefault: your current channel";
    }

    @Override
    public String getCategory() {
        return "Admin";
    }

    @Override
    public String getName() {
        return "setlogs";
    }
}
