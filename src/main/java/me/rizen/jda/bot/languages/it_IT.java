package me.rizen.jda.bot.languages;

import me.rizen.jda.bot.config.Config;

public class it_IT implements Language {
    String prefix;

    public it_IT() {
        this.prefix = Config.getInstance().getString("prefix");
    }

    @Override
    public String LANGUAGE_MENU() {
        return "Menu Lingua";
    }

    @Override
    public String DANISH() {
        return "Danese (Dansk)";
    }

    @Override
    public String GERMAN() {
        return "Tedesco (Deutsch)";
    }

    @Override
    public String NORWEGIAN_BOKMAAL() {
        return "Norvegese (Norsk Bokmål)";
    }

    @Override
    public String ROMANIAN() {
        return "Rumeno (Română)";
    }

    @Override
    public String ITALIAN() {
        return "**Italiano (Italiano)**";
    }

    @Override
    public String ENGLISH() {
        return "Inglese (English)";
    }

    @Override
    public String BOT_ERROR() {
        return null;
    }

    @Override
    public String BOT_MISSING_PERMISSIONS() {
        return null;
    }

    @Override
    public String MEMBER_MISSING_PERMISSIONS() {
        return null;
    }

    @Override
    public String NO_REASON_PROVIDED() {
        return null;
    }

    @Override
    public String MISSING_MENTION() {
        return null;
    }

    @Override
    public String ERROR_NOT_PLAYING() {
        return null;
    }

    @Override
    public String ERROR_NOT_IN_SAME_VOICE_CHANNEL() {
        return null;
    }

    @Override
    public String ERROR_ALREADY_VOTE_SKIPPED() {
        return null;
    }

    @Override
    public String ERROR_ALREADY_CONNECTED_TO_VOICE_CHANNEL() {
        return null;
    }

    @Override
    public String ERROR_BOT_NOT_IN_VOICE_CHANNEL() {
        return null;
    }

    @Override
    public String ERROR_YOUTUBE_RETURNED_NO_RESULTS() {
        return null;
    }

    @Override
    public String ERROR_MEMBER_NOT_IN_VOICE_CHANNEL() {
        return null;
    }

    @Override
    public String MEMBER_BAN_SELF() {
        return null;
    }

    @Override
    public String MEMBER_KICK_SELF() {
        return null;
    }

    @Override
    public String ERROR_NAN() {
        return null;
    }

    @Override
    public String ERROR() {
        return null;
    }

    @Override
    public String CUSTOM_COMMAND_ALREADY_EXISTS() {
        return null;
    }

    @Override
    public String EQUAL_PERMISSIONS() {
        return null;
    }

    @Override
    public String BOT_CANNOT_INTERACT() {
        return null;
    }

    @Override
    public String ERROR_RATE_LIMIT() {
        return null;
    }

    @Override
    public String HELP_COMMAND_NOT_FOUND(String guildId) {
        return null;
    }

    @Override
    public String COMMAND_NOT_DELETEABLE() {
        return null;
    }

    @Override
    public String MEMBER_CANNOT_INTERACT() {
        return null;
    }

    @Override
    public String COMMAND_DOES_NOT_EXIST() {
        return null;
    }

    @Override
    public String MISSING_ARGS() {
        return null;
    }

    @Override
    public String IN_PROPER_HTML() {
        return null;
    }

    @Override
    public String COMMAND_DOES_NOT_EXIST_OR_BUILT_IN_BOT() {
        return null;
    }

    @Override
    public String WEATHER_LOW() {
        return null;
    }

    @Override
    public String WEATHER_HIGH() {
        return null;
    }

    @Override
    public String WEATHER_TEMPERATURE() {
        return null;
    }

    @Override
    public String CASE_ID() {
        return null;
    }

    @Override
    public String PAUSED() {
        return null;
    }

    @Override
    public String UNPAUSED() {
        return null;
    }

    @Override
    public String FREE_REAL_MESSAGE() {
        return null;
    }

    @Override
    public String QUEUE_IS_EMPTY() {
        return null;
    }

    @Override
    public String QUEUE_WAS_CLEARED() {
        return null;
    }

    @Override
    public String SONG_QUEUE() {
        return null;
    }

    @Override
    public String COCKTAIL_INIT_DESCRIPTION() {
        return null;
    }

    @Override
    public String COCKTAIL_ALCOHOLIC() {
        return null;
    }

    @Override
    public String COCKTAIL_LAST_MODIFIED() {
        return null;
    }

    @Override
    public String COCKTAIL_SERVING_GLASS() {
        return null;
    }

    @Override
    public String YODA_WOULD_SAY() {
        return null;
    }

    @Override
    public String IN_MANDALORIAN_THAT_IS() {
        return null;
    }

    @Override
    public String IN_SITH_THAT_IS() {
        return null;
    }

