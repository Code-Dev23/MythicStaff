package it.codedev.mythicstaff;

import com.google.common.collect.Lists;
import it.codedev.mythicstaff.commands.impl.FlyCommand;
import it.codedev.mythicstaff.commands.impl.MainCommand;
import it.codedev.mythicstaff.commands.impl.gamemodes.GmcCommand;
import it.codedev.mythicstaff.commands.impl.gamemodes.GmsCommand;
import it.codedev.mythicstaff.commands.impl.gamemodes.GmspCommand;
import it.codedev.mythicstaff.commands.impl.other.StaffModeCommand;
import it.codedev.mythicstaff.commands.impl.other.VanishCommand;
import it.codedev.mythicstaff.commands.inv.InvseeCommand;
import it.codedev.mythicstaff.commands.inv.OpenEnderCommand;
import it.codedev.mythicstaff.handlers.StaffHandler;
import it.codedev.mythicstaff.handlers.StaffModeHandler;
import it.codedev.mythicstaff.listeners.StaffModeListeners;
import it.codedev.mythicstaff.utilities.YamlFile;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class MythicStaff extends JavaPlugin {

    @Getter
    private static MythicStaff instance;
    @Getter
    private YamlFile yamlFile;
    @Getter
    private StaffModeHandler staffModeManager;
    @Getter
    private StaffHandler staffManager;
    @Getter
    private final List<Player> staffModes = Lists.newArrayList();
    @Getter
    private final List<Player> vanished = Lists.newArrayList();

    @Override
    public void onEnable() {
        instance = this;
        reloadConfigurations();
        loadHandlers();
        getLogger().info("MythicStaff has been enabled!");

        loadCommands();
        loadListeners();
    }

    @Override
    public void onDisable() {
        if (!staffModeManager.staffInvs.isEmpty()) {
            staffModeManager.saveInventory();
        }
        getLogger().info("MythicStaff has been disabled!");
    }

    public void loadCommands() {
        new GmcCommand();
        new GmsCommand();
        new GmspCommand();

        new VanishCommand();
        new FlyCommand();

        new InvseeCommand();
        new OpenEnderCommand();

        new StaffModeCommand();
        new MainCommand();
    }

    public void loadListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new StaffModeListeners(), this);
    }

    public void loadHandlers() {
        staffModeManager = new StaffModeHandler();
        staffModeManager.restoreInventory();
        staffManager = new StaffHandler();
    }

    public void reloadConfigurations() {
        yamlFile = new YamlFile();
    }
}