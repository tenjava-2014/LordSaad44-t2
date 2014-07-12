package com.tenjava.entries.LordSaad44.t2;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TenJava extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("==============");
        System.out.println("Creeper Energy by LordSaad44");
        System.out.println(" ");
        System.out.println("The plugin has been enabled successfully");
        System.out.println("==============");
    }

    public void onDisable() {
        System.out.println("==============");
        System.out.println("Creeper Energy by LordSaad44");
        System.out.println(" ");
        System.out.println("The plugin has been disabled successfully");
        System.out.println("==============");
    }

    @EventHandler
    public void onCreeperKill(EntityDeathEvent event) {
        if (event.getEntity() instanceof Creeper) {
            Creeper creeper = (Creeper) event.getEntity();
            if (creeper.getKiller() != null) {
                if (creeper.isPowered()) {
                    Location loc = creeper.getLocation();
                    ItemStack item = new ItemStack(Material.SULPHUR, 10);
                    ItemMeta meta = item.getItemMeta();
                    meta.addEnchant(Enchantment.LURE, 1, true);
                    meta.setDisplayName(ChatColor.DARK_RED + "Creeper Stone");
                    List<String> lore = new ArrayList<String>();
                    lore.add(ChatColor.YELLOW + "Use me like redstone or use me for fuel. I'm good energy.");
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    Bukkit.getServer().getWorld(creeper.getWorld().getUID()).dropItemNaturally(loc, item);
                } else {
                    Random rnd = new Random();
                    int num = rnd.nextInt(4);
                    if (num >= 3) {
                        Location loc = creeper.getLocation();
                        ItemStack item = new ItemStack(Material.SULPHUR);
                        ItemMeta meta = item.getItemMeta();
                        meta.addEnchant(Enchantment.LURE, 1, true);
                        meta.setDisplayName(ChatColor.DARK_RED + "Creeper Stone");
                        List<String> lore = new ArrayList<String>();
                        lore.add(ChatColor.YELLOW + "Use me like redstone or use me for fuel. I'm good energy.");
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        Bukkit.getServer().getWorld(creeper.getWorld().getUID()).dropItemNaturally(loc, item);
                    }
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
                                int y = event.getClickedBlock().getY() + 1;
                                int z = event.getClickedBlock().getZ();
                                Location loc = new Location(Bukkit.getWorld(event.getPlayer().getWorld().getName()), x, y, z);
                                if (Bukkit.getWorld(event.getPlayer().getWorld().getName()).getBlockAt(loc).getType() == Material.AIR) {
                                    World world = event.getClickedBlock().getWorld();
                                    world.getBlockAt(loc).setType(Material.REDSTONE_WIRE);
                                    ItemStack old = new ItemStack(event.getPlayer().getItemInHand().getType(), event.getPlayer().getItemInHand().getAmount() - 1);

                                    ItemMeta meta = old.getItemMeta();
                                    meta.addEnchant(Enchantment.LURE, 1, true);
                                    meta.setDisplayName(ChatColor.DARK_RED + "Creeper Stone");
                                    List<String> lore = new ArrayList<String>();
                                    lore.add(ChatColor.YELLOW + "Use me like redstone or use me for fuel. I'm good energy. Careful though, placing me will turn me into redstone.");
                                    meta.setLore(lore);
                                    old.setItemMeta(meta);

                                    event.getPlayer().setItemInHand(old);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void Furnace(FurnaceBurnEvent event) {
        if (event.getFuel().getType() == Material.SULPHUR) {
            if (event.getFuel().hasItemMeta()) {
                if (event.getFuel().getItemMeta().hasEnchant(Enchantment.LURE)) {
                    event.setBurnTime(10000);
                    event.setBurning(true);
                }
            }
        }
    }
}

