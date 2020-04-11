package me.rizen.jda.bot.command.commands.utility

import me.rizen.jda.bot.functions.MessageFunctions.createEmbed
import me.rizen.jda.bot.functions.MessageFunctions.sendEmbed
import me.rizen.jda.bot.command.CommandContext
import me.rizen.jda.bot.command.ICommand
import me.rizen.jda.bot.config.Config
import me.rizen.jda.bot.misc.GuildLanguage

class InfoCommand : ICommand {
    override fun handle(ctx: CommandContext?) {
        sendEmbed(ctx?.channel, createEmbed(ctx?.member)
                .setTitle("Information")
                .addField("Links", "**[Dashboard](http://alephbot.xyz) | [Invite Me](https://discordapp.com/api/oauth2/authorize?client_id=613343414951477269&permissions=8&scope=bot) | [GitHub](https://github.com/Aleph-Discord-Bot/Aleph/)**",false)
                .setDescription("Updates as of thr 8th of April:\n" +
                        "Added language support (supports English and Danish as of now)\n" +
                        "We're looking for translators (DM me, R1zeN)")
        )
    }

    override fun getName(): String {
        return "info"
    }

    override fun getHelp(guildId: String?): String? {
        return GuildLanguage.GuildLanguage[guildId]!!.COMMAND_HELP_INFO()
    }
}