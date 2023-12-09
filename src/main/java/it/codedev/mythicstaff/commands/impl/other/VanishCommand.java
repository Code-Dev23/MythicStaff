package it.codedev.mythicstaff.commands.impl.other;

import com.google.common.collect.Lists;
import it.codedev.mythicstaff.commands.Command;
import it.codedev.mythicstaff.utilities.C;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class VanishCommand extends Command {
    private final List<Player> vanished = Lists.newArrayList();

    public VanishCommand() {
        super("vanish", "mythicstaff.command.vanish", new String[]{"v"}, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (vanished.contains(player)) {
            vanished.remove(player);
            C.sendMessage(player, "&cVanish disabled.");
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.showPlayer(player);
            }
            for (Player p : vanished) {
                player.hidePlayer(p);
            }
            return;
        }
        vanished.add(player);
        C.sendMessage(player, "&cVanish enabled.");
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(player);
        }
        for (Player p : vanished) {
            player.showPlayer(p);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
