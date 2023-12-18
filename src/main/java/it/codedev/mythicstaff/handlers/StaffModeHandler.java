package it.codedev.mythicstaff.handlers;

import com.google.common.collect.Lists;
import it.codedev.mythicstaff.MythicStaff;
import it.codedev.mythicstaff.utilities.C;
import it.codedev.mythicstaff.utilities.ItemType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.*;

public class StaffModeHandler {
    public Collection<UUID> staffModePlayers = new HashSet<>();
    public HashMap<String, ItemStack[]> staffInvs = new HashMap<>();
    private final FileConfiguration config = MythicStaff.getInstance().getYamlFile().getConfig();

    public void setStaffMode(Player player) {
        staffModePlayers.add(player.getUniqueId());
        staffInvs.put(player.getUniqueId().toString(), player.getInventory().getContents());
        player.getInventory().clear();

        loadStaffItems(player);
    }

    public void removeStaffMode(Player player) {
        player.getInventory().clear();
        if (staffInvs.containsKey(player.getUniqueId().toString())) {
            player.getInventory().setContents(staffInvs.get(player.getUniqueId().toString()));
        }
        staffModePlayers.remove(player.getUniqueId());
    }

    public boolean checkStaffMode(Player player) {
        return staffModePlayers.contains(player.getUniqueId());
    }

    public void saveInventory() {
        for (Map.Entry<String, ItemStack[]> entry : staffInvs.entrySet()) {
            MythicStaff.getInstance().getYamlFile().getStaffMode().set("staff-invs." + entry.getKey(), entry.getValue());
            MythicStaff.getInstance().getYamlFile().saveFile(MythicStaff.getInstance().getYamlFile().getStaffMode(), new File(MythicStaff.getInstance().getDataFolder(), "staffmode.yml"));
        }
    }

    public void restoreInventory() {
        ConfigurationSection section = MythicStaff.getInstance().getYamlFile().getStaffMode().getConfigurationSection("staff-invs");
        if (section == null) {
            return;
        }
        section.getKeys(false).forEach(key -> {
            ItemStack[] content = ((List<ItemStack>) MythicStaff.getInstance().getYamlFile().getStaffMode().get("staff-invs." + key)).toArray(new ItemStack[0]);
            staffModePlayers.add(UUID.fromString(key));
            staffInvs.put(key, content);
        });
        MythicStaff.getInstance().getYamlFile().getStaffMode().set("staff-invs", null);
    }

    public void loadStaffItems(Player player) {
        if (isEnabled(ItemType.RANDOM_TP)) {
            player.getInventory().setItem(config.getInt("staffmode-items.random-tp.slot"), getStaffItem(ItemType.RANDOM_TP));
        }
        if (isEnabled(ItemType.FLY)) {
            player.getInventory().setItem(config.getInt("staffmode-items.fly-mode.slot"), getStaffItem(ItemType.FLY));
        }
        if(isEnabled(ItemType.VANISH)) {
            player.getInventory().setItem(config.getInt("staffmode-items.vanish-mode.slot"), getStaffItem(ItemType.VANISH));
        }
        if(isEnabled(ItemType.INVSEE)) {
            player.getInventory().setItem(config.getInt("staffmode-items.invsee-mode.slot"), getStaffItem(ItemType.INVSEE));
        }
        if(isEnabled(ItemType.EXIT)) {
            player.getInventory().setItem(config.getInt("staffmode-items.exit.slot"), getStaffItem(ItemType.EXIT));
        }
    }

