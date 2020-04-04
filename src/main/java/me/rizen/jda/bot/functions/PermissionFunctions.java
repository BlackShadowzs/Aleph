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

import me.rizen.jda.bot.config.Config;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static me.rizen.jda.bot.database.DatabaseFunctions.*;


public class PermissionFunctions {
    private static final ArrayList<String> ids = new ArrayList<>();

    public static ArrayList<String> getDevs() {
        return ids;
    }


    public static void addDev (String devID) {
        if (!getDevs().contains(devID)) {
            getDevs().add(devID);
        }
    }
    public static void removeDev (String devID) {
        if (getDevs().contains(devID)) {
            getDevs().remove(devID);
        }
    }
    public static boolean isAdmin (Member member) throws ExecutionException, InterruptedException {
        String admin_permission = getServerAdminInfo(member.getGuild().getId(), "perm");
        String admin_role = getServerAdminInfo(member.getGuild().getId(), "role");

        List<String> roleIds = new ArrayList<>();
        member.getRoles().forEach((role) -> roleIds.add(role.getId()));
        return roleIds.toString().contains(admin_role) || member.getRoles().toString().toLowerCase().contains(admin_role) || member.getPermissions().toString().toLowerCase().contains(admin_permission) || member.getUser().equals(member.getGuild().getOwner().getUser());
    }

    public static boolean isMod (Member member) throws ExecutionException, InterruptedException {
        String moderator_permission = getServerModInfo(member.getGuild().getId(), "perm");
        String moderator_role = getServerModInfo(member.getGuild().getId(), "role");

        List<String> roleIds = new ArrayList<>();
        member.getRoles().forEach((role) -> roleIds.add(role.getId()));
        return roleIds.toString().contains(moderator_role) || member.getRoles().toString().toLowerCase().contains(moderator_role) || member.getPermissions().toString().toLowerCase().contains(moderator_permission) || member.getUser().equals(member.getGuild().getOwner().getUser());
    }

    public static boolean isGuildOwner (Guild guild, Member member) {
        return guild.getOwner().getUser() == member.getUser();
    }

    public static boolean isBotOwner (String id) {
        return id.equals(Config.getInstance().getString("ownerID"));
    }

    public static boolean botCanInteract (Member selfMember, Member member) {
        return selfMember.canInteract(member);
    }
}