package me.wega.teleportbow.listeners;

import me.wega.teleportbow.Main;
import me.wega.teleportbow.utilities.BowUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public record BowListener(Main main) implements Listener {

    @EventHandler
    public void onArrowLand(ProjectileHitEvent event) {
        if (event.getEntity().getShooter() instanceof Player player) {
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            if (itemInMainHand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Teleport Bow")) {

                Location location = event.getEntity().getLocation();
                location.setYaw(player.getLocation().getYaw());
                location.setPitch(player.getLocation().getPitch());
                player.teleport(location);
                event.getEntity().remove();
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
            }
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (main.getConfig().getBoolean("give-on-join")) {
            Player player = event.getPlayer();
            if (!player.getInventory().contains(Material.BOW)){
                player.getInventory().addItem(BowUtils.createBow());
                player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
            }else if (player.getInventory().contains(Material.BOW)){
                player.sendMessage(ChatColor.GRAY + "You already have a teleport bow!");
            }
        }
    }
    @EventHandler
    public void onBowDrop(PlayerDropItemEvent event){
        if (event.getItemDrop().getItemStack().getType().equals(Material.BOW) || event.getItemDrop().getItemStack().getType().equals(Material.ARROW)) {
            event.setCancelled(true);
        }
    }
}
