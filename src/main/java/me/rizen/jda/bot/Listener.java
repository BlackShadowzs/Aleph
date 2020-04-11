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

package me.rizen.jda.bot;

import me.rizen.jda.bot.languages.Language;
import me.rizen.jda.bot.languages.en_GB;
import me.rizen.jda.bot.misc.GuildLanguage;
import me.rizen.jda.bot.misc.Prefixes;
import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static me.duncte123.botcommons.BotCommons.shutdown;
import static me.rizen.jda.bot.database.DatabaseFunctions.*;
import static me.rizen.jda.bot.functions.MiscFunctions.getPrefix;
import static me.rizen.jda.bot.functions.MusicFunctions.getAudioManager;

class Listener extends ListenerAdapter {
    private final CommandManager manager;
    private final Logger logger = LoggerFactory.getLogger(Listener.class);

    Listener(CommandManager manager) throws UnknownHostException {
        this.manager = manager;
    }


    public void onReady(@Nonnull ReadyEvent event) {
        logger.info("I am now online.");
        event.getJDA().getPresence().setActivity(Activity.streaming(";help - " + event.getJDA().getGuilds().size() + " servers", "https://twitch.tv/ugrizen"));
        try {
            event.getJDA().getGuilds().forEach((guild) -> {
                String guildId = guild.getId();
                User owner = guild.retrieveOwner().complete().getUser();
                try {
                    registerGuild(guildId, ";", owner.getAsTag(), owner.getId());
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                final String prefix;
                try {
                    prefix = getPrefix(guildId);

                    Prefixes.PREFIXES.put(guildId, prefix);

                String getLanguage = getDatabase().collection(guildId).document("guildConfig").get().get().getString("language");
                if (getLanguage.equalsIgnoreCase("en_GB")) {
                    GuildLanguage.GuildLanguage.put(guildId, new en_GB());
                }
                if (getLanguage.equalsIgnoreCase("da_DK")) {
                    GuildLanguage.GuildLanguage.put(guildId, new en_GB());
                }
                if (getLanguage.equalsIgnoreCase("ro_RO")) {
                    GuildLanguage.GuildLanguage.put(guildId, new en_GB());
                }
                if (getLanguage.equalsIgnoreCase("it_IT")) {
                    GuildLanguage.GuildLanguage.put(guildId, new en_GB());
                }
                if (getLanguage.equalsIgnoreCase("nb_NO")) {
                    GuildLanguage.GuildLanguage.put(guildId, new en_GB());
                }

                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGuildJoin(@Nonnull GuildJoinEvent event) {
        Prefixes.PREFIXES.put(event.getGuild().getId(), ";");
        event.getJDA().getPresence().setActivity(Activity.streaming(";help - " + event.getJDA().getGuilds().size() + " servers", "https://twitch.tv/ugrizen"));
        try {
            registerGuild(event.getGuild().getId(), ";", event.getGuild().retrieveOwner().complete().getUser().getAsTag(), event.getGuild().getOwnerId());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGuildLeave(@Nonnull GuildLeaveEvent event) {
        event.getJDA().getPresence().setActivity(Activity.streaming(";help - " + event.getJDA().getGuilds().size() + " servers", "https://twitch.tv/ugrizen"));
    }

    @Override
    public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event) {
        final AudioManager audioManager = getAudioManager(event.getGuild());

        if (audioManager.isConnected() && Objects.requireNonNull(audioManager.getConnectedChannel()).getMembers().size() == 1) {
                audioManager.closeAudioConnection();
            }
    }

    private boolean cmdPause = false;

    private boolean getCmdPause() {
        return cmdPause;
    }

    private void setCmdPause(boolean bool) {
        this.cmdPause = bool;
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        User user = event.getAuthor();
        try {
            registerGuild(event.getGuild().getId(), ";", event.getGuild().retrieveOwner().complete().getUser().getAsTag(), event.getGuild().getOwnerId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user.isBot() || event.isWebhookMessage()) {
            return;
        }

        final String guildId = event.getGuild().getId();
        String rw = event.getMessage().getContentRaw();
        String prefix;
        try {
            try {
                prefix = getPrefix(guildId);


                if (rw.equalsIgnoreCase(prefix + "shutdown") &&
                        event.getAuthor().getIdLong() == Long.parseLong(Config.getInstance().getString("ownerID"))) {
                    event.getChannel().sendMessage("Shutting down ...").queue();
                    shutdown(event.getJDA());
                    return;
                }

                if (rw.equalsIgnoreCase(prefix + "togglepause") &&
                        event.getAuthor().getIdLong() == Long.parseLong(Config.getInstance().getString("ownerID"))) {
                    String str = getCmdPause() ? "Responding to commands!" : "Ignoring commands ...";
                    setCmdPause(!getCmdPause());
                    event.getChannel().sendMessage(str).queue();
                    return;
                }

                if (!getCmdPause()) {
                    if (!event.getAuthor().isBot() && !event.getMessage().isWebhookMessage() && rw.startsWith(prefix)) {
                        manager.handle(event);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}