package me.rizen.jda.bot.languages;

import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.misc.Prefixes;

public class da_DK implements Language {
    public String prefix;

    public da_DK() {
        this.prefix = Config.getInstance().getString("prefix");
    }

    @Override
    public String LANGUAGE_MENU() {
        return "Sprog Menu";
    }

    @Override
    public String DANISH() {
        return "**Dansk (Dansk)**";
    }

    @Override
    public String GERMAN() {
        return "Tysk (Deutsch)";
    }

    @Override
    public String NORWEGIAN_BOKMAAL() {
        return "Norsk (Norsk Bokmål)";
    }

    @Override
    public String ITALIAN() {
        return "Italiensk (Italiano)";
    }

    @Override
    public String ROMANIAN() {
        return "Rumænsk (Română)";
    }

    @Override
    public String ENGLISH() {
        return "Engelsk (English)";
    }

    @Override
    public String ERROR_NOT_PLAYING() {
        return "Jeg spiller ikke i denne server.";
    }

    @Override
    public String ERROR_NOT_IN_SAME_VOICE_CHANNEL() {
        return "Du skal være i min stemmekanal for at bruge denne kommando!";
    }

    @Override
    public String ERROR_ALREADY_VOTE_SKIPPED() {
        return "Du har allerede stemt for at skippe sangen!";
    }

    @Override
    public String ERROR_ALREADY_CONNECTED_TO_VOICE_CHANNEL() {
        return "Jeg er allerede tilsluttet en stemmekanal!";
    }

    @Override
    public String ERROR_BOT_NOT_IN_VOICE_CHANNEL() {
        return "Jeg er ikke i en stemmekanal.";
    }

    @Override
    public String ERROR_YOUTUBE_RETURNED_NO_RESULTS() {
        return "YouTube-søgningen gav ingen resultater.";
    }

    @Override
    public String ERROR_MEMBER_NOT_IN_VOICE_CHANNEL() {
        return "Du skal være i en stemmekanal for at bruge denne kommando.";
    }

    @Override
    public String PAUSED() {
        return "Pausede";
    }

    @Override
    public String UNPAUSED() {
        return "Startede";
    }

    @Override
    public String QUEUE_IS_EMPTY() {
        return "Køen er tom";
    }

    @Override
    public String QUEUE_WAS_CLEARED() {
        return "Køen blev fjernet";
    }

    @Override
    public String SONG_QUEUE() {
        return "Sangkø";
    }

    @Override
    public String PLAYING() {
        return "Afspiller";
    }

    @Override
    public String BAN_ACTION() {
        return "Ban-handling";
    }

    @Override
    public String KICK_ACTION() {
        return "Udsmidnings-handling";
    }

    @Override
    public String NOW_LOOPING() {
        return "Looper nu: `%s`";
    }

    @Override
    public String STOPPED_LOOPING() {
        return "Stoppede med at loope: `%s`";
    }

    @Override
    public String SUCCESS_SET_VOLUME() {
        return "Jeg satte lydniveauet til ";
    }

    @Override
    public String SUCCESS_STOPPED_SONG() {
        return "Stoppede sangen!";
    }

    @Override
    public String SUCCESS_LEAVE_VOICE_CHANNEL() {
        return "Jeg forlod din stemmekanal.";
    }

    @Override
    public String SUCCESS_VOTE_SKIP() {
        return "Du stemte for at skippe den nuværende sang.";
    }

    @Override
    public String SUCCESS_SKIP() {
        return "Skipper ...";
    }

    @Override
    public String ERROR_NAN() {
        return "Dette er ikke et gyldigt nummer.";
    }

    @Override
    public String CASE_ID() {
        return "Straf ID: ";
    }

    @Override
    public String LOGS_NOT_SET_FIELD_VALUE() {
        return "Skriv REPLACEsetlogs [@TekstKanal|TekstKanalId|TekstsKanalNavn] (Admin eller højere)";
    }

