package it.codedev.mythicstaff.listeners;

import it.codedev.mythicstaff.MythicStaff;
import it.codedev.mythicstaff.handlers.StaffHandler;
import it.codedev.mythicstaff.handlers.StaffModeHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class StaffModeListeners implements Listener {
    private final StaffModeHandler staffMode = MythicStaff.getInstance().getStaffModeManager();
    private final StaffHandler staffManager = MythicStaff.getInstance().getStaffManager();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null)
            return;
        if (!staffMode.checkStaffMode(player))
            return;
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            switch (item.getType()) {
                case BARRIER:
                    staffManager.toggleStaffMode(player);
                    break;
                case COMPASS:
                    staffManager.randomTP(player);
                    break;
                case FEATHER:
                    staffManager.toggleFly(player);
                    break;
                case BLAZE_ROD:
                    staffManager.toggleVanish(player);
                    break;
            }
            if(!MythicStaff.getInstance().getVanished().contains(player)) {
                event.setCancelled(true);
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (!staffMode.checkStaffMode(player))
            return;
        if (!(event.getRightClicked() instanceof Player)) return;
        Player target = (Player) event.getRightClicked();
        if (!player.getInventory().getItemInHand().getType().equals(Material.BOOK))
            return;
        player.openInventory(target.getInventory());
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;
        Player player = (Player) event.getEntity();

        if (!staffMode.checkStaffMode(player) || !MythicStaff.getInstance().getVanished().contains(player))
            return;

        event.setCancelled(true);
    }


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(event.getEntity() == null || event.getDamager() == null) return;
        Player player = (Player) event.getEntity();
        if(!staffMode.checkStaffMode(player) || !MythicStaff.getInstance().getVanished().contains(player))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(!staffMode.checkStaffMode(player) || !MythicStaff.getInstance().getVanished().contains(player))
            return;

        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(!staffMode.checkStaffMode(player) || !MythicStaff.getInstance().getVanished().contains(player))
            return;

        event.setCancelled(true);
    }
}