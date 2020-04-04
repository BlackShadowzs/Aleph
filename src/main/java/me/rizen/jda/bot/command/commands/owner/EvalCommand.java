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

import groovy.lang.GroovyShell;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import static me.rizen.jda.bot.functions.APIFunctions.postToHastebin;
import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;
import static me.rizen.jda.bot.functions.PermissionFunctions.getDevs;
import static me.rizen.jda.bot.functions.PermissionFunctions.isBotOwner;

public class EvalCommand implements ICommand {
    private final GroovyShell engine;
    private final String imports;

    public EvalCommand() {

        this.engine = new GroovyShell();
        this.imports = "import java.io.*\n" +
                "import java.lang.*\n" +
                "import java.util.*\n" +
                "import java.util.concurrent.*\n" +
                "import net.dv8tion.jda.api.*\n" +
                "import net.dv8tion.jda.api.entities.*\n" +
                "import net.dv8tion.jda.api.entities.impl.*\n" +
                "import static me.rizen.jda.Bot.Database.DatabaseFunctions.*;\n" +
                "import net.dv8tion.jda.api.managers.*\n" +
                "import net.dv8tion.jda.api.managers.impl.*\n" +
                "import net.dv8tion.jda.api.utils.*\n";
    }

    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        User author = ctx.getAuthor();
        if (!getDevs().contains(author.getId()) && !isBotOwner(author.getId())) {
            sendMessage(channel,"You do not have permission to use this command.\nThis command is reserved for bot-developers.");
            return;
        }

        if (ctx.getArgs().isEmpty()) {
            sendMessage(channel, "You must provide code for me to run!");
            return;
        }


        try {
            engine.setProperty("args", ctx.getArgs());
            engine.setProperty("event", ctx.getEvent());
            engine.setProperty("message", ctx.getMessage());
            engine.setProperty("channel", channel);
            engine.setProperty("jda", ctx.getJDA());
            engine.setProperty("guild", ctx.getGuild());
            engine.setProperty("member", ctx.getMember());

            String script = imports + ctx.getMessage().getContentRaw().split("\\s+", 2)[1];
            Object out = engine.evaluate(script);

            sendMessage(channel, out == null ? "Successfully executed code!" : out.toString());
        }
        catch (Exception e) {
            if (e.getMessage().length() > 50) {
                postToHastebin(e.toString(), (code) -> sendMessage(channel, "Your code errored!\nCheck the stack trace: " + code));
            } else {
                sendMessage(channel, "Your code errored!\n"+e.getLocalizedMessage());
            }
        }
    }

    @Override
    public String getHelp() {
        return "Evaluates Groovy/Java code.\nUsage: "+ Config.getInstance().getString("prefix")+ "eval <Groovy Code Here>";
    }

    @Override
    public String getCategory() {
        return "Bot Owner";
    }

    @Override
    public String getName() {
        return "eval";
    }
}
