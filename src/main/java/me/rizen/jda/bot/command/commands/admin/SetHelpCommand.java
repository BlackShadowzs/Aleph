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

import com.google.cloud.firestore.DocumentSnapshot;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static me.rizen.jda.bot.database.DatabaseFunctions.getCustomCommand;
import static me.rizen.jda.bot.functions.MessageFunctions.*;
import static me.rizen.jda.bot.functions.MiscFunctions.randomColour;
import static me.rizen.jda.bot.functions.PermissionFunctions.isAdmin;

public class SetHelpCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        Guild guild = ctx.getGuild();
        Member member = ctx.getMember();
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();
        final Language language = ctx.getGuildLanguage();

        try {
            if (!isAdmin(member)) {
                sendMessage(channel, language.MEMBER_MISSING_PERMISSIONS());
                return;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (args.size() < 2) {
            sendMessage(channel, getHelp(guild.getId()));
            return;
        }
        try {
            DocumentSnapshot commandDoc = getCustomCommand(guild, args.get(0));
            if (!commandDoc.exists()) {
                sendMessage(channel, language.COMMAND_DOES_NOT_EXIST_OR_BUILT_IN_BOT());
                return;
            }
            String newHelp = args.subList(1, args.size()).toString().replace("[", "").replace("]", "").replace(",", "");
            commandDoc.getReference().update("help", newHelp);
            EmbedBuilder builder = createEmbed(member)
                    .setAuthor(language.SUCCESS_SUCCESS(), null, member.getUser().getEffectiveAvatarUrl())
                    .setColor(randomColour())
                    .addField(language.SUCCESS_SET_HELP_EMBED_FIELD_NAME()+args.get(0)+" to:", newHelp, false);
            sendEmbed(channel, builder);
        } catch (Exception e) {
            sendMessage(channel, language.BOT_ERROR());
        }

    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_SETHELP();
    }

    @Override
    public String getCategory() {
        return "Admin";
    }

    @Override
    public String getName() {
        return "sethelp";
    }
}
