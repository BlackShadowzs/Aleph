package me.rizen.jda.bot.command.commands.utility

import me.rizen.jda.bot.functions.MessageFunctions.createEmbed
import me.rizen.jda.bot.functions.MessageFunctions.sendEmbed
import me.rizen.jda.bot.command.CommandContext
import me.rizen.jda.bot.command.ICommand
import me.rizen.jda.bot.config.Config

class InfoCommand : ICommand {
    override fun handle(ctx: CommandContext?) {
        sendEmbed(ctx?.channel, createEmbed(ctx?.member)
                .setTitle("Information")
                .addField("Links", "**[Dashboard](http://alephbot.xyz) | [Invite Me](https://discordapp.com/api/oauth2/authorize?client_id=613343414951477269&permissions=8&scope=bot) | [GitHub](https://github.com/Aleph-Discord-Bot/Aleph/)**",false)
                .setDescription("Bot hasn't worked properly lately, I apologise!\n" +
                        "I've done a lot of database work, it should be fine now.")
        )
    }

    override fun getName(): String {
        return "info"
    }

    override fun getHelp(): String {
        return "Sends some good information.\n"+ Config.getInstance()["prefix"]+"info"
    }
}