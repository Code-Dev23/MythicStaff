package it.codedev.mythicstaff.commands.impl.other;

import it.codedev.mythicstaff.MythicStaff;
import it.codedev.mythicstaff.commands.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class StaffModeCommand extends Command {
    public StaffModeCommand() {
        super("staffmode", "mythicstaff.command.staffmode", new String[]{"staff"}, false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        MythicStaff.getInstance().getStaffManager().toggleStaffMode(player);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}