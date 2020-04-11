/*
 * Aleph, Advanced Discord bot
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

import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;
import static me.rizen.jda.bot.functions.ModerationFunctions.handleBan;
import static me.rizen.jda.bot.functions.PermissionFunctions.*;

public class BanCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final Guild guild = ctx.getGuild();
        final Member member = ctx.getMember();
        final TextChannel channel = ctx.getChannel();
        final List<Member> mentionedMembers = ctx.getMessage().getMentionedMembers();
        final Language language = ctx.getGuildLanguage();

        try {
            if (!isAdmin(member)) {
                sendMessage(channel, language.MEMBER_MISSING_PERMISSIONS());
                return;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (mentionedMembers.isEmpty()) {
            sendMessage(channel, language.MISSING_MENTION());
            return;
        }

        Member target = mentionedMembers.get(0);
        String reason = ctx.getArgs().size() > 0 ? String.join(" ", ctx.getArgs().subList(1, ctx.getArgs().size())) : language.NO_REASON_PROVIDED();

        try {
            if (isAdmin(target) && !isGuildOwner(guild, member) && isAdmin(member)) {
                sendMessage(channel, language.EQUAL_PERMISSIONS());
                return;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (!botCanInteract(ctx.getSelfMember(), target)) {
                    sendMessage(channel, language.BOT_CANNOT_INTERACT());
                    return;
                }
                if (!ctx.getSelfMember().hasPermission(Permission.BAN_MEMBERS)) {
                    sendMessage(channel, language.BOT_MISSING_PERMISSIONS());
                    return;
                }
                if (target.equals(member)) {
                    sendMessage(channel, language.MEMBER_BAN_SELF());
                }
        try {
            handleBan(channel, member.getUser(), target, reason);
            sendMessage(channel, language.SUCCESS_BAN());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_BAN();
    }


    @Override
    public String getName() {
        return "ban";
    }

    @Override
    public String getCategory() {
        return "Admin";
    }

    @Override
    public List<String> getAliases() {
        return List.of("b","banish");
    }
}
