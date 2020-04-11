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

import me.duncte123.botcommons.web.WebUtils;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.List;

import static me.rizen.jda.bot.functions.MessageFunctions.*;

public class GithubCommand implements ICommand {

    private static boolean websiteProvided (String text) {
        return text.startsWith("http://") || text.startsWith("https://");
    }

    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();
        Member member = ctx.getMember();
        final Language language = ctx.getGuildLanguage();

        if (args.isEmpty()) {
            sendMessage(channel, getHelp(ctx.getGuild().getId()));
        }

        WebUtils.ins.getJSONObject("https://api.github.com/users/"+args.get(0)).async((json) -> {
            if (json.get("login").asText() == null) {
                sendMessage(channel, json.get("message").asText());
                return;
            }

            final String username = json.get("login").asText();
            final String realName = !json.get("name").asText().equals("null") ? json.get("name").asText() : "Not provided.";
            final String githubId = json.get("id").asText();
            final String avatarUrl = json.get("avatar_url").asText();
            final String userPageLink = json.get("html_url").asText();
            final String followers = json.get("followers").asText();
            final String following = json.get("following").asText();
            final String bio = !json.get("bio").asText().equals("null") ? json.get("bio").asText() : "Not provided";
            final String location = !json.get("location").asText().equals("null") ? json.get("location").asText() : "Not provided";
            final String website = websiteProvided(json.get("blog").asText()) ? "[Click me]("+json.get("blog").asText()+")" : "Not provided";

            EmbedBuilder embed = createEmbed(member)
                    .setAuthor(language.GITHUB_USER()+username, userPageLink, avatarUrl)
                    .addField(language.GITHUB_USER_INFO(),
                            String.format("%s\n" +
                                    "%s\n" +
                                    "%s\n" +
                                    "%s\n", language.GITHUB_REAL_NAME().replace("REPLACE", realName),
                                    language.GITHUB_LOCATION().replace("REPLACE", location),
                                    language.GITHUB_ID().replace("REPLACE", githubId),
                                    language.GITHUB_WEBSITE().replace("REPLACE", website)), true)
                    .addField(language.GITHUB_SOCIAL_STATS(),
                                    String.format(
                                    "%s\n"+
                                    language.GITHUB_FOLLOWERS().replace("REPLACE", followers), language.GITHUB_FOLLOWING().replace("REPLACE", following)), true)
                    .setDescription("**Bio**:\n"+bio)
                    .setImage(avatarUrl)
                    .setFooter("")
                    .setColor(Color.blue);

            sendEmbed(channel, embed);
        });
    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_GITHUB();
    }

    @Override
    public String getCategory() {
        return "Util";
    }

    @Override
    public String getName() {
        return "github";
    }
}
