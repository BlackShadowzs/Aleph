package me.rizen.jda.bot.command.commands.fun;

import me.duncte123.botcommons.web.WebUtils;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.misc.GuildLanguage;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static me.rizen.jda.bot.functions.MessageFunctions.createEmbed;
import static me.rizen.jda.bot.functions.MessageFunctions.sendEmbed;
import static me.rizen.jda.bot.functions.MiscFunctions.capitalise;

public class YesNoCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        WebUtils.ins.getJSONObject("https://yesno.wtf/api/").async((json -> {
            String answerSrc = json.findValue("answer").asText();
            String answer = answerSrc.equalsIgnoreCase("Yes") ? ctx.getGuildLanguage().YES() : ctx.getGuildLanguage().NO();
            String image = json.findValue("image").asText();
            sendEmbed(channel, createEmbed(ctx.getMember())
                    .setTitle(capitalise(answer))
                    .setImage(image)
            );
        }));
    }

    @Override
    public String getHelp(String guildId) { return GuildLanguage.GuildLanguage.get(guildId).COMMAND_HELP_YESNO(); }

    @Override
    public String getName() {
        return "yesno";
    }

    @Override
    public List<String> getAliases() {
        return List.of("yesorno");
    }

    @Override
    public String getCategory() {
        return "Fun";
    }
}