    @Override
    public String SUCCESS_REWIND() {
        return "Skruede succesfuldt REPLACE sekunder tilbage!";
    }

    @Override
    public String SUCCESS_FORWARD() {
        return "Skruede succesfuldt REPLACE sekunder frem!";
    }

    @Override
    public String LOGS_NOT_SET_FIELD_NAME() {
        return "Træt af at modtage disse embeds i kommando-kanalen?";
    }

    @Override
    public String PROVIDED_BY_RIZEN() {
        return "Bragt til dig af ";
    }

    @Override
    public String ADMIN_COMMANDS() {
        return "\n\n**• Admin Kommandoer [REPLACE]**\n";
    }

    @Override
    public String MOD_COMMANDS() {
        return "\n\n**• Mod Kommandoer [REPLACE]**\n";
    }

    @Override
    public String UTIL_COMMANDS() {
        return "\n\n**• Diverse Kommandoer [REPLACE]**\n";
    }

    @Override
    public String IMAGE_COMMANDS() {
        return "\n\n**• Billede Kommandoer [REPLACE]**\n";
    }

    @Override
    public String FUN_COMMANDS() {
        return "\n\n**• Sjove Kommandoer [REPLACE]**\n";
    }

    @Override
    public String OWNER_COMMANDS() {
        return "\n\n**• Ejer Kommandoer [REPLACE]**\n";
    }

    @Override
    public String MUSIC_COMMANDS() {
        return "\n\n**• Musik Kommandoer [REPLACE]**\n";
    }

    @Override
    public String IN_PROPER_HTML() {
        return "Du skal angive rigtig HTML!";
    }

    @Override
    public String LYRICS_FIELD_NAME() {
        return "Mangler den fuldte tekst?";
    }

    @Override
    public String WEATHER_LOW() {
        return "Koldest";
    }

    @Override
    public String WEATHER_HIGH() {
        return "Varmest";
    }

    @Override
    public String COCKTAIL_ALCOHOLIC() {
        return "Alkoholisk";
    }

    @Override
    public String COCKTAIL_LAST_MODIFIED() {
        return "Sidst ændret: ";
    }

    @Override
    public String IN_MANDALORIAN_THAT_IS() {
        return "På mandaloriansk er det: REPLACE";
    }

    @Override
    public String IN_SITH_THAT_IS() {
        return "På sith er det: REPLACE";
    }

    @Override
    public String ERROR() {
        return "Fejl: ";
    }

    @Override
    public String YODA_WOULD_SAY() {
        return "Yoda ville sige: REPLACE";
    }

    @Override
    public String COCKTAIL_SERVING_GLASS() {
        return "Serverings-glads";
    }

    @Override
    public String COCKTAIL_INIT_DESCRIPTION() {
        return "Instrukser & Ingredienser:```yaml\n";
    }

    @Override
    public String FREE_REAL_MESSAGE() {
        return "Din \"free real estate meme.\"";
    }

    @Override
    public String WEATHER_TEMPERATURE() {
        return "Temperatur";
    }

    @Override
    public String DRAKE_MESSAGE() {
        return "Her er din drake meme!";
    }

    @Override
    public String INVERT_MESSAGE() {
        return "Her er **REPLACE**s avatar, med `invert` filteret on.";
    }

    @Override
    public String ERROR_RATE_LIMIT() {
        return "Jeg er blevet \"rate-limited\"\nPrøv venligst igen senere.";
    }

    @Override
    public String LYRICS_FIELD_VALUE() {
        return "Find den [her](https://genius.com/REPLACE)";
    }

    @Override
    public String SOURCE_CODE_PRIVATE_PARTS() {
        return "Her, et link til at mine private dele ...REPLACE";
    }

    @Override
    public String HELP_COMMAND_NOT_FOUND(String guildId) {
        return "Kommando ikke fundet.\n`"+ Prefixes.PREFIXES.get(guildId)+"help` for en kommandoliste.";
    }

