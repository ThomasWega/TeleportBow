package me.wega.teleportbow.commands;

import me.wega.teleportbow.utilities.BowUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GiveBowCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (player.hasPermission("wega.bow")) {
                if (args.length == 0) {
                    if (!player.getInventory().contains(Material.BOW)) {
                        ItemStack bow = BowUtils.createBow();
                        player.getInventory().addItem(bow);
                        player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                        player.sendMessage(ChatColor.GREEN + "You have been given yourself a teleport bow");
                    } else if (player.getInventory().contains(Material.BOW)) {
                        player.sendMessage(ChatColor.RED + "You already have a teleport bow!");
                    }
                } else if (args.length == 1) {
                    if (player.hasPermission("wega.admin")) {
                        Player target = Bukkit.getPlayer(args[0]);

                        if (target == null) {
                            player.sendMessage(ChatColor.RED + "Player was not found!");
                            return true;
                        }
                        if (!target.getInventory().contains(Material.BOW)) {
                            ItemStack bow = BowUtils.createBow();
                            target.getInventory().addItem(bow);
                            target.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                            target.sendMessage(ChatColor.YELLOW + "You have been given a teleport bow by " + player.getName());
                            player.sendMessage(ChatColor.GREEN + "You have given a teleport bow to " + target.getName());
                        } else if (target.getInventory().contains(Material.BOW)) {
                            player.sendMessage(ChatColor.RED + target.getName() + " already has a teleport bow!");
                        }
                    } else if (!player.hasPermission("wega.admin")) {
                        player.sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
                    }
                }
            } else if (!player.hasPermission("wega.fly")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to execute this command!");
            }
        }

        return true;
    }
}
