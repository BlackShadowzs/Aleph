package me.rizen.jda.bot.command.commands.fun;

import me.duncte123.botcommons.web.WebUtils;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.misc.GuildLanguage;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static me.rizen.jda.bot.functions.MessageFunctions.createEmbed;
import static me.rizen.jda.bot.functions.MessageFunctions.sendEmbed;

public class TrumpQuoteCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        final Language language = ctx.getGuildLanguage();
        WebUtils.ins.getJSONObject("https://api.tronalddump.io/random/quote").async((json) -> {
            String body = json.findValue("value").asText();
            String url = json.findValue("url").asText();

                sendEmbed(channel, createEmbed(ctx.getMember())
                        .setTitle(language.TRUMP_QUOTE(), url)
                        .setDescription(body)
                        .setFooter(language.TRUMP_QUOTE_SEE_SOURCE()));
            });
    }

    @Override
    public String getHelp(String guildId) {
        return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_TRUMPQUOTE();
    }

    @Override
    public String getName() {
        return "trumpquote";
    }

    @Override
    public List<String> getAliases() {
        return List.of("donaldquote", "trump", "donaldtrumpquote", "donaldtrump");
    }

    @Override
    public String getCategory() {
        return "Fun";
    }
}