    @Override
    public String ALIASES() {
        return "Aliaser: `REPLACE`";
    }

    @Override
    public String NO_ALIASES() {
        return "Ingen aliaser";
    }

    @Override
    public String HELP_MENU() {
        return "Hjælp Menu";
    }

    @Override
    public String HELP_EMBED_AUTHOR() {
        return "Aleph | Kommandoer";
    }

    @Override
    public String HELP_EMBED_DESCRIPTION(String guildId) {
        return "Hvis du har brug for hjælp med en specifik kommando, skriv `" + Prefixes.PREFIXES.get(guildId) + "help <kommando>`";
    }

    @Override
    public String CUSTOM_COMMANDS() {
        return "\n\n**• Brugerdefinerede Kommandoer [REPLACE]**\n";
    }

    @Override
    public String COMMAND_HELP_EVAL() {
        return "Evaluerer Java/Groovy kode.\n"+
                "Andvendelse: "+this.prefix+"eval <kode>\nKun bot-ejeren!";
    }

    @Override
    public String COMMAND_HELP_DEV() {
        return "Tilføjer/Fjerner en botudvikler.\n"+
                "Anvendelse: "+this.prefix+"dev <add|rem> <@Member>";
    }

    @Override
    public String COMMAND_HELP_BOTSERVERSINFO() {
        return "Returnerer en liste over servere med deres respektive ejere.\n"+
                "Andvendelse: "+"botserversinfo\n"+
                "Kun bot-ejer!";
    }

    @Override
    public String COMMAND_HELP_SETPREFIX() {
        return "Sætter bot-prefixet for din server.\n"+
                "Andvendelse: "+this.prefix+"setprefix <Ønskede Prefix Her>";
    }

    @Override
    public String COMMAND_HELP_SETLOGS() {
        return "Sætter logs-kanalen for din server.\n"+
                "Andvendelse: "+this.prefix+"setlogs [@TekstKanal|TekstKanalID|TekstKanalNavn]";
    }

    @Override
    public String COMMAND_HELP_SETHELP() {
        return "Angiver kommando-hjælpen for en brugerdefineret kommando.\n"+
                "Andvendelse: "+this.prefix+"sethelp <Kommando Navn> <Help For Command>";
    }

    @Override
    public String COMMAND_HELP_CASE() {
        return "Returns info about a case.\n"+
                "Anvendes: "+this.prefix+"case <StrafID>";
    }

    @Override
    public String COMMAND_HELP_FORWARD() {
        return "Spoler x sekunder frem i sangen."+
                "Anvendelse: "+this.prefix+"forward <Tid I Seconds>";
    }

    @Override
    public String COMMAND_HELP_JOIN() {
        return "Får Aleph til at tilslutte sig din talekanal.\n"+
                "Anvendelse: "+this.prefix+"join";
    }

    @Override
    public String COMMAND_HELP_LEAVE() {
        return "Får Aleph til at forlade din stemmekanal.\n"+
                "Anvendelse: "+this.prefix+"leave";
    }

    @Override
    public String COMMAND_HELP_LOOP() {
        return "Sætter din nuværende sang på repeat.\n"+
                "Anvendelse: "+this.prefix+"loop";
    }

    @Override
    public String COMMAND_HELP_LYRICS() {
        return "Find teksten på en sang.\n"+
                "Anvendelse: "+this.prefix+"lyrics <SangNavn>";
    }

    @Override
    public String COMMAND_HELP_NOWPLAYING() {
        return "Returnerer info om den spillende sang.\n"+
                "Andvendelse: "+this.prefix+"nowplaying";
    }

    @Override
    public String COMMAND_HELP_PAUSE() {
        return "Pauser din sang.\n"+
                "Anvendelse: "+this.prefix+"pause";
    }

