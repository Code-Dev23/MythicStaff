package it.codedev.mythicstaff.commands.impl.gamemodes;

import it.codedev.mythicstaff.commands.Command;
import it.codedev.mythicstaff.utilities.C;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GmspCommand extends Command {
    public GmspCommand() {
        super("gmsp", "mythicstaff.command.gmsp", new String[]{"spectator"}, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                player.setGameMode(GameMode.SURVIVAL);
                C.sendMessage(player, "&aGmsp enabled.");
                return;
            }
            player.setGameMode(GameMode.SPECTATOR);
            C.sendMessage(player, "&aGmsp disabled.");
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            C.sendMessage(player, "&cPlayer not found.");
            return;
        }
        if (target.getGameMode().equals(GameMode.SPECTATOR)) {
            target.setGameMode(GameMode.SURVIVAL);
            C.sendMessage(player, ("&aGmsp enabled for %player%.").replace("%player%", target.getName()));
            return;
        }
        target.setGameMode(GameMode.SPECTATOR);
        C.sendMessage(player, ("&aGmsp disabled for %player%.").replace("%player%", target.getName()));
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
