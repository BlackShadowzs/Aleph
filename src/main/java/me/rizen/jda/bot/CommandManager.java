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

package me.rizen.jda.bot;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.rizen.jda.bot.command.commands.fun.*;
import me.rizen.jda.bot.command.commands.images.DrakeMemeCommand;
import me.rizen.jda.bot.command.commands.images.FreeRealEstateMemeCommand;
import me.rizen.jda.bot.command.commands.images.InvertAvatarCommand;
import me.rizen.jda.bot.command.commands.moderator.KickCommand;
import me.rizen.jda.bot.command.commands.music.*;
import me.rizen.jda.bot.command.commands.owner.DevCommand;
import me.rizen.jda.bot.command.commands.owner.EvalCommand;
import me.rizen.jda.bot.command.commands.utility.*;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.command.commands.admin.*;
import me.rizen.jda.bot.command.commands.owner.BotServersInfo;
import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import static me.rizen.jda.bot.functions.MiscFunctions.getPrefix;


public class CommandManager {

    private final List<ICommand> commands = new ArrayList<>();

    CommandManager(EventWaiter waiter) {

        if (Config.getInstance().getBoolean("loadcommands")) {
            System.out.print("Loading commands\n");

            try {
                //Owner Commands
                addCommand(new EvalCommand());
                addCommand(new DevCommand());
                addCommand(new BotServersInfo());


                //Admin Commands
                addCommand(new SetPrefixCommand());
                addCommand(new SetLogsCommand());
                //addCommand(new SetupPermissionsCommand());
                addCommand(new SetHelpCommand());
                addCommand(new BanCommand());
                addCommand(new RemoveCustomCommand(this));
                addCommand(new CreateCommandCommand(this));

                // Moderator Commands
                addCommand(new CaseCommand());
                addCommand(new KickCommand());

                //Music Commands
                addCommand(new ForwardCommand());
                addCommand(new JoinCommand());
                addCommand(new LeaveCommand());
                addCommand(new LoopCommand());
                addCommand(new LyricsCommand());
                addCommand(new NowPlayingCommand());
                addCommand(new PauseCommand());
                addCommand(new PlayCommand());
                addCommand(new QueueCommand());
                addCommand(new RewindCommand());
                addCommand(new SkipCommand());
                addCommand(new StopCommand());
                addCommand(new VolumeCommand());

                //Image Commands
                addCommand(new InvertAvatarCommand());
                addCommand(new DrakeMemeCommand());
                addCommand(new FreeRealEstateMemeCommand());

                //Utility Commands
                addCommand(new GithubCommand());
                addCommand(new SourceCodeCommand());
                addCommand(new WeatherCommand());
                addCommand(new InfoCommand());
                addCommand(new HelpCommand(this));
                addCommand(new PasteCommand());
                //addCommand(new RandomQuoteCommand());
                addCommand(new PingCommand());
                addCommand(new WebsiteStatusCommand());

                //Fun Commands
                addCommand(new TriviaCommand(waiter));
                addCommand(new RandomFactCommand());
                addCommand(new HtmlToPdfCommand());
                addCommand(new YesNoCommand());
                addCommand(new TrumpQuoteCommand());
                addCommand(new CocktailCommand());
                addCommand(new YodaCommand());
                addCommand(new MandalorianCommand());
                addCommand(new SithCommand());
                addCommand(new JokeCommand());



            }catch (Exception e) {
                e.getMessage();
            }
        }
    }


    private void addCommand(ICommand cmd) {
        boolean nameFound = this.commands.stream()
                .anyMatch((it) -> it.getName()
                        .equalsIgnoreCase(cmd.getName()));

        if (nameFound) {
            throw new IllegalArgumentException("A command with this name is already present");
        }

        commands.add(cmd);
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (ICommand cmd : this.commands) {
            if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
                return cmd;
            }
        }

        return null;
    }

    public void handle(GuildMessageReceivedEvent event) {
        String prefix = null;
        try {
            prefix = getPrefix(event.getGuild().getId());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(prefix), "")
                .split("\\s+");

        String invoke = split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);

        if (cmd != null) {
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            if (!ctx.getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
                return;
            }

            cmd.handle(ctx);


        }

    }

}