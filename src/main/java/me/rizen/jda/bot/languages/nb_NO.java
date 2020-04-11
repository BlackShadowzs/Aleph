package me.rizen.jda.bot.languages;

import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.misc.Prefixes;

public class nb_NO implements Language {
    public String prefix;

    public nb_NO() {
        this.prefix = Config.getInstance().getString("prefix");
    }

    @Override
    public String LANGUAGE_MENU() {
        return "Menu Lingua";
    }

    @Override
    public String DANISH() {
        return null;
    }

    @Override
    public String GERMAN() {
        return null;
    }

    @Override
    public String NORWEGIAN_BOKMAAL() {
        return null;
    }

    @Override
    public String ROMANIAN() {
        return null;
    }

    @Override
    public String ITALIAN() {
        return null;
    }

    @Override
    public String ENGLISH() {
        return null;
    }

    @Override
    public String ERROR_NAN() {
        return "Dette er ikke et gyldig nummer.";
    }

    @Override
    public String CASE_ID() {
        return "Straff ID: ";
    }

    @Override
    public String LOGS_NOT_SET_FIELD_VALUE() {
        return "Skriv REPLACEsetlogs i kanalen du vil at loggene skal sendes inn (Admin eller over)";
    }

    @Override
    public String SUCCESS_REWIND() {
        return "Successfully rewinded REPLACE seconds!";
    }

    @Override
    public String SUCCESS_FORWARD() {
        return "Successfully forwarded REPLACE seconds!";
    }

    @Override
    public String LOGS_NOT_SET_FIELD_NAME() {
        return "Tired of getting these embeds in the event channel?";
    }

    @Override
    public String PROVIDED_BY_RIZEN() {
        return "Provided to you by "+Config.getInstance().getString("ownerTag");
    }

    @Override
    public String ADMIN_COMMANDS() {
        return "\n\n**• Admin Commands [REPLACE]**\n";
    }

    @Override
    public String MOD_COMMANDS() {
        return "\n\n**• Mod Commands [REPLACE]**\n";
    }

    @Override
    public String UTIL_COMMANDS() {
        return "\n\n**• Util Commands [REPLACE]**\n";
    }

    @Override
    public String IMAGE_COMMANDS() {
        return "\n\n**• Image Commands [REPLACE]**\n";
    }

    @Override
    public String FUN_COMMANDS() {
        return "\n\n**• Fun Commands [REPLACE]**\n";
    }

    @Override
    public String OWNER_COMMANDS() {
        return "\n\n**• Owner Commands [REPLACE]**\n";
    }

    @Override
    public String MUSIC_COMMANDS() {
        return "\n\n**• Music Commands [REPLACE]**\n";
    }

    @Override
    public String IN_PROPER_HTML() {
        return "You must supply proper HTML!";
    }

    @Override
    public String LYRICS_FIELD_NAME() {
        return "Full lyrics not there?";
    }

    @Override
    public String WEATHER_LOW() {
        return "Low";
    }

    @Override
    public String WEATHER_HIGH() {
        return "High";
    }

    @Override
    public String COCKTAIL_ALCOHOLIC() {
        return "Alcoholic";
    }

    @Override
    public String COCKTAIL_LAST_MODIFIED() {
        return "Modified: ";
    }

    @Override
    public String IN_MANDALORIAN_THAT_IS() {
        return "In madalorian that is: REPLACE";
    }

    @Override
    public String IN_SITH_THAT_IS() {
        return "In sith that is: REPLACE";
    }

    @Override
    public String ERROR() {
        return "Error: ";
    }

    @Override
    public String YODA_WOULD_SAY() {
        return "Yoda would say: REPLACE";
    }

    @Override
    public String COCKTAIL_SERVING_GLASS() {
        return "Serving Glass";
    }

    @Override
    public String COCKTAIL_INIT_DESCRIPTION() {
        return "Instructions & Ingredients:```yaml\n";
    }

