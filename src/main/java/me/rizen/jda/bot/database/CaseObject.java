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

public class CaseObject{

    public final String targetName;
    public final String targetId;
    public final String punisherName;
    public final String punisherId;
    public final String reasonForWarn;
    public final String action;


    public CaseObject (String targetName, String targetId, String punisherName, String punisherId, String reasonForWarn, String action) {
        this.targetName = targetName;
        this.targetId = targetId;
        this.punisherName = punisherName;
        this.punisherId = punisherId;
        this.reasonForWarn = reasonForWarn;
        this.action = action;
    }

}