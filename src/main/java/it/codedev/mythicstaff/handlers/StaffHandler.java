package it.codedev.mythicstaff.handlers;

import it.codedev.mythicstaff.MythicStaff;
import it.codedev.mythicstaff.utilities.C;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class StaffHandler {
    public void toggleVanish(Player player) {
        if (MythicStaff.getInstance().getVanished().contains(player)) {
            MythicStaff.getInstance().getVanished().remove(player);
            C.sendMessage(player, "&cVanish disabled.");
            return;
        }
        C.sendMessage(player, "&aVanish enabled.");
        MythicStaff.getInstance().getVanished().add(player);
    }

    public void toggleFly(Player player) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            C.sendMessage(player, "&cFly disabled.");
            return;
        }
        C.sendMessage(player, "&aFly enabled.");
        player.setAllowFlight(true);
    }

    public void toggleStaffMode(Player player) {
        if (MythicStaff.getInstance().getStaffModeManager().checkStaffMode(player)) {
            MythicStaff.getInstance().getStaffModeManager().removeStaffMode(player);
            C.sendMessage(player, "&cStaffmode disabled.");
            return;
        }
        MythicStaff.getInstance().getStaffModeManager().setStaffMode(player);
        C.sendMessage(player, "&aStaffmode enabled.");
    }

    public void randomTP(Player player) {
        Random random = new Random();
        int x = random.nextInt(Bukkit.getOnlinePlayers().size());
        Player player1 = Bukkit.getOnlinePlayers().stream().skip(x).findFirst().orElse(null);
        player.teleport(player1.getLocation());
        C.sendMessage(player, ("&eRandom teleported to %player%!").replace("%player%", player1.getName()));
    }
}