    @Override
    public String FREE_REAL_MESSAGE() {
        return "Your free real estate meme.";
    }

    @Override
    public String WEATHER_TEMPERATURE() {
        return "Temperature";
    }

    @Override
    public String DRAKE_MESSAGE() {
        return "Here is your drake meme!";
    }

    @Override
    public String INVERT_MESSAGE() {
        return "Here is **REPLACE**'s avatar, with the `invert` filter on.";
    }

    @Override
    public String ERROR_RATE_LIMIT() {
        return "I've been  rate-limited.\nPlease try again later.";
    }

    @Override
    public String LYRICS_FIELD_VALUE() {
        return "Find the rest of the lyrics [here](https://genius.com/REPLACE)";
    }

    @Override
    public String SOURCE_CODE_PRIVATE_PARTS() {
        return "Here, a link to see my private parts ...REPLACE";
    }

    @Override
    public String HELP_COMMAND_NOT_FOUND(String guildId) {
        return "Command not found.\n`"+ Prefixes.PREFIXES.get(guildId)+"help` for a command list.";
    }

    @Override
    public String ALIASES() {
        return "Aliases: `REPLACE`";
    }

    @Override
    public String NO_ALIASES() {
        return "No aliases";
    }

    @Override
    public String HELP_MENU() {
        return "Help Menu";
    }

    @Override
    public String HELP_EMBED_AUTHOR() {
        return "Aleph | Commands";
    }

    @Override
    public String HELP_EMBED_DESCRIPTION(String guildId) {
        return "If you require help with a specific command, do `" + Prefixes.PREFIXES.get(guildId) + "help <command>`";
    }

    @Override
    public String CUSTOM_COMMANDS() {
        return "\n\n**• Custom Commands [REPLACE]**\n";
    }

    @Override
    public String COMMAND_HELP_EVAL() {
        return "Evaluates Java/Groovy code.\n"+
                "Usage: "+this.prefix+"eval <Code>\nBot Owner Only!";
    }

    @Override
    public String COMMAND_HELP_DEV() {
        return "Adds/Removes a bot developer.\n"+
                "Usage: "+this.prefix+"dev <add|rem> <@Member>";
    }

    @Override
    public String COMMAND_HELP_BOTSERVERSINFO() {
        return "Retrieves a list of bot servers with their respective owners.\n"+
                "Usage: "+"botserversinfo\n"+
                "Bot Owner Only";
    }

    @Override
    public String COMMAND_HELP_SETPREFIX() {
        return "Sets the prefix for your guild.\n"+
                "Usage: "+this.prefix+"setprefix <Desired Prefix Here>";
    }

    @Override
    public String COMMAND_HELP_SETLOGS() {
        return "Sets the logs channel for your guild.\n"+
                "Usage: "+this.prefix+"setlogs [@TextChannel|TextChannelID|TextChannelName]";
    }

    @Override
    public String COMMAND_HELP_SETHELP() {
        return "Sets the help for a custom command.\n"+
                "Usage: "+this.prefix+"sethelp <Invoke> <Help For Command>";
    }

    @Override
    public String COMMAND_HELP_CASE() {
        return "Returns info about a case.\n"+
                "Usage: "+this.prefix+"case <CaseID>";
    }

    @Override
    public String COMMAND_HELP_FORWARD() {
        return "Forwards the song."+
                "Usage: "+this.prefix+"forward <Time In Seconds>";
    }

    @Override
    public String COMMAND_HELP_JOIN() {
        return "Makes Aleph join your voice channel.\n"+
                "Usage: "+this.prefix+"join";
    }

    @Override
    public String PAUSED() {
        return "Paused the player!";
    }

    @Override
    public String UNPAUSED() {
        return "Unpaused the player!";
    }

    @Override
    public String QUEUE_IS_EMPTY() {
        return "The song queue is empty.";
    }

