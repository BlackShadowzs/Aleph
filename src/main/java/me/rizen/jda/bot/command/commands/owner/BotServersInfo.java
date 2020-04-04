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

package me.rizen.jda.bot.command.commands.owner;

import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.JDA;

import java.util.List;

import static me.rizen.jda.bot.functions.APIFunctions.postToHastebin;
import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;

public class BotServersInfo implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        if (!ctx.getMember().getId().equals(Config.getInstance().getString("ownerID"))) {
            sendMessage(ctx.getChannel(), "This command is owner-only!");
            return;
        }
        final JDA jda = ctx.getJDA();
        StringBuilder guildInfo = new StringBuilder();
        jda.getGuilds().forEach((guild) -> {
            guildInfo.append(guild.getName()).append("\n\nOwner: ").append(guild.getOwner().getUser().getAsTag()).append(" (").append(guild.getOwner().getUser().getId()).append(")\n-------------\n");
                });

                postToHastebin(guildInfo.toString(), (text) -> sendMessage(ctx.getChannel(), text));
    }

    @Override
    public String getHelp() {
        return "Display information about each guild the bot is in.\nNotice: As this is marked as personal data by Discord it must be an owner-only command.";
    }

    @Override
    public String getName() {
        return "botserversinfo";
    }

    @Override
    public String getCategory() {
        return "Bot Owner";
    }

    @Override
    public List<String> getAliases() {
        return List.of("bsi");
    }
}
