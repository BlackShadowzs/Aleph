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

package me.rizen.jda.bot.command.commands.moderator;

import me.rizen.jda.bot.functions.ModerationFunctions;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;
import static me.rizen.jda.bot.functions.ModerationFunctions.handleKick;
import static me.rizen.jda.bot.functions.PermissionFunctions.botCanInteract;
import static me.rizen.jda.bot.functions.PermissionFunctions.isMod;

public class KickCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        try {
            ModerationFunctions moderationFunctions = new ModerationFunctions();
            Member member = ctx.getMember();
            TextChannel channel = ctx.getChannel();
            Member selfMember = ctx.getSelfMember();
            List<Member> mentionedMembers = ctx.getMessage().getMentionedMembers();
            if (!isMod(member)) {
                sendMessage(channel, "You're missing the `KICK_MEMBERS` permission.");
                return;
            }

            if (mentionedMembers.isEmpty()) {
                sendMessage(channel, "You must mention a member.");
                return;
            }

            Member target = mentionedMembers.get(0);
            String reason = ctx.getArgs().size() > 0 ? String.join(" ", ctx.getArgs().subList(1, ctx.getArgs().size())) : "No reason provided.";

            if (!isMod(selfMember)) {
                sendMessage(channel, "I am missing the `KICK_MEMBERS` permission.");
                return;
            }

            if (!botCanInteract(selfMember, target)) {
                sendMessage(channel, "I can't interact with them.");
                return;
            }
            if (!selfMember.hasPermission(Permission.BAN_MEMBERS)) {
                sendMessage(channel, "I am missing the `KICK_MEMBERS` permission.");
                return;
            }
            if (target.equals(member)) {
                sendMessage(channel, "Trying to kick yourself? Too bad, you can't.");
                return;
            }
            handleKick(channel, member.getUser(), target, reason);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getHelp() {
        return "Kicks a member.\n"
                + Config.getInstance().getString("prefix") +"kick <@Member|UserID>";
    }

    @Override
    public String getCategory() {
        return "Mod";
    }

    @Override
    public String getName() {
        return "kick";
    }

    @Override
    public List<String> getAliases() {
        return List.of("boot");
    }
}