    @Override
    public String QUEUE_WAS_CLEARED() {
        return "Song queue was cleared!";
    }

    @Override
    public String KICK_ACTION() {
        return "Kick Action";
    }

    @Override
    public String BAN_ACTION() {
        return "Ban Action";
    }

    @Override
    public String SUCCESS_SET_VOLUME() {
        return "Successfully set the volume to ";
    }

    @Override
    public String SUCCESS_STOPPED_SONG() {
        return "Stopping the player and clearing the queue!";
    }

    @Override
    public String SUCCESS_VOTE_SKIP() {
        return "You voted to skip the current song.";
    }

    @Override
    public String ERROR_ALREADY_VOTE_SKIPPED() {
        return "You've already vote-skipped.";
    }

    @Override
    public String SUCCESS_SKIP() {
        return "Skipping ...";
    }

    @Override
    public String SONG_QUEUE() {
        return "Song Queue (REPLACE Songs)";
    }

    @Override
    public String ERROR_YOUTUBE_RETURNED_NO_RESULTS() {
        return "Youtube returned no results for this query.";
    }

    @Override
    public String ERROR_MEMBER_NOT_IN_VOICE_CHANNEL() {
        return "You're not in a voice channel!";
    }

    @Override
    public String PLAYING() {
        return "Playing";
    }

    @Override
    public String NOW_LOOPING() {
        return "Now looping: `%s`";
    }

    @Override
    public String STOPPED_LOOPING() {
        return "Stopped looping: `%s`";
    }

    @Override
    public String SUCCESS_LEAVE_VOICE_CHANNEL() {
        return "I've left your voice channel!";
    }

    @Override
    public String ERROR_BOT_NOT_IN_VOICE_CHANNEL() {
        return "I'm not in a voice channel.";
    }

    @Override
    public String ERROR_ALREADY_CONNECTED_TO_VOICE_CHANNEL() {
        return "I'm already connected  to a voice channel.";
    }

    @Override
    public String ERROR_NOT_IN_SAME_VOICE_CHANNEL() {
        return "You're not in my voice channel.";
    }

    @Override
    public String ERROR_NOT_PLAYING() {
        return "I'm currently not playing in this guild.";
    }

    @Override
    public String COMMAND_HELP_LEAVE() {
        return "Makes Aleph leave your voice channel.\n"+
                "Usage: "+this.prefix+"leave";
    }

    @Override
    public String COMMAND_HELP_LOOP() {
        return "Loops the currently playing track.\n"+
                "Usage: "+this.prefix+"loop";
    }

    @Override
    public String COMMAND_HELP_LYRICS() {
        return "Gets the lyrics of a song.\n"+
                "Usage: "+this.prefix+"lyrics <SongName>";
    }

    @Override
    public String COMMAND_HELP_NOWPLAYING() {
        return "Retrieves info about the currently playing track.\n"+
                "Usage: "+this.prefix+"nowplaying";
    }

    @Override
    public String COMMAND_HELP_PAUSE() {
        return "Pauses your currently playing song.\n"+
                "Usage: "+this.prefix+"pause";
    }

    @Override
    public String COMMAND_HELP_PLAY() {
        return "Plays a song for you.\n;"+
                "Usage: "+this.prefix+"play <SongName|SongURL>";
    }

    @Override
    public String COMMAND_HELP_QUEUE() {
        return "Retrieves the queue of songs.\n"+
                "Usage: "+this.prefix+"queue";
    }

    @Override
    public String COMMAND_HELP_REWIND() {
        return "Rewinds the song."+
                "Usage: "+this.prefix+"rewind <Time In Seconds>";
    }

    @Override
    public String COMMAND_HELP_SKIP() {
        return "Skips the current track.\n"+
                "Usage: "+this.prefix+"skip";
    }

    @Override
    public String COMMAND_HELP_STOP() {
        return "Stops the currently playing track.\n"+
                "Usage: "+this.prefix+"stop";
    }

