package cc.leafed.core.account;

import cc.leafed.core.Core;
import cc.leafed.core.MiniPlugin;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.database.Database;
import cc.leafed.core.util.F;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CoreClientManager extends MiniPlugin {
    private Database database;
    private Map<String, Player> _DisguisedPlayers = new HashMap<>();

    public CoreClientManager(Database database) {
        super("Client Manager");
        this.database = database;
    }

    private Map<Player, CoreClient> _CoreClients = new HashMap<>();

    // Use this to get a CoreClient object from Bukkit player
    public CoreClient Get(Player player) {
        return _CoreClients.get(player);
    }

    public boolean Exists(OfflinePlayer player) throws SQLException {
        ResultSet check = database.query("SELECT * FROM `accounts` WHERE `uuid` = '" + player.getUniqueId() + "';");
        int i = 0;
        while(check.next()) {
            i++;
        }
        // There was no account data found
        if(i == 0) {
            return false;
        } return true;
    }

    public void updateRank(PermissionGroup group, OfflinePlayer player) throws SQLException {
        if(player.isOnline()) {
            _CoreClients.get(player).setRank(group);
            Player online = (Player) player;
            //online.sendMessage("§eYour rank has been updated to " + group.getPrefixForChat() + "§e!");
            online.sendMessage(F.main(getName(), "§7Your rank has been updated to " + group.getName() + "!"));
        }// UPDATE `accounts` SET `rank` = 'PLAYER' WHERE `accounts`.`id` = 3;
        database.update("UPDATE `accounts` SET `rank` = '" + group.toString() + "' WHERE `uuid` = '" + player.getUniqueId() + "';");
    }

    public PermissionGroup getOfflineRank(OfflinePlayer player) throws SQLException {
        PermissionGroup per = null;
        ResultSet pull = database.query("SELECT * FROM `accounts` WHERE `uuid` = '" + player.getUniqueId() + "';");
        while(pull.next()) {
            per = PermissionGroup.valueOf(pull.getString("rank"));
        }
        return per;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerConnect(PlayerJoinEvent event) {
        // Make sure that we run it async that way logging players in, doesnt lag the main thread lol
        new BukkitRunnable() {
            @Override
            public void run() {

                CoreClient client = new CoreClient(event.getPlayer());
                // Then pull data from MySQL, update it in the client variable, then finally put it in the hashmap
                // Check if the player has an account at all
                try {
                    ResultSet check = database.query("SELECT * FROM `accounts` WHERE `uuid` = '" + event.getPlayer().getUniqueId() + "';");
                    int i = 0;
                    while(check.next()) {
                        i++;
                    }

                    // There was no account data found
                    if(i == 0) {
                        event.getPlayer().sendMessage(ChatColor.GOLD + Core.getCore().getConfig().getString("serverstatus.first-message"));
                        database.update("INSERT INTO `accounts` (`username`, `uuid`, `rank`) VALUES ('" + event.getPlayer().getName() + "', '" + event.getPlayer().getUniqueId() + "', 'PLAYER');");
                        // Then just put an exact duplicate of that data into the CoreClient
                        client.setRank(PermissionGroup.PLAYER);
                    } else {
                        // We did find some data
                        ResultSet pull = database.query("SELECT * FROM `accounts` WHERE `uuid` = '" + event.getPlayer().getUniqueId() + "';");
                        while(pull.next()) {
                            client.setRank(PermissionGroup.valueOf(pull.getString("rank")));
                        }
                    }
                } catch (SQLException e) {
                    event.getPlayer().kickPlayer(ChatColor.RED + "There was a problem logging you in. Please try again later.");
                    return;
                }

                _CoreClients.put(event.getPlayer(), client);
            }
        }.runTaskAsynchronously(Core.getCore());
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event) {
        event.setQuitMessage("§8[§c-§8] §7" + event.getPlayer().getName());
        _CoreClients.remove(event.getPlayer());
        // Make sure to also remove their entry from _DisguisedPlayers as well when they leave!!!
        _DisguisedPlayers.remove(event.getPlayer());
    }

    public void undisguise(Player caller) {
        Get(caller).setDisguised(false);
        Get(caller).setUsername(caller.getName());
    }
    public void disguise(Player caller, String name) {
        Get(caller).setDisguised(true);
        Get(caller).setUsername(name);
        _DisguisedPlayers.put(name, caller);
    }
    public Player getDisguisedPlayer(String disguisedName) {
        if(_DisguisedPlayers.containsKey(disguisedName)) {
            return _DisguisedPlayers.get(disguisedName);
        }
        return null;
    }
}