    @Override
    public String LYRICS_FIELD_NAME() {
        return null;
    }

    @Override
    public String LYRICS_FIELD_VALUE() {
        return null;
    }

    @Override
    public String PROVIDED_BY_RIZEN() {
        return null;
    }

    @Override
    public String LOGS_NOT_SET_FIELD_NAME() {
        return null;
    }

    @Override
    public String LOGS_NOT_SET_FIELD_VALUE() {
        return null;
    }

    @Override
    public String GITHUB_USER() {
        return null;
    }

    @Override
    public String GITHUB_USER_INFO() {
        return null;
    }

    @Override
    public String SOURCE_CODE_PRIVATE_PARTS() {
        return null;
    }

    @Override
    public String GITHUB_REAL_NAME() {
        return null;
    }

    @Override
    public String GITHUB_LOCATION() {
        return null;
    }

    @Override
    public String GITHUB_ID() {
        return null;
    }

    @Override
    public String GITHUB_WEBSITE() {
        return null;
    }

    @Override
    public String GITHUB_SOCIAL_STATS() {
        return null;
    }

    @Override
    public String INVERT_MESSAGE() {
        return null;
    }

    @Override
    public String DRAKE_MESSAGE() {
        return null;
    }

    @Override
    public String GITHUB_FOLLOWERS() {
        return null;
    }

    @Override
    public String GITHUB_FOLLOWING() {
        return null;
    }

    @Override
    public String GITHUB_BIO() {
        return null;
    }

    @Override
    public String DEFAULT_COMMAND_HELP() {
        return null;
    }

    @Override
    public String NEW_PREFIX() {
        return null;
    }

    @Override
    public String TRUMP_QUOTE() {
        return null;
    }

    @Override
    public String CASE() {
        return null;
    }

    @Override
    public String CASE_INFO() {
        return null;
    }

    @Override
    public String PUNISHER() {
        return null;
    }

    @Override
    public String TARGET() {
        return null;
    }

    @Override
    public String REASON_FOR_ACTION() {
        return null;
    }

    @Override
    public String ACTION_TYPE() {
        return null;
    }

    @Override
    public String NO_CASES_REGISTERED_IN_GUILD() {
        return null;
    }

    @Override
    public String CASE_NOT_FOUND() {
        return null;
    }

    @Override
    public String CASE_LIST() {
        return null;
    }

    @Override
    public String TRUMP_QUOTE_SEE_SOURCE() {
        return null;
    }

    @Override
    public String PLAYING() {
        return null;
    }

    @Override
    public String TRIVIA_CORRECT_ANSWER() {
        return null;
    }

    @Override
    public String YES() {
        return null;
    }

    @Override
    public String NO() {
        return null;
    }

    @Override
    public String TRIVIA_INCORRECT_ANSWER() {
        return null;
    }

    @Override
    public String BAN_ACTION() {
        return null;
    }

    @Override
    public String KICK_ACTION() {
        return null;
    }

    @Override
    public String TRIVIA_TIME_LIMIT_EXCEEDED() {
        return null;
    }

    @Override
    public String DEFAULT_CUSTOM_COMMAND_HELP() {
        return null;
    }

    @Override
    public String NOW_LOOPING() {
        return null;
    }

    @Override
    public String STOPPED_LOOPING() {
        return null;
    }

    @Override
    public String HELP_EMBED_DESCRIPTION(String guildId) {
        return null;
    }

    @Override
    public String HELP_EMBED_AUTHOR() {
        return null;
    }

    @Override
    public String HELP_MENU() {
        return null;
    }

    @Override
    public String ALIASES() {
        return null;
    }

    @Override
    public String NO_ALIASES() {
        return null;
    }

    @Override
    public String ADMIN_COMMANDS() {
        return null;
    }

    @Override
    public String MOD_COMMANDS() {
        return null;
    }

    @Override
    public String UTIL_COMMANDS() {
        return null;
    }

    @Override
    public String IMAGE_COMMANDS() {
        return null;
    }

    @Override
    public String FUN_COMMANDS() {
        return null;
    }

    @Override
    public String OWNER_COMMANDS() {
        return null;
    }

    @Override
    public String MUSIC_COMMANDS() {
        return null;
    }

    @Override
    public String CUSTOM_COMMANDS() {
        return null;
    }

    @Override
    public String SUCCESS_BAN() {
        return null;
    }

    @Override
    public String SUCCESS_CREATE_COMMAND() {
        return null;
    }

    @Override
    public String SUCCESS_REMOVE_COMMAND() {
        return null;
    }

    @Override
    public String SUCCESS_SET_HELP_EMBED_FIELD_NAME() {
        return null;
    }

    @Override
    public String SUCCESS_SET_VOLUME() {
        return null;
    }

    @Override
    public String SUCCESS_UPDATE_PREFIX() {
        return null;
    }

