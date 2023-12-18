package it.codedev.mythicstaff.commands.impl;

import it.codedev.mythicstaff.MythicStaff;
import it.codedev.mythicstaff.commands.Command;
import it.codedev.mythicstaff.utilities.C;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class MainCommand extends Command {
    public MainCommand() {
        super("mythicstaff", "mythicstaff.admin", new String[]{"ms", "staffmythic"}, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if(args.length == 0) {
            C.sendMessage(player, "&5• &fPlugin created by &dCodeDevv_ &5| &fVersion: &dv1.0 &7&o(/mythicstaff reload)");
            return;
        }
        if(args[0].equalsIgnoreCase("reload")) {
            MythicStaff.getInstance().reloadConfigurations();
            C.sendMessage(player, "&5• &aPlugin configurations reloaded!");
            return;
        }
        C.sendMessage(player, "&5• &fPlugin created by &dCodeDevv_ &5| &fVersion: &dv1.0");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return Arrays.asList("reload");
    }
}
