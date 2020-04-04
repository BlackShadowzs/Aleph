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

import com.google.cloud.firestore.Firestore;
import me.rizen.jda.bot.CommandManager;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static me.rizen.jda.bot.database.DatabaseFunctions.getDatabase;
import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;

public class RemoveCustomCommand implements ICommand {
    CommandManager manager;

    public RemoveCustomCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(CommandContext ctx) {
        try {
             Firestore database = getDatabase();
            TextChannel channel = ctx.getChannel();
            Member member = ctx.getMember();
            Guild guild = ctx.getGuild();
            List<String> args = ctx.getArgs();
            if (!member.hasPermission(Permission.MANAGE_SERVER)) {
                sendMessage(channel, "You must have the `MANAGE_SERVER` permission to run this.");
                return;
            }
            if (args.size() < 1) {
                sendMessage(channel, getHelp());
                return;
            }
            if (!database.collection(guild.getId()).document(args.get(0).toLowerCase()+"Command").get().get().exists() &&
                    manager.getCommand(args.get(0).toLowerCase()) != null) {
                sendMessage(channel, "This command isn't deletable.");
                return;
            }
            if (!database.collection(guild.getId()).document(args.get(0).toLowerCase()+"Command").get().get().exists()) {
                sendMessage(channel, "This command doesn't exist.");
                return;
            }

             database.collection(guild.getId()).document(args.get(0)+"Command").delete();
                 sendMessage(channel,"Successfully deleted command: `"+args.get(0)+"`");
        } catch (Exception e) {
            TextChannel channel = ctx.getChannel();
            sendMessage(channel, "An error occurred. If this keeps on happening,\nplease contact a bot developer.");
        }
    }



    @Override
    public String getHelp() {
        return "Creates a command\nUsage: "+ Config.getInstance().getString("prefix")+"removecommand <invoke>";
    }

    @Override
    public String getName() {
        return "removecommand";
    }

    @Override
    public String getCategory() {
        return "Admin";
    }

    @Override
    public List<String> getAliases() {
        return List.of("remcmd", "delcmd", "deletecommand", "deletecmd", "removecmd");
    }

}