    @Override
    public String SUCCESS_STOPPED_SONG() {
        return null;
    }

    @Override
    public String SUCCESS_LEAVE_VOICE_CHANNEL() {
        return null;
    }

    @Override
    public String SUCCESS_VOTE_SKIP() {
        return null;
    }

    @Override
    public String SUCCESS_SKIP() {
        return null;
    }

    @Override
    public String SUCCESS_FORWARD() {
        return null;
    }

    @Override
    public String SUCCESS_REWIND() {
        return null;
    }

    @Override
    public String SUCCESS_SET_LOGS() {
        return null;
    }

    @Override
    public String SUCCESS_SUCCESS() {
        return null;
    }

    @Override
    public String SUCCESS_KICK() {
        return null;
    }

    @Override
    public String SUCCESS_PING() {
        return null;
    }

    @Override
    public String COMMAND_HELP_BAN() {
        return null;
    }

    @Override
    public String COMMAND_HELP_CREATE_COMMAND() {
        return null;
    }

    @Override
    public String COMMAND_HELP_REMOVE_COMMAND() {
        return null;
    }

    @Override
    public String COMMAND_HELP_HELP() {
        return null;
    }

    @Override
    public String COMMAND_HELP_KICK() {
        return null;
    }

    @Override
    public String COMMAND_HELP_EVAL() {
        return null;
    }

    @Override
    public String COMMAND_HELP_DEV() {
        return null;
    }

    @Override
    public String COMMAND_HELP_BOTSERVERSINFO() {
        return null;
    }

    @Override
    public String COMMAND_HELP_SETPREFIX() {
        return null;
    }

    @Override
    public String COMMAND_HELP_SETLOGS() {
        return null;
    }

    @Override
    public String COMMAND_HELP_SETHELP() {
        return null;
    }

    @Override
    public String COMMAND_HELP_CASE() {
        return null;
    }

    @Override
    public String COMMAND_HELP_FORWARD() {
        return null;
    }

    @Override
    public String COMMAND_HELP_JOIN() {
        return null;
    }

    @Override
    public String COMMAND_HELP_LEAVE() {
        return null;
    }

    @Override
    public String COMMAND_HELP_LOOP() {
        return null;
    }

    @Override
    public String COMMAND_HELP_LYRICS() {
        return null;
    }

    @Override
    public String COMMAND_HELP_NOWPLAYING() {
        return null;
    }

    @Override
    public String COMMAND_HELP_PAUSE() {
        return null;
    }

    @Override
    public String COMMAND_HELP_PLAY() {
        return null;
    }

    @Override
    public String COMMAND_HELP_QUEUE() {
        return null;
    }

    @Override
    public String COMMAND_HELP_REWIND() {
        return null;
    }

    @Override
    public String COMMAND_HELP_SKIP() {
        return null;
    }

    @Override
    public String COMMAND_HELP_STOP() {
        return null;
    }

    @Override
    public String COMMAND_HELP_VOLUME() {
        return null;
    }

    @Override
    public String COMMAND_HELP_INVERT() {
        return null;
    }

    @Override
    public String COMMAND_HELP_DRAKE() {
        return null;
    }

    @Override
    public String COMMAND_HELP_FREEREAL() {
        return null;
    }

    @Override
    public String COMMAND_HELP_GITHUB() {
        return null;
    }

    @Override
    public String COMMAND_HELP_SEEK() {
        return null;
    }

    @Override
    public String COMMAND_HELP_SOURCECODE() {
        return null;
    }

    @Override
    public String COMMAND_HELP_WEATHER() {
        return null;
    }

    @Override
    public String COMMAND_HELP_INFO() {
        return null;
    }

    @Override
    public String COMMAND_HELP_HASTE() {
        return null;
    }

    @Override
    public String COMMAND_HELP_PING() {
        return null;
    }

    @Override
    public String COMMAND_HELP_CHECKWEB() {
        return null;
    }

    @Override
    public String COMMAND_HELP_TRIVIA() {
        return null;
    }

    @Override
    public String COMMAND_HELP_FACT() {
        return null;
    }

    @Override
    public String COMMAND_HELP_HTMLTOPDF() {
        return null;
    }

    @Override
    public String COMMAND_HELP_YESNO() {
        return null;
    }

    @Override
    public String COMMAND_HELP_TRUMPQUOTE() {
        return null;
    }

    @Override
    public String COMMAND_HELP_COCKTAIL() {
        return null;
    }

    @Override
    public String COMMAND_HELP_YODA() {
        return null;
    }

    @Override
    public String COMMAND_HELP_MANDALORIAN() {
        return null;
    }

    @Override
    public String COMMAND_HELP_SITH() {
        return null;
    }

    @Override
    public String COMMAND_HELP_JOKE() {
        return null;
    }
}