    @Override
    public String COMMAND_HELP_VOLUME() {
        return "Sets the music player's volume.\n"+
                "Usage: "+this.prefix+"volume <Number>\n"+
                "(There's no max number, but dont destroy your ears c:)";
    }

    @Override
    public String COMMAND_HELP_INVERT() {
        return "Inverts your or another's avatar.\n"+
                "Usage: "+this.prefix+"invert [@GuildMember]\n"+
                "(Argument is optional)";
    }

    @Override
    public String COMMAND_HELP_DRAKE() {
        return "Generates a drake meme.\n"+
                "Usage: "+this.prefix+"drake <Top and : bottom line>\n"+
                "(Lines are separated by a colon)";
    }

    @Override
    public String DEFAULT_CUSTOM_COMMAND_HELP() {
        return "No help was provided for this command.\nTo add info to this command do REPLACEsethelp <Command Name> <What Command Does>";
    }

    @Override
    public String DEFAULT_COMMAND_HELP() {
        return "No instructions were provided for this command.";
    }

    @Override
    public String COMMAND_HELP_SEEK() {
        return "Seeks into a song.\n"+
                "Usage: "+this.prefix+"seek <Time>\n"+
                "Example: "+this.prefix+"seek 1:20";
    }

    @Override
    public String COMMAND_HELP_FREEREAL() {
        return "Generates a \"Its Free Real Estate\"meme.\n"+
                "Usage: "+this.prefix+"freereal <Text>";
    }

    @Override
    public String COMMAND_HELP_GITHUB() {
        return "Finds & retrieves info on a GitHub account.\n"+
                "Usage: "+this.prefix+"github <Username>\n"+
                "(For all you nerds out there :P)";
    }

    @Override
    public String COMMAND_HELP_SOURCECODE() {
        return "Sends you a link to Aleph's GitHub.\n"+
                "Usage: "+this.prefix+"sourcecode\n"+
                "(For all you nerds out there :P)";
    }

    @Override
    public String COMMAND_HELP_WEATHER() {
        return "Returns the weather of a specific city/region/state/country.\n"+
                "Usage: "+this.prefix+"weather <Query>";
    }

    @Override
    public String COMMAND_HELP_INFO() {
        return "Retrieves a bit of info that the developer chose to share.\n"+
                "Usage: "+this.prefix+"info";
    }

    @Override
    public String COMMAND_HELP_HASTE() {
        return "Posts some code/text to one of the most popular bins.\n"+
                "Usage: "+this.prefix+"haste <Code|Text>\n"+
                "(For all you nerds out there :P)";
    }

    @Override
    public String COMMAND_HELP_PING() {
        return "Am I slow? Get my latency using this command-\n"+
                "Usage: "+this.prefix+"ping";
    }

    @Override
    public String COMMAND_HELP_CHECKWEB() {
        return "Checks the status of a website"+
                "Usage: "+this.prefix+"checkweb <URL>";
    }

    @Override
    public String COMMAND_HELP_TRIVIA() {
        return "You think you're smart? Try this trivia ;)\n"+
                "Usage: "+this.prefix+"trivia";
    }

    @Override
    public String COMMAND_HELP_FACT() {
        return "Sends a random fact.\n"+
                "Usage: "+this.prefix+"fact";
    }

    @Override
    public String COMMAND_HELP_HTMLTOPDF() {
        return "Converts HTML tags into a PDF document.\n"+
                "Usage: "+this.prefix+"htmltopdf <HTML Here>\n"+
                "Example: "+this.prefix+"htmltopdf <h1>Hello</h1>\n"+
                "(For all you nerds out there :P)";
    }

    @Override
    public String COMMAND_HELP_YESNO() {
        return "Can't decide something yourself? Try this command!\n"+
                "Usage: "+this.prefix+"yesno";
    }

