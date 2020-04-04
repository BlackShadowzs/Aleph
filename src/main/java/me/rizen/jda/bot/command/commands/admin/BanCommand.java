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
import me.rizen.jda.bot.config.Config;
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
        Member member = ctx.getMember();
        TextChannel channel = ctx.getChannel();
        Guild guild = ctx.getGuild();
        List<Member> mentionedMembers = ctx.getMessage().getMentionedMembers();


        try {
            if (!isAdmin(member)) {
                sendMessage(channel, "You're missing the `BAN_MEMBERS` permission.");
                return;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (mentionedMembers.isEmpty()) {
            sendMessage(channel, "You must mention a member.");
            return;
        }

        Member target = mentionedMembers.get(0);
        String reason = ctx.getArgs().size() > 0 ? String.join(" ", ctx.getArgs().subList(1, ctx.getArgs().size())) : "No reason provided.";

        try {
            if (isAdmin(target) && !isGuildOwner(guild, member) && isAdmin(member)) {
                sendMessage(channel, "You can't ban other admins.");
                return;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (!botCanInteract(ctx.getSelfMember(), target)) {
                    sendMessage(channel, "I can't interact with them.");
                    return;
                }
                if (!ctx.getSelfMember().hasPermission(Permission.BAN_MEMBERS)) {
                    sendMessage(channel, "I am missing the `BAN_MEMBERS` permission.");
                    return;
                }
                if (target.equals(member)) {
                    sendMessage(channel, "Trying to ban yourself? Too bad, you can't.");
                }

        try {
            handleBan(channel, member.getUser(), target, reason);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getHelp() {
        return "Bans a member of the guild.\nUsage: "+ Config.getInstance().getString("prefix")+"ban <@Member> <Reason>";
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
