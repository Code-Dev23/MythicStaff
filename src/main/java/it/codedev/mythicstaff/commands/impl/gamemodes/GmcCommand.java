package it.codedev.mythicstaff.commands.impl.gamemodes;

import it.codedev.mythicstaff.commands.Command;
import it.codedev.mythicstaff.utilities.C;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GmcCommand extends Command {
    public GmcCommand() {
        super("gmc", "mythicstaff.command.gmc", new String[]{"creative"}, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            if (player.getGameMode().equals(GameMode.CREATIVE)) {
                player.setGameMode(GameMode.SURVIVAL);
                C.sendMessage(player, "Gmc disabled.");
                return;
            }
            player.setGameMode(GameMode.CREATIVE);
            C.sendMessage(player, "Gmc enabled.");
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            C.sendMessage(player, "Player not found.");
            return;
        }
        if (target.getGameMode().equals(GameMode.CREATIVE)) {
            target.setGameMode(GameMode.SURVIVAL);
            C.sendMessage(player, ("Gmc disabled for %player%.").replace("%player%", target.getName()));
            return;
        }
        target.setGameMode(GameMode.CREATIVE);
        C.sendMessage(player, ("Gmc enabled for %player%.").replace("%player%", target.getName()));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> result = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            result.add(player.getName());
        }
        return result;
    }
}
