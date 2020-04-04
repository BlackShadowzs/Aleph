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

package me.rizen.jda.bot.database;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import me.rizen.jda.bot.Main;
import net.dv8tion.jda.api.entities.Guild;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class DatabaseFunctions {
    public static Firestore getDatabase () {
        return Main.getDatabase();
    }

    public static void registerGuild(String guildId, String prefix, String serverOwnerName, String serverOwnerId) throws ExecutionException, InterruptedException {
        if (getDatabase().collection(guildId).document("guildConfig").get().get().exists()) {
            return;
        }
        GuildObject guild = new GuildObject(guildId, serverOwnerName, serverOwnerId, prefix, "1", "none", "Permission.BAN_MEMBERS", "none", "Permission.KICK_MEMBERS");
            getDatabase().collection(guildId).document("guildConfig").set(guild);

    }
    public static DocumentSnapshot getCustomCommand(Guild guild, String commandName) throws ExecutionException, InterruptedException {
        Firestore database = getDatabase();

        return database.collection(guild.getId()).document(commandName.toLowerCase()+"Command").get().get();
    }

    public static List<String> getCustomCommands (Guild guild) {
        Firestore database = getDatabase();
        List<String> customCommandsList = new ArrayList<>();
        database.collection(guild.getId()).listDocuments().iterator().forEachRemaining(
                (p) -> {
                    if(p.getId().contains("Command")) {
                        try {
                            customCommandsList.add(p.get().get().getString("invoke"));
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        return customCommandsList;
    }


    public static void createCase (String collectionName, String documentName, CaseObject caseObject) throws ExecutionException, InterruptedException {
        if (getDatabase().collection(collectionName).document(documentName).get().get().exists()) {
            return;
        }
        getDatabase().collection(collectionName).document(documentName).set(caseObject);
    }

    public static void createCustomCommand(String guildId, String invoke, String message, String help) {
        CustomCommand commandObj = new CustomCommand(invoke, message, help);
        getDatabase().collection(guildId).document(invoke+"Command").set(commandObj);
    }

    public static String getServerAdminInfo(String guildId, String roleOrPerm) throws ExecutionException, InterruptedException {
        final DocumentSnapshot guildConfig = getDatabase().collection(guildId).document("guildConfig").get().get();

        if (roleOrPerm.equalsIgnoreCase("perm")) {
            return guildConfig.getString("adminPerm").replace("Permission.", "").toLowerCase();
        } else {
            return guildConfig.getString("adminRole").toLowerCase();
        }
    }

    public static String getServerModInfo(String guildId, String roleOrPerm) throws ExecutionException, InterruptedException {
        final DocumentSnapshot guildConfig = getDatabase().collection(guildId).document("guildConfig").get().get();

        if (roleOrPerm.equalsIgnoreCase("perm")) {
            return guildConfig.getString("modPerm").replace("Permission.", "").toLowerCase();
        } else {
            return guildConfig.getString("modRole").toLowerCase();
        }
    }
}