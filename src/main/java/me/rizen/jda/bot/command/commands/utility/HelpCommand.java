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

package me.rizen.jda.bot.command.commands.utility;

import com.google.cloud.firestore.DocumentSnapshot;
import me.rizen.jda.bot.CommandManager;
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import me.rizen.jda.bot.misc.Prefixes;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.List;

import static me.rizen.jda.bot.database.DatabaseFunctions.getCustomCommand;
import static me.rizen.jda.bot.database.DatabaseFunctions.getCustomCommands;
import static me.rizen.jda.bot.functions.MessageFunctions.*;
import static me.rizen.jda.bot.functions.MiscFunctions.*;

public class HelpCommand implements ICommand {
    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }


    @Override
    public void handle(CommandContext ctx) {
        final Language language = ctx.getGuildLanguage();
        try {
            TextChannel channel = ctx.getChannel();
            Member member = ctx.getMember();
            List<String> args = ctx.getArgs();
            Guild guild = ctx.getGuild();
            if (args.size() > 0) {
                ICommand command = manager.getCommand(args.get(0));
                DocumentSnapshot customCommand = getCustomCommand(guild, args.get(0));
                if (command == null && !customCommand.exists()) {
                    sendMessage(channel, language.HELP_COMMAND_NOT_FOUND(guild.getId()));
                    return;
                }
                String help = customCommand.getString("help");
                if (command != null && customCommand.getString("invoke") == null) {
                    String commandHelp = command.getHelp(ctx.getGuild().getId()) == null ? GuildLanguage.GuildLanguage.get(ctx.getGuild().getId()).DEFAULT_COMMAND_HELP() : command.getHelp(ctx.getGuild().getId());
                    String aliases = !command.getAliases().isEmpty() ? command.getAliases().toString().replace("[", "").replace("]", "") : language.NO_ALIASES();
                    EmbedBuilder embed = createEmbed(member).setAuthor(language.HELP_MENU(), null, member.getUser().getEffectiveAvatarUrl()).setDescription(
                            capitalise(command.getName()) + "```yaml\n" + commandHelp +"```\n"+ language.ALIASES().replace("REPLACE", aliases));
                    sendEmbed(channel, embed);
                    return;
                } else if (command == null && customCommand.getString("invoke") != null) {
                    String name = customCommand.getString("invoke");
                    EmbedBuilder embed = createEmbed(member).setAuthor(language.HELP_MENU(), null, member.getUser().getEffectiveAvatarUrl()).setDescription(
                            capitalise(name) + "```yaml\n" + help + "```");
                    sendEmbed(channel, embed);
                    return;
                }

            }
            EmbedBuilder embed = createEmbed(member).setAuthor(language.HELP_EMBED_AUTHOR(), null, ctx.getSelfMember().getUser().getEffectiveAvatarUrl())
                    .setColor(randomColour())
                    .setDescription(language.HELP_EMBED_DESCRIPTION(guild.getId()));
            List<String> adminCmds = new ArrayList<>();
            List<String> imageCmds = new ArrayList<>();
            List<String> musicCmds = new ArrayList<>();
            List<String> modCmds = new ArrayList<>();
            List<String> utilCmds = new ArrayList<>();
            List<String> ownerCmds = new ArrayList<>();
            List<String> funCmds = new ArrayList<>();
            manager.getCommands().forEach((cmd) -> {
                final String category = cmd.getCategory();
                if (category.equals("Admin")) {
                    adminCmds.add(cmd.getName());
                }
                if (category.equals("Mod")) {
                    modCmds.add(cmd.getName());
                }
                if (category.equals("Music")) {
                    musicCmds.add(cmd.getName());
                }
                if (category.equals("Fun")) {
                    funCmds.add(cmd.getName());
                }
                if (category.equals("Util")) {
                    utilCmds.add(cmd.getName());
                }
                if (category.equals("Bot Owner")) {
                    ownerCmds.add(cmd.getName());
                }
                if (category.equals("Image")) {
                    imageCmds.add(cmd.getName());
                }
            });
            embed.appendDescription(language.ADMIN_COMMANDS().replace("REPLACE", String.valueOf(adminCmds.size())));
            adminCmds.forEach(
                    (x) -> {
                        embed.appendDescription("`" + x + "` ");
                    });
            embed.appendDescription(language.MOD_COMMANDS().replace("REPLACE", String.valueOf(modCmds.size())));
            modCmds.forEach(
                    (x) -> {
                        embed.appendDescription("`" + x + "` ");
                    });
            embed.appendDescription(language.UTIL_COMMANDS().replace("REPLACE", String.valueOf(utilCmds.size())));
            utilCmds.forEach(
                    (x) -> {
                        embed.appendDescription("`" + x + "` ");
                    });
            embed.appendDescription(language.IMAGE_COMMANDS().replace("REPLACE", String.valueOf(imageCmds.size())));
            imageCmds.forEach(
                    (x) -> {
                        embed.appendDescription("`" + x + "` ");
                    });
            embed.appendDescription(language.FUN_COMMANDS().replace("REPLACE", String.valueOf(funCmds.size())));
            funCmds.forEach(
                    (x) -> {
                        embed.appendDescription("`" + x + "` ");
                    });
            embed.appendDescription(language.OWNER_COMMANDS().replace("REPLACE", String.valueOf(ownerCmds.size())));
            ownerCmds.forEach(
                    (x) -> {
                        embed.appendDescription("`" + x + "` ");
                    });
            embed.appendDescription(language.MUSIC_COMMANDS().replace("REPLACE", String.valueOf(musicCmds.size())));
            musicCmds.forEach(
                    (x) -> {
                        embed.appendDescription("`" + x + "` ");
                    });

            if (getCustomCommands(guild).isEmpty()) {
                embed.appendDescription(language.CUSTOM_COMMANDS().replace("REPLACE", String.valueOf(getCustomCommands(guild).size())));
                embed.appendDescription("This guild has no custom commands.");
            } else {
                embed.appendDescription(language.CUSTOM_COMMANDS().replace("REPLACE", String.valueOf(getCustomCommands(guild).size())));
                getCustomCommands(guild).forEach((cmd) -> {
                    embed.appendDescription("`"+cmd+"` ");
                });
            }



        sendEmbed(channel, embed);
        } catch (Exception e) {
            TextChannel channel = ctx.getChannel();
            sendMessage(channel, language.BOT_ERROR());

            e.printStackTrace();
        }
    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_HELP();
    }

    @Override
    public String getCategory() {
        return "Util";
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public List<String> getAliases() {
        return List.of("halp","?");
    }
}
