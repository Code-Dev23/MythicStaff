package it.codedev.mythicstaff.commands.impl;

import it.codedev.mythicstaff.commands.Command;
import it.codedev.mythicstaff.utilities.C;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FlyCommand extends Command {
    public FlyCommand() {
        super("fly", "mythicstaff.command.fly", new String[]{"flight"}, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                C.sendMessage(player, "&cFly disabled.");
                return;
            }
            player.setAllowFlight(true);
            C.sendMessage(player, "&aFly enabled.");
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            C.sendMessage(player, "&cPlayer not found.");
            return;
        }
        if (target.getAllowFlight()) {
            target.setAllowFlight(false);
            C.sendMessage(player, ("&cFly disabled for %player%.").replace("%player%", target.getName()));
            return;
        }
        target.setAllowFlight(true);
        C.sendMessage(player, ("&aFly enabled for %player%.").replace("%player%", target.getName()));
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