    @Override
    public String COMMAND_HELP_TRUMPQUOTE() {
        return "Love Trump? So do I, here are some of his best quotes ...\n"+
                "Usage: "+this.prefix+"trump";
    }

    @Override
    public String COMMAND_HELP_COCKTAIL() {
        return "Returns a random/specific cocktail w/ recipe.\n"+
                "Usage: "+this.prefix+"cocktail [Cocktail Name]";
    }

    @Override
    public String COMMAND_HELP_YODA() {
        return "Tells you how master Yoda would've said something.\n"+
                "Usage: "+this.prefix+"yoda <Text To Translate>\n"+
                "(For all your Star Wars fans!)";
    }

    @Override
    public String COMMAND_HELP_MANDALORIAN() {
        return "Converts your text into 'Mandalorian'\n"+
                "Usage: "+this.prefix+"mandalorian <Text To Translate>\n"+
                "(For all your Star Wars fans!)";
    }

    @Override
    public String COMMAND_HELP_SITH() {
        return "Converts your text into 'Sith'\n"+
                "Usage: "+this.prefix+"sith <Text To Translate>\n"+
                "(For all your Star Wars fans!)";
    }

    @Override
    public String COMMAND_HELP_JOKE() {
        return "Sends a joke.\n"+
                "Usage: "+this.prefix+"joke";
    }

    @Override
    public String COMMAND_HELP_KICK() {
        return "Kicks a member from your guild."+
                "\nUsage: "+this.prefix+"kick <@Member|MemberID> [Reason]";
    }

    @Override
    public String BOT_ERROR() {
        return "The bot ran into an error!\nPlease contact a bot dev, if this happens often.";
    }

    @Override
    public String CUSTOM_COMMAND_ALREADY_EXISTS() {
        return "This command already exists.";
    }

    @Override
    public String MEMBER_BAN_SELF() {
        return "Trying to ban yourself? Too bad, you can't.";
    }

    @Override
    public String NO_REASON_PROVIDED() {
        return "No reason provided.";
    }

    @Override
    public String BOT_CANNOT_INTERACT() {
        return "I cannot interact with them, are they staff?";
    }

    @Override
    public String MEMBER_CANNOT_INTERACT() {
        return "You do not have permission to interact with this member.";
    }

    @Override
    public String BOT_MISSING_PERMISSIONS() {
        return "I do not have permissions to do this action.";
    }

    @Override
    public String MEMBER_MISSING_PERMISSIONS() {
        return "You do not have permission to execute this command.";
    }

    @Override
    public String EQUAL_PERMISSIONS() {
        return "You cannot interact with someone, who has the same permissions as you!";
    }

    @Override
    public String MISSING_MENTION() {
        return "You must mention a member.";
    }

    @Override
    public String SUCCESS_BAN() {
        return "**REPLACE** ble vellykket utestengt.";
    }

    @Override
    public String SUCCESS_CREATE_COMMAND() {
        return "Successfully create command: **REPLACE**";
    }

    @Override
    public String COMMAND_NOT_DELETEABLE() {
        return "This command is not deleteable.";
    }

    @Override
    public String COMMAND_DOES_NOT_EXIST() {
        return "This command does not exist";
    }

    @Override
    public String COMMAND_DOES_NOT_EXIST_OR_BUILT_IN_BOT() {
        return "This command does either not exist, or is a build-in command to the bot.";
    }

    @Override
    public String SUCCESS_REMOVE_COMMAND() {
        return "Successfully removed command: **REPLACE**";
    }

    @Override
    public String TRIVIA_CORRECT_ANSWER() {
        return "Your answer was correct!";
    }

    @Override
    public String TRIVIA_INCORRECT_ANSWER() {
        return "**Incorrect.*\nCorrect answer was: *REPLACE*";
    }

    @Override
    public String CASE_NOT_FOUND() {
        return "This case isn't registered.\nDo `"+this.prefix+"case list` to get a list of cases.";
    }

    @Override
    public String ACTION_TYPE() {
        return "Action type";
    }

