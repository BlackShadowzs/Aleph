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

package me.rizen.jda.bot.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static me.rizen.jda.bot.functions.MessageFunctions.sendEmbed;


public class TrackScheduler extends AudioEventAdapter {
    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue;
    private boolean repeating = false;
    private TextChannel announceChannel;



    /**
     * @param player The audio player this scheduler uses
     */
    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }

    /**
     * Add the next track to queue or play right away if nothing is in the queue.
     *
     * @param track The track to play or add to queue.
     */
    public void queue(AudioTrack track) {
        // Calling startTrack with the noInterrupt set to true will start the track only if nothing is currently playing. If
        // something is playing, it returns false and does nothing. In that case the player was already playing so this
        // track goes to the queue instead.
        if (!player.startTrack(track, true)) {
            queue.offer(track);
        }
        }

    public BlockingQueue<AudioTrack> getQueue() {
        return queue;
    }

    /**
     * Start the next track, stopping the current one if it is playing.
     */
    public void nextTrack() {
        // Start the next track, regardless of if something is already playing or not. In case queue was empty, we are
        // giving null to startTrack, which is a valid argument and will simply stop the player.
        player.startTrack(queue.poll(), false);
    }

    public void skipTrack() {
        skipTracks(1);
    }

    public void skipTracks(int count) {
        AudioTrack nextTrack = null;

        for (int i = 0; i < count; i++) {
            nextTrack = queue.poll();
        }

        if (nextTrack != null) {
            player.playTrack(nextTrack);
            announceNextTrack(nextTrack);
            return;
        }

        if (player.getPlayingTrack() != null) {
            player.stopTrack();
        }
    }

    /**
     * Gets run when a track ends
     *
     * @param player
     *     The {@link AudioPlayer AudioTrack} for that guild
     * @param track
     *     The {@link AudioTrack AudioTrack} that ended
     * @param endReason
     *     Why did this track end?
     */
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {

        if (!endReason.mayStartNext) {
            return;
        }


        if (isRepeating()) {
            final AudioTrack clone = track.makeClone();
            clone.setUserData(track.getUserData());
            this.player.playTrack(clone);
            return;
        }
        skipTrack();
    }
    /**
     * This will tell you if the player is repeating
     *
     * @return true if the player is set to repeat
     */
    public boolean isRepeating() {
        return repeating;
    }

    /**
     * tell the player if needs to repeat
     *
     * @param repeating
     *     if the player needs to repeat
     */
    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    public TextChannel getAnnounceChannel() {
        return announceChannel;
    }

    public void setAnnounceChannel(TextChannel announceChannel) { this.announceChannel = announceChannel; }

    private void announceNextTrack(AudioTrack track) {
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Now playing.")
                .setColor(Color.red)
                .setDescription(String.format("**Playing** [%s] (%s)",
                        track.getInfo().title,
                        track.getInfo().uri)).setThumbnail("https://i.ytimg.com/vi/"+track.getInfo().identifier+"/hq720.jpg");

        sendEmbed(getAnnounceChannel(), embed);
        }


    }



