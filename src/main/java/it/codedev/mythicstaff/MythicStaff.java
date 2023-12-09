package it.codedev.mythicstaff;

import it.codedev.mythicstaff.utilities.YamlFile;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class MythicStaff extends JavaPlugin {

    @Getter
    private static MythicStaff instance;
    @Getter
    private YamlFile yamlFile;

    @Override
    public void onEnable() {
        instance = this;
        yamlFile = new YamlFile();
        getLogger().info("MythicStaff has been enabled!");

        loadCommands();
        loadListeners();
        loadOthers();
    }

    @Override
    public void onDisable() {

    }

    public void loadCommands() {

    }

    public void loadListeners() {

    }

    public void loadOthers() {

    }

    public void reloadConfigurations() {
        yamlFile = new YamlFile();
    }
}