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
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
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
            final TextChannel channel = ctx.getChannel();
            final Member member = ctx.getMember();
            final List<String> args = ctx.getArgs();
            final Message message = ctx.getMessage();
            final Language language = ctx.getGuildLanguage();

            DocumentReference guildConfig = getDatabase().collection(ctx.getGuild().getId()).document("guildConfig");
            if (!isAdmin(member)) {
                sendMessage(channel, language.MEMBER_MISSING_PERMISSIONS());
                return;
            }

            if (args.isEmpty()) {
                guildConfig.update("logs", channel.getId());
                sendMessage(channel, language.SUCCESS_SET_LOGS() + channel.getAsMention());
                return;
            }

            if (!message.getMentionedChannels().isEmpty()) {
                guildConfig.update("logs", message.getMentionedChannels().get(0).getId());
                sendMessage(channel, language.SUCCESS_SET_LOGS() + message.getMentionedChannels().get(0).getAsMention());
                return;
            }

            guildConfig.update("logs", ctx.getArgs().get(0));
            sendMessage(channel, language.SUCCESS_SET_LOGS()+" <@" + ctx.getArgs().get(0)+">");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_SETLOGS();
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
