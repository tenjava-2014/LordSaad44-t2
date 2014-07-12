package com.tenjava.entries.LordSaad44.t2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TenJava extends JavaPlugin {

    public void onEnable() {
        System.out.println("==============");
        System.out.println("Creeper Energy by LordSaad44");
        System.out.println("");
        System.out.println("The plugin has been enabled successfully");
        System.out.println("==============");
    }

    public void onDisable() {
        System.out.println("==============");
        System.out.println("Creeper Energy by LordSaad44");
        System.out.println("");
        System.out.println("The plugin has been disabled successfully");
        System.out.println("==============");
    }

    @EventHandler
    public void onCreeperKill(EntityDeathEvent event) {
        if (event.getEntity() instanceof Creeper) {
            Creeper creeper = (Creeper) event.getEntity();
            if (creeper.getKiller() != null) {
                Random rnd = new Random();
                int randomnumber = rnd.nextInt(2);
                if (randomnumber == 2) {
                    Location loc = creeper.getLocation();
                    ItemStack item = new ItemStack(Material.SULPHUR);
                    ItemMeta meta = item.getItemMeta();
                    meta.addEnchant(Enchantment.LURE, 1, true);
                    meta.setDisplayName(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Creeper Stone");
                    List<String> lore = new ArrayList<String>();
                    lore.add(ChatColor.YELLOW + "Use me like redstone or use me for fuel.");
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    Bukkit.getServer().getWorld(creeper.getWorld().getUID()).dropItemNaturally(loc, item);
                }
            }
        }
    }

    @EventHandler
    public void onRightClickBlock(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType().isSolid()) {
                if (!event.getClickedBlock().getType().isTransparent()) {
                    if (event.getPlayer().getItemInHand().getType() == Material.SULPHUR) {
                        if (event.getPlayer().getItemInHand().hasItemMeta()) {
                            if (event.getPlayer().getItemInHand().getItemMeta().hasEnchant(Enchantment.LURE)) {
                                int x = event.getClickedBlock().getX();
                                int y = event.getClickedBlock().getY();
                                int z = event.getClickedBlock().getZ();
                                Location loc = new Location(Bukkit.getWorld(event.getPlayer().getWorld().getName()), x, y, z);
                                if (Bukkit.getWorld(event.getPlayer().getWorld().getName()).getBlockAt(loc).getRelative(BlockFace.UP).getType() == Material.AIR) {
                                    Bukkit.getWorld(loc.getWorld().getName()).getBlockAt(loc).getRelative(BlockFace.UP).setType(Material.REDSTONE);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

