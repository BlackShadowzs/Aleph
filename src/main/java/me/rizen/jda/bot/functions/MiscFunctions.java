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

package me.rizen.jda.bot.functions;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static me.rizen.jda.bot.database.DatabaseFunctions.getDatabase;


public class MiscFunctions {
    /**
     * Generate Random Color
     *
     * @return Color
     */
    public static Color randomColour() {
        Random colourPicker = new Random();
        int red = colourPicker.nextInt(255) + 1;
        int green = colourPicker.nextInt(255) + 1;
        int blue = colourPicker.nextInt(255) + 1;
        return new Color(red, green, blue);
    }

    /**
     * Capitalise String
     *
     * @return String
     */
    public static String capitalise (String text) {
        String capitalLetter = text.substring(0,1).toUpperCase();
        return capitalLetter + text.substring(1);
    }
    public static String randomNumber (int amountOfDigits) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <= amountOfDigits; i++) {
            res.append(getRanNum());
        }
        return res.toString();
    }
    private static double getRanNum() {
        return Math.random() * (9 + 1);
    }

    public static String getPrefix (String guildId) throws ExecutionException, InterruptedException {
        String prefix = getDatabase().collection(guildId).document("guildConfig").get().get().getString("prefix");
        if (prefix == null) {
            return ";";
        }
        return prefix;
    }

    public static String getLogs (String guildId) throws ExecutionException, InterruptedException {
        String logs = getDatabase().collection(guildId).document("guildConfig").get().get().getString("logs");
        if (logs == null) {
            return "1";
        }
        return logs;
    }
}
