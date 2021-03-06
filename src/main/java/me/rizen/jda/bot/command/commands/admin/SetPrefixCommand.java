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
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static me.rizen.jda.bot.database.DatabaseFunctions.getDatabase;
import static me.rizen.jda.bot.functions.MessageFunctions.*;
import static me.rizen.jda.bot.functions.PermissionFunctions.isAdmin;
import static me.rizen.jda.bot.misc.Prefixes.PREFIXES;


public class SetPrefixCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final Member executor = ctx.getMember();
        final List<String> args = ctx.getArgs();
        final TextChannel channel = ctx.getChannel();
        final String guildId = ctx.getGuild().getId();
        final Language language = ctx.getGuildLanguage();

        try {
            if (!isAdmin(executor)) {
                sendMessage(channel, language.MEMBER_MISSING_PERMISSIONS());
                return;
            }
        if (args.isEmpty()) {
                sendMessage(channel, getHelp(guildId));
                return;
            }
            String prefix = String.join("", args);
            sendEmbed(channel, createEmbed(executor).setAuthor(language.SUCCESS_SUCCESS(), null, executor.getUser().getEffectiveAvatarUrl())
                    .setColor(Color.green).addField(language.SUCCESS_UPDATE_PREFIX(), language.NEW_PREFIX(), false));

                DocumentReference guildConfig = getDatabase().collection(ctx.getGuild().getId()).document("guildConfig");
                guildConfig.update("prefix", prefix);
                PREFIXES.replace(guildId, prefix);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_SETPREFIX();
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
