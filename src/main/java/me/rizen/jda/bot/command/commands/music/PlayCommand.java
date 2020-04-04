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

package me.rizen.jda.bot.command.commands.music;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import me.rizen.jda.bot.command.CommandContext;
import me.rizen.jda.bot.command.ICommand;
import me.rizen.jda.bot.config.Config;
import me.rizen.jda.bot.music.PlayerManager;
import me.rizen.jda.bot.music.TrackScheduler;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;

import static me.rizen.jda.bot.functions.MusicFunctions.*;
import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static me.rizen.jda.bot.functions.MessageFunctions.sendMessage;

public class PlayCommand implements ICommand {
    private final YouTube youTube;

    public PlayCommand() {
        YouTube temp = null;

        try {
            temp = new YouTube.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    null
            )
                    .setApplicationName("Aleph")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        youTube = temp;
    }


    @Override
    public void handle(CommandContext ctx) {
        Guild guild = ctx.getGuild();
        Member member = ctx.getMember();
        TextChannel channel = ctx.getEvent().getChannel();
        List<String> args = ctx.getArgs();

        GuildVoiceState memberVoiceState = ctx.getEvent().getMember().getVoiceState();

        String input = String.join("", args);
        VoiceChannel voiceChannel = memberVoiceState.getChannel();
        PlayerManager manager = PlayerManager.getInstance();

        if (!memberInVoiceChannel(member)) {
            sendMessage(channel, "Please join a voice channel first");
            return;
        }

        AudioManager audioManager = getAudioManager(guild);
        if (args.isEmpty()) {
            sendMessage(channel, "Please provide a song name/URL for me to play!");
            return;
        }


        if (!isUrl(input)) {
            String ytSearched = searchYoutube(input);


            if (ytSearched == null) {
                sendMessage(channel, "Youtube returned no results for this query.");
                return;
            }
            input = ytSearched;
        }
        getAudioManager(guild).setSelfDeafened(true);
        audioManager.openAudioConnection(voiceChannel);
        PlayerManager playerManager = PlayerManager.getInstance();
        TrackScheduler scheduler = playerManager.getGuildMusicManager(ctx.getEvent().getGuild()).scheduler;

        scheduler.setAnnounceChannel(ctx.getEvent().getChannel());
            manager.loadAndPlay(ctx.getEvent().getChannel(), input);

    }
        private boolean isUrl (String input){
            try {
                new URL(input);

                return true;
            } catch (MalformedURLException ignored) {
                return false;
            }
        }
    @Nullable
    private String searchYoutube (String input) {
        try {
            List<SearchResult> results = youTube.search()
                    .list("id,snippet")
                    .setQ(input)
                    .setMaxResults(1L)
                    .setType("video")
                    .setFields("items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)")
                    .setKey(Config.getInstance().getString("youtubeKey"))
                    .execute()
                    .getItems();

            if(!results.isEmpty()) {
                String videoId = results.get(0).getId().getVideoId();

                return "https://www.youtube.com/watch?v=" + videoId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public String getHelp() {
        return "Plays a song\n" +
                "Usage: " + Config.getInstance().getString("prefix") + "play <Song Name|Song URL>";
    }

    @Override
    public List<String> getAliases() {
        return List.of("p");
    }

    @Override
    public String getCategory() {
        return "Music";
    }

    @Override
    public String getName() {
        return "play";
    }
}
