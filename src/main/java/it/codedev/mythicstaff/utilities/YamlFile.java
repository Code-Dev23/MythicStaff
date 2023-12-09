package it.codedev.mythicstaff.utilities;

import it.codedev.mythicstaff.MythicStaff;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@Getter
public class YamlFile {
    private final FileConfiguration config;
    private final FileConfiguration messages;

    public YamlFile() {
        this.config = saveConfig("config.yml");
        this.messages = saveConfig("messages.yml");
    }

    public void saveFile(FileConfiguration configuration, File file) {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration saveConfig(String configName) {
        File file = new File(MythicStaff.getInstance().getDataFolder(), configName);

        if (!file.exists()) {
            MythicStaff.getInstance().saveResource(configName, false);
        }

        return loadConfig(file);
    }

    public FileConfiguration loadConfig(File file) {
        YamlConfiguration configuration = new YamlConfiguration();

        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException ex) {
            ex.printStackTrace();
        }

        return configuration;
    }
}