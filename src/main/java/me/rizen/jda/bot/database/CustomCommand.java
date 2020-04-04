package me.rizen.jda.bot.database;

public class CustomCommand {
    public String invoke;
    public String message;
    public String help;
    public CustomCommand(String invoke, String message, String help) {
        this.invoke = invoke;
        this.message = message;
        this.help = help;
    }
}
