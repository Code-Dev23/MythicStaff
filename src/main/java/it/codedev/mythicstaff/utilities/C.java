package it.codedev.mythicstaff.utilities;

import it.codedev.mythicstaff.MythicStaff;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@UtilityClass
public class C {
    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }

    public String fromConfig(String path) {
        return color(MythicStaff.getInstance().getYamlFile().getMessages().getString(path));
    }
}