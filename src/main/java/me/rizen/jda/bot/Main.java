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


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import me.duncte123.botcommons.web.WebUtils;
import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    private Main() throws IOException {
        WebUtils.setUserAgent("Mozilla/5.0 Aleph#1255 / jonasschiott.online");

        try {
            Config config = new Config(new File("botconfig.json"));
            EventWaiter waiter = new EventWaiter();
            CommandManager manager = new CommandManager(waiter);
            Listener listener = new Listener(manager);
            String token = System.getProperty("os.name").equalsIgnoreCase("linux") ? config.getString("token") : config.getString("betaToken");
            JDABuilder.createDefault(token)
                    .setActivity(Activity.streaming("Booting ...", "https://twitch.tv/ugrizen"))
                    .addEventListeners(waiter, listener)
                    .build();

        } catch (LoginException e) {
            e.printStackTrace();
        }

        FileInputStream serviceAccount = System.getProperty("os.name").equals("Linux") ? new FileInputStream("/root/Aleph/serviceAccount.json")
                : new FileInputStream("C:\\Users\\jonas\\OneDrive\\Skrivebord\\AdvancedModerationBot\\serviceAccount.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://aleph-120b6.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);


    }

    public static Firestore getDatabase() {
        return FirestoreClient.getFirestore();
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}
