package me.rizen.jda.bot.command.commands.admin;

import me.duncte123.botcommons.BotCommons;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.languages.Language;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

import static me.rizen.jda.bot.functions.MessageFunctions.createEmbed;
import static me.rizen.jda.bot.functions.MessageFunctions.sendEmbed;

public class LanguageCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final Language guildLanguage = ctx.getGuildLanguage();
        final Guild guild = ctx.getGuild();
        final TextChannel channel = ctx.getChannel();

        sendEmbed(channel, createEmbed(ctx.getMember())
                .setAuthor(guildLanguage.LANGUAGE_MENU(), null, guild.getIconUrl())
                .setDescription("```h\n")

        )
    }

    @Override
    public String getHelp(String guildId) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getCategory() {
        return null;
    }
}
