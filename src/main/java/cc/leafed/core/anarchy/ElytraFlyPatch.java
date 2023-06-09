package cc.leafed.core.anarchy;

import cc.leafed.core.Core;
import cc.leafed.core.MiniPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ElytraFlyPatch extends MiniPlugin {

    /*
    This code was taken from https://github.com/IzDaBait/ElytraRoofPatch/blob/master/src/main/java/io/github/izdabait/rooflimiter/rooflimiter.java
    Thanks for all your hard work, rest in peace
     */


    // Speed Multiplier Double (Relaxed: 5.0, Default: 2.5, More strict: 2.0, Extremely Strict & Safe: 1.0) This is the maximum
    //# 	velocity a player can go. It is not measured in blocks per second but blocks per movement packet. A Firework does
    //#	not propel a user much more than 2.5, so it should be a safe value.
    double vdouble = Core.getCore().getConfig().getDouble("elytrafly.vdouble");
    boolean tp = Core.getCore().getConfig().getBoolean("elytrafly.tp");
    boolean sendspeed = Core.getCore().getConfig().getBoolean("elytrafly.sendspeed");;
    boolean sendmessage = Core.getCore().getConfig().getBoolean("elytrafly.sendmessage");;

    public ElytraFlyPatch() {
        super("Elytra Fly Patch");
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void elytraLagCheck(PlayerMoveEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                double TPS = Bukkit.getTPS()[0];
                Player p = event.getPlayer();
                if(p.isGliding()) {
                    // Keep tabs on the TPS
                    if(TPS <= 10.00) {
                        ItemStack copy = p.getEquipment().getChestplate();
                        p.getEquipment().getChestplate().subtract();
                        p.getWorld().dropItem(p.getLocation(), copy);
                        p.sendMessage("§cThe server is running below 10 ticks per second. Elytras are disabled.");
                    }
                }
            }
        }.runTaskAsynchronously(Core.getCore());
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void inElytra(PlayerMoveEvent playerMoveEvent) {
        Player p = playerMoveEvent.getPlayer();
        if (p.isGliding()) {
            Location loc = p.getLocation();
            if (Math.abs(playerMoveEvent.getFrom().getX() - playerMoveEvent.getTo().getX()) > vdouble) {
                playerMoveEvent.setCancelled(true);
                if (tp) {
                    double x = loc.getBlockX();
                    double y = loc.getBlockY();
                    y = (y - 1);
                    double z = loc.getBlockZ();
                    Location loc1 = new Location(p.getWorld(), x, y, z);
                    p.teleport(loc1);
                }
                if (sendmessage) {
                    p.sendMessage("§cYou are going too fast with that!");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10f, 6f);
                    if (sendspeed) {
                        p.sendMessage("Velocity: " + (Math.abs(playerMoveEvent.getFrom().getX() - playerMoveEvent.getTo().getX())) + " " + (Math.abs(playerMoveEvent.getFrom().getZ() - playerMoveEvent.getTo().getZ())));
                    }
                }
            }
            if (Math.abs(playerMoveEvent.getFrom().getZ() - playerMoveEvent.getTo().getZ()) > vdouble) {
                playerMoveEvent.setCancelled(true);
                if (tp) {
                    double x = loc.getBlockX();
                    double y = loc.getBlockY();
                    y = (y - 1);
                    double z = loc.getBlockZ();
                    Location loc1 = new Location(p.getWorld(), x, y, z);
                    p.teleport(loc1);
                }
                if (sendmessage) {
                    p.sendMessage("§cYou are going too fast with that!");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10f, 6f);
                    if (sendspeed) {
                        p.sendMessage("Velocity: " + (Math.abs(playerMoveEvent.getFrom().getX() - playerMoveEvent.getTo().getX())) + " " + (Math.abs(playerMoveEvent.getFrom().getZ() - playerMoveEvent.getTo().getZ())));
                    }
                }
            }
            if (Math.abs(playerMoveEvent.getFrom().getX() - playerMoveEvent.getTo().getX()) > vdouble) {
                playerMoveEvent.setCancelled(true);
                if (tp) {
                    double x = loc.getBlockX();
                    double y = loc.getBlockY();
                    y = (y - 1);
                    double z = loc.getBlockZ();
                    Location loc1 = new Location(p.getWorld(), x, y, z);
                    p.teleport(loc1);
                }
                if (sendmessage) {
                    p.sendMessage("§cYou are going too fast with that!");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10f, 6f);
                    if (sendspeed) {
                        p.sendMessage("Velocity: " + (Math.abs(playerMoveEvent.getFrom().getX() - playerMoveEvent.getTo().getX())) + " " + (Math.abs(playerMoveEvent.getFrom().getZ() - playerMoveEvent.getTo().getZ())));
                    }
                }
            }
            if (Math.abs(playerMoveEvent.getFrom().getZ() - playerMoveEvent.getTo().getZ()) > vdouble) {
                playerMoveEvent.setCancelled(true);
                if (tp) {
                    double x = loc.getBlockX();
                    double y = loc.getBlockY();
                    y = (y - 1);
                    double z = loc.getBlockZ();
                    Location loc1 = new Location(p.getWorld(), x, y, z);
                    p.teleport(loc1);
                }
                if (sendmessage) {
                    p.sendMessage("§cYou are going too fast with that!");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 10f, 6f);
                    if (sendspeed) {
                        p.sendMessage("Velocity: " + (Math.abs(playerMoveEvent.getFrom().getX() - playerMoveEvent.getTo().getX())) + " " + (Math.abs(playerMoveEvent.getFrom().getZ() - playerMoveEvent.getTo().getZ())));
                    }
                }
            }

        }
    }
}
