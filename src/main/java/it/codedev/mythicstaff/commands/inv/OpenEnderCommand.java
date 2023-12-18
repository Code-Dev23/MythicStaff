package it.codedev.mythicstaff.commands.inv;

import it.codedev.mythicstaff.commands.Command;
import it.codedev.mythicstaff.utilities.C;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class OpenEnderCommand extends Command {
    public OpenEnderCommand() {
        super("openender", "mythicstaff.command.openender", new String[]{"openec", "ecsee"}, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            C.sendMessage(player, "&cUsage: /openender <player>");
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            C.sendMessage(player, "&cPlayer not found.");
            return;
        }
        player.openInventory(target.getEnderChest());
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