    @Override
    public String COMMAND_HELP_PLAY() {
        return "Spiller en sang for dig.\n;"+
                "Anvendelse: "+this.prefix+"play <SongNavn|SangURL>";
    }

    @Override
    public String COMMAND_HELP_QUEUE() {
        return "Returnerer sangkøen.\n"+
                "Anvendelse: "+this.prefix+"queue";
    }

    @Override
    public String COMMAND_HELP_REWIND() {
        return "Spoler tilbage i sangen."+
                "Anvendelse: "+this.prefix+"rewind <Tid I Sekunder>";
    }

    @Override
    public String COMMAND_HELP_SKIP() {
        return "Springer den nuværende sang over.\n"+
                "Anvendelse: "+this.prefix+"skip";
    }

    @Override
    public String COMMAND_HELP_STOP() {
        return "Stopper sangen.\n"+
                "Anvendelse: "+this.prefix+"stop";
    }

    @Override
    public String COMMAND_HELP_VOLUME() {
        return "Sætter musikafspillerens lydniveau.\n"+
                "Anvendelse: "+this.prefix+"volume <Number>\n"+
                "(There's no max number, but dont destroy your ears c:)";
    }

    @Override
    public String COMMAND_HELP_INVERT() {
        return "Inverterer dit eller en andens avatar.\n"+
                "Anvendelse: "+this.prefix+"invert [@GuildMedlem]\n"+
                "(Argument is optional)";
    }

    @Override
    public String COMMAND_HELP_DRAKE() {
        return "Genererer et drake meme.\n"+
                "Anvendelse: "+this.prefix+"drake <Top og : bund linje>\n"+
                "(Lines are separated by a colon)";
    }

    @Override
    public String DEFAULT_CUSTOM_COMMAND_HELP() {
        return "Ingen hjælp blev angivet for denne kommando.\nFor at tilføje hjælp til denne kommando skirv REPLACEsethelp <Kommando Navn> <Hvad kommandoen gør>";
    }

    @Override
    public String DEFAULT_COMMAND_HELP() {
        return "Ingen instrukser blev angivet for denne kommando.";
    }

    @Override
    public String COMMAND_HELP_SEEK() {
        return "Sætter tidspunktet i din sang.\n"+
                "Anvendelse: "+this.prefix+"seek <Tid>\n"+
                "Eksempel: "+this.prefix+"seek 1:20";
    }

    @Override
    public String COMMAND_HELP_FREEREAL() {
        return "Genererer et \"Its Free Real Estate\"meme.\n"+
                "Anvendelse: "+this.prefix+"freereal <Tekst>";
    }

    @Override
    public String COMMAND_HELP_GITHUB() {
        return "Finds & retrieves info on a GitHub account.\n"+
                "Anvendelse: "+this.prefix+"github <Username>\n"+
                "(Til alle jer nørder der ude :P)";
    }

    @Override
    public String COMMAND_HELP_SOURCECODE() {
        return "Sends you a link to Aleph's GitHub.\n"+
                "Anvendelse: "+this.prefix+"sourcecode\n"+
                "(Til alle jer nørder der ude :P)";
    }

    @Override
    public String COMMAND_HELP_WEATHER() {
        return "Returns the weather of a specific city/region/state/country.\n"+
                "Anvendelse: "+this.prefix+"weather <Query>";
    }

    @Override
    public String COMMAND_HELP_INFO() {
        return "Retrieves a bit of info that the developer chose to share.\n"+
                "Anvendelse: "+this.prefix+"info";
    }

    @Override
    public String COMMAND_HELP_HASTE() {
        return "Posts some code/text to one of the most popular bins.\n"+
                "Anvendelse: "+this.prefix+"haste <Code|Text>\n"+
                "(Til alle jer nørder der ude :P)";
    }

    @Override
    public String COMMAND_HELP_PING() {
        return "Am I slow? Get my latency using this command-\n"+
                "Anvendelse: "+this.prefix+"ping";
    }

