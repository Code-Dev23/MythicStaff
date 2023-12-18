package it.codedev.mythicstaff.commands.impl.other;

import it.codedev.mythicstaff.MythicStaff;
import it.codedev.mythicstaff.commands.Command;
import it.codedev.mythicstaff.utilities.C;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class VanishCommand extends Command {
    public VanishCommand() {
        super("vanish", "mythicstaff.command.vanish", new String[]{"v"}, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (MythicStaff.getInstance().getVanished().contains(player)) {
            MythicStaff.getInstance().getVanished().remove(player);
            C.sendMessage(player, "&cVanish disabled.");
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.showPlayer(player);
            }
            for (Player p : MythicStaff.getInstance().getVanished()) {
                player.hidePlayer(p);
            }
            return;
        }
        MythicStaff.getInstance().getVanished().add(player);
        C.sendMessage(player, "&aVanish enabled.");
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(player);
        }
        for (Player p : MythicStaff.getInstance().getVanished()) {
            player.showPlayer(p);
            p.showPlayer(player);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
