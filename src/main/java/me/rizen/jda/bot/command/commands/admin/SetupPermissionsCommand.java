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

import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;

import java.util.List;

import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;


public class SetupPermissionsCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
     /*   final Guild guild = ctx.getGuild();
        final Member member = ctx.getMember();
        final TextChannel channel = ctx.getChannel();
        //final DocumentReference guildConfig = getDatabase().collection(guild.getId()).document("guildConfig");
        final List<String> args = ctx.getArgs();

        /*try {
            if (!isAdmin(member)) {
                sendMessage(channel, "You must be a server administrator to execute this command!");
                return;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (args.isEmpty()) {
            try {
                sendEmbed(channel, createEmbed(member)
                        .setAuthor("Setup Permissions Menu", null, member.getUser().getEffectiveAvatarUrl())
                        .addField("Set Admin Perms", getPrefix(guild.getId()) + "setuppermissions admin", true)
                        .addField("Set Moderator Perms", getPrefix(guild.getId()) + "setuppermissions mod", true));
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (args.get(0).equals("admin")) {
            if (args.get(1) == null) {
                try {
                    sendEmbed(channel, createEmbed(member)
                            .setTitle("Admin Perms")
                            .setDescription("Set admin role.```yaml\n"
                                    + getPrefix(guild.getId()) + this.getName() + " admin role <Role Name|Role ID|@Role>```"
                                    + "Set admin permission.```yaml\n" + getPrefix(guild.getId()) + this.getName() + " admin perm <SERVER_PERMISSION>```")
                            .addField("Wonder what permissions there are?", "Here's a [list of permissions](https://discordapp.com/developers/docs/topics/permissions#permissions-bitwise-permission-flags)", true));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
          /*  if (args.get(0).equals("mod")) {
                if () {
                    try {
                        sendEmbed(channel, createEmbed(member)
                                .setTitle("Mod Perms")
                                .setDescription("Set mod role.```yaml\n"
                                        + getPrefix(guild.getId()) + this.getName() + " mod role <Role Name|Role ID|@Role>```"
                                        + "Set modpermission.```yaml\n" + getPrefix(guild.getId()) + this.getName() + " mod perm <SERVER_PERMISSION>```")
                                .addField("Wonder what permissions there are?", "Here's a [list of permissions](https://discordapp.com/developers/docs/topics/permissions#permissions-bitwise-permission-flags)", true));
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
        sendMessage(ctx.getChannel(), "This command is temporarily disabled while we fix our database!");

    }




    @Override
    public String getHelp() {
        return "Send a menu that can help you set up permissions.\nUsage: "+ Config.getInstance().getString("prefix")+"setuppermissions";
    }

    @Override
    public String getName() {
        return "setuppermissions";
    }

    @Override
    public String getCategory() {
        return "Admin";
    }

    @Override
    public List<String> getAliases() {
        return List.of("setupperms", "setup", "editperms");
    }
}