    public ItemStack getStaffItem(ItemType type) {
        switch (type) {
            case RANDOM_TP:
                Material material1 = Material.valueOf(config.getString("staffmode-items.random-tp.type"));
                if(material1 == null) {
                    Bukkit.getLogger().warning("Material not found. (Random TP Item)");
                    return null;
                }
                ItemStack item1 = new ItemStack(material1);
                ItemMeta meta1 = item1.getItemMeta();

                meta1.setDisplayName(C.color(config.getString("staffmode-items.random-tp.displayName")));
                List<String> lore1 = Lists.newArrayList();
                config.getStringList("staffmode-items.random-tp.lore").forEach(s -> lore1.add(C.color(s)));
                meta1.setLore(lore1);

                item1.setItemMeta(meta1);
                return item1;
            case FLY:
                Material material2 = Material.valueOf(config.getString("staffmode-items.fly-mode.type"));
                if(material2 == null) {
                    Bukkit.getLogger().warning("Material not found. (Fly Mode Item)");
                    return null;
                }
                ItemStack item2 = new ItemStack(material2);
                ItemMeta meta2 = item2.getItemMeta();

                meta2.setDisplayName(C.color(config.getString("staffmode-items.fly-mode.displayName")));
                List<String> lore2 = Lists.newArrayList();
                config.getStringList("staffmode-items.fly-mode.lore").forEach(s -> lore2.add(C.color(s)));
                meta2.setLore(lore2);

                item2.setItemMeta(meta2);
                return item2;
            case VANISH:
                Material material3 = Material.valueOf(config.getString("staffmode-items.vanish-mode.type"));
                if(material3 == null) {
                    Bukkit.getLogger().warning("Material not found. (Vanish Mode Item)");
                    return null;
                }
                ItemStack item3 = new ItemStack(material3);
                ItemMeta meta3 = item3.getItemMeta();

                meta3.setDisplayName(C.color(config.getString("staffmode-items.vanish-mode.displayName")));
                List<String> lore3 = Lists.newArrayList();
                config.getStringList("staffmode-items.vanish-mode.lore").forEach(s -> lore3.add(C.color(s)));
                meta3.setLore(lore3);

                item3.setItemMeta(meta3);
                return item3;
            case INVSEE:
                Material material4 = Material.valueOf(config.getString("staffmode-items.invsee-mode.type"));
                if(material4 == null) {
                    Bukkit.getLogger().warning("Material not found. (Vanish Mode Item)");
                    return null;
                }
                ItemStack item4 = new ItemStack(material4);
                ItemMeta meta4 = item4.getItemMeta();

                meta4.setDisplayName(C.color(config.getString("staffmode-items.invsee-mode.displayName")));
                List<String> lore4 = Lists.newArrayList();
                config.getStringList("staffmode-items.invsee-mode.lore").forEach(s -> lore4.add(C.color(s)));
                meta4.setLore(lore4);

                item4.setItemMeta(meta4);
                return item4;
            case EXIT:
                Material material5 = Material.valueOf(config.getString("staffmode-items.exit.type"));
                if(material5 == null) {
                    Bukkit.getLogger().warning("Material not found. (Vanish Mode Item)");
                    return null;
                }
                ItemStack item5 = new ItemStack(material5);
                ItemMeta meta5 = item5.getItemMeta();

                meta5.setDisplayName(C.color(config.getString("staffmode-items.exit.displayName")));
                List<String> lore5 = Lists.newArrayList();
                config.getStringList("staffmode-items.exit.lore").forEach(s -> lore5.add(C.color(s)));
                meta5.setLore(lore5);

                item5.setItemMeta(meta5);
                return item5;
        }

        return new ItemStack(Material.BARRIER);
    }

    public boolean isEnabled(ItemType type) {
        switch (type) {
            case RANDOM_TP:
                return config.getBoolean("staffmode-items.random-tp.enabled");
            case FLY:
                return config.getBoolean("staffmode-items.fly-mode.enabled");
            case VANISH:
                return config.getBoolean("staffmode-items.vanish-mode.enabled");
            case INVSEE:
                return config.getBoolean("staffmode-items.invsee-mode.enabled");
            case EXIT:
                return config.getBoolean("staffmode-items.exit.enabled");
        }
        return true;
    }
}