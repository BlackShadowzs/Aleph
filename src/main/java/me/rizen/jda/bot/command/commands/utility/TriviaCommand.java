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

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.duncte123.botcommons.web.WebUtils;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.sharding.ShardManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;

public class TriviaCommand implements ICommand {
    private EventWaiter waiter;

    public TriviaCommand(EventWaiter waiter) {
        this.waiter = waiter;
    }

    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        long channelId = channel.getIdLong();
        User author = ctx.getAuthor();

        WebUtils.ins.getJSONArray("http://jservice.io/api/random").async((json) -> {
            String question = json.findValue("question").asText();
            String answer = json.findValue("answer").asText().replace("<i>", "").replace("</i>", "");

            System.out.print(answer);
            sendMessage(channel, question);
            initQuiz(author, channelId,ctx.getJDA().getShardManager(), answer);
        });
    }

    private void initQuiz (User initiator, Long channelId, ShardManager shardManager, String answer) {
        waiter.waitForEvent(
                GuildMessageReceivedEvent.class,
                (event) -> {
                    User user = event.getAuthor();

                    return user.equals(initiator);
                },
                (event) -> {
                    TextChannel channel = shardManager.getTextChannelById(channelId);
                    Message message = event.getMessage();

                    if (message.getContentRaw().toLowerCase().contains(answer.toLowerCase())) {
                        sendMessage(channel, "Correct!");
                        return;
                    }

                    sendMessage(channel, "**Incorrect!**\nRight answer: *"+answer+"*");

                },
                15, TimeUnit.SECONDS,
                () -> {
                    TextChannel channel = shardManager.getTextChannelById(channelId);

                    sendMessage(channel, "Took too long!\nAnswer was: `"+answer+"`");
                }
        );
    }

    @Override
    public String getHelp() {
        return "Asks you a question\nUsage: "+ Config.getInstance().getString("prefix")+"trivia";
    }

    @Override
    public String getCategory() {
        return "Util";
    }

    @Override
    public String getName() {
        return "trivia";
    }

    @Override
    public List<String> getAliases() {
        return List.of("quiz");
    }
}