    @Override
    public String COMMAND_HELP_CHECKWEB() {
        return "Checks the status of a website"+
                "Anvendelse: "+this.prefix+"checkweb <URL>";
    }

    @Override
    public String COMMAND_HELP_TRIVIA() {
        return "You think you're smart? Try this trivia ;)\n"+
                "Anvendelse: "+this.prefix+"trivia";
    }

    @Override
    public String COMMAND_HELP_FACT() {
        return "Sends a random fact.\n"+
                "Anvendelse: "+this.prefix+"fact";
    }

    @Override
    public String COMMAND_HELP_HTMLTOPDF() {
        return "Converts HTML tags into a PDF document.\n"+
                "Anvendelse: "+this.prefix+"htmltopdf <HTML Here>\n"+
                "Example: "+this.prefix+"htmltopdf <h1>Hello</h1>\n"+
                "(Til alle jer nørder der ude :P)";
    }

    @Override
    public String COMMAND_HELP_YESNO() {
        return "Can't decide something yourself? Try this command!\n"+
                "Anvendelse: "+this.prefix+"yesno";
    }

    @Override
    public String COMMAND_HELP_TRUMPQUOTE() {
        return "Love Trump? So do I, here are some of his best quotes ...\n"+
                "Anvendelse: "+this.prefix+"trump";
    }

    @Override
    public String COMMAND_HELP_COCKTAIL() {
        return "Returns a random/specific cocktail w/ recipe.\n"+
                "Anvendelse: "+this.prefix+"cocktail [Cocktail Name]";
    }

    @Override
    public String COMMAND_HELP_YODA() {
        return "Tells you how master Yoda would've said something.\n"+
                "Anvendelse: "+this.prefix+"yoda <Text To Translate>\n"+
                "(For all your Star Wars fans!)";
    }

    @Override
    public String COMMAND_HELP_MANDALORIAN() {
        return "Converts your text into 'Mandalorian'\n"+
                "Anvendelse: "+this.prefix+"mandalorian <Text To Translate>\n"+
                "(For all your Star Wars fans!)";
    }

    @Override
    public String COMMAND_HELP_SITH() {
        return "Converts your text into 'Sith'\n"+
                "Anvendelse: "+this.prefix+"sith <Text To Translate>\n"+
                "(For all your Star Wars fans!)";
    }

    @Override
    public String COMMAND_HELP_JOKE() {
        return "Sends a joke.\n"+
                "Anvendelse: "+this.prefix+"joke";
    }

    @Override
    public String COMMAND_HELP_KICK() {
        return "Kicks a member from your guild."+
                "\nAnvendelse: "+this.prefix+"kick <@Member|MemberID> [Reason]";
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
        return "**REPLACE** was successfully banned.";
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
        return "Set the help for REPLACE to:";
    }

    @Override
    public String SUCCESS_KICK() {
        return "**REPLACE** was successfully kicked.";
    }

    @Override
    public String SUCCESS_PING() {
        return "My ping is: ";
    }

    @Override
    public String COMMAND_HELP_BAN() {
        return "Bans a member from your guild."+
                "\nAnvendelse: "+this.prefix+"ban <@Member|MemberID> [Reason]";
    }

    @Override
    public String COMMAND_HELP_CREATE_COMMAND() {
        return "Creates a custom command for your guild.\n"+
                "Anvendelse: "+this.prefix+"createcommand <Invoke> <Response Message>";
    }

    @Override
    public String COMMAND_HELP_REMOVE_COMMAND() {
        return "Removes a custom command from your guild\n"+
                "Anvendelse: "+this.prefix+"removecommand <Invoke>";
    }

    @Override
    public String COMMAND_HELP_HELP() {
        return "Retrieves a list of commands, or help for a specific command.\n"+
                "Anvendelse: "+this.prefix+"help [Command Name]";
    }
}