    @Override
    public String REASON_FOR_ACTION() {
        return "Reason for action:";
    }

    @Override
    public String NO_CASES_REGISTERED_IN_GUILD() {
        return "No cases registered in this guild.";
    }

    @Override
    public String CASE() {
        return "Case: ";
    }

    @Override
    public String CASE_LIST() {
        return "Case List";
    }

    @Override
    public String MEMBER_KICK_SELF() {
        return "Trying to kick yourself? Too bad, you can't.";
    }

    @Override
    public String CASE_INFO() {
        return "Case info";
    }

    @Override
    public String PUNISHER() {
        return "Punisher";
    }

    @Override
    public String TARGET() {
        return "Target";
    }

    @Override
    public String GITHUB_USER() {
        return "GitHub User: ";
    }

    @Override
    public String GITHUB_USER_INFO() {
        return "User Info";
    }

    @Override
    public String GITHUB_REAL_NAME() {
        return "**Real Name**: *REPLACE*";
    }

    @Override
    public String GITHUB_LOCATION() {
        return "**Location**: *REPLACE*";
    }

    @Override
    public String GITHUB_ID() {
        return "**GitHub ID**: *REPLACE*";
    }

    @Override
    public String GITHUB_WEBSITE() {
        return "**Website**: *REPLACE*";
    }

    @Override
    public String GITHUB_SOCIAL_STATS() {
        return "Social Stats";
    }

    @Override
    public String GITHUB_FOLLOWERS() {
        return "**Followers**: *REPLACE*";
    }

    @Override
    public String GITHUB_FOLLOWING() {
        return "**Following**: *REPLACE*";
    }

    @Override
    public String GITHUB_BIO() {
        return "**Bio**:\n";
    }

    @Override
    public String YES() {
        return "yes";
    }

    @Override
    public String NO() {
        return "no";
    }

    @Override
    public String TRIVIA_TIME_LIMIT_EXCEEDED() {
        return "Took too long.\nAnswer was: *REPLACE*";
    }

    @Override
    public String TRUMP_QUOTE_SEE_SOURCE() {
        return "Click the title to see the source.";
    }

    @Override
    public String TRUMP_QUOTE() {
        return "Trump Quote";
    }

    @Override
    public String MISSING_ARGS() {
        return "Missing arguments";
    }

    @Override
    public String NEW_PREFIX() {
        return "New prefix: ";
    }

    @Override
    public String SUCCESS_UPDATE_PREFIX() {
        return "Prefix has been updated.";
    }

    @Override
    public String SUCCESS_SET_LOGS() {
        return "Successfully set the logs to: ";
    }

    @Override
    public String SUCCESS_SUCCESS() {
        return "Success";
    }

    @Override
    public String SUCCESS_SET_HELP_EMBED_FIELD_NAME() {
        return "Set the help for REPLACE to: ";
    }

    @Override
    public String SUCCESS_KICK() {
        return "**REPLACE** ble vellykket sparket ut.";
    }

    @Override
    public String SUCCESS_PING() {
        return "My ping is: ";
    }

    @Override
    public String COMMAND_HELP_BAN() {
        return "Bans a member from your guild."+
                "\nUsage: "+this.prefix+"ban <@Member|MemberID> [Reason]";
    }

    @Override
    public String COMMAND_HELP_CREATE_COMMAND() {
        return "Creates a custom command for your guild.\n"+
                "Usage: "+this.prefix+"createcommand <Invoke> <Response Message>";
    }

    @Override
    public String COMMAND_HELP_REMOVE_COMMAND() {
        return "Removes a custom command from your guild\n"+
                "Usage: "+this.prefix+"removecommand <Invoke>";
    }

    @Override
    public String COMMAND_HELP_HELP() {
        return "Retrieves a list of commands, or help for a specific command.\n"+
                "Usage: "+this.prefix+"help [Command Name]";
    }
}
