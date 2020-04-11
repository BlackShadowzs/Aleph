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

public class GuildObject {
    public final String id;
    public final String owner;
    public final String ownerId;
    public final String prefix;
    public final String language;
    public final String logs;
    public final String adminRole;
    public final String adminPerm;
    public final String modRole;
    public final String modPerm;

    public GuildObject(String guildId, String owner, String ownerId, String prefix, String language, String logs, String adminRole, String adminPerm, String modRole, String modPerm) {
        this.id = guildId;
        this.owner = owner;
        this.ownerId = ownerId;
        this.prefix = prefix;
        this.language = language;
        this.logs = logs;
        this.adminRole = adminRole;
        this.adminPerm = adminPerm;
        this.modRole = modRole;
        this.modPerm = modPerm;
    }
}
