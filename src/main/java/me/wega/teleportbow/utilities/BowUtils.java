package me.wega.teleportbow.utilities;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BowUtils {

    public static ItemStack createBow(){

        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.displayName(Component.text(ChatColor.YELLOW + "Teleport Bow"));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(ChatColor.GRAY + "Shoot this bow"));
        lore.add(Component.text(ChatColor.GRAY + "and get teleported"));
        lore.add(Component.text(ChatColor.GRAY + "to where it lands!"));
        bowMeta.lore(lore);
        bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        bowMeta.setUnbreakable(true);
        bowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        bow.setItemMeta(bowMeta);

        return bow;
    }
}
