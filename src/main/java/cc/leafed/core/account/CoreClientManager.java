package cc.leafed.core.account;

import cc.leafed.core.Core;
import cc.leafed.core.MiniPlugin;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.database.Database;
import cc.leafed.core.util.F;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoreClientManager extends MiniPlugin {
    private Database database;
    private Map<String, Player> _DisguisedPlayers = new HashMap<>();
    private List<Player> _VanishedPlayers = new ArrayList<>();


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
            // update their nametags too
            new BukkitRunnable() {
                public void run() {
                    // i dont know what the fuck is breaking
//                    removeTag(online);
//                    newTag(online);
//                    setNametags(online);
                }
            }.runTask(Core.getCore());
        }
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

    @EventHandler(priority = EventPriority.LOW)
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

                for(Player staff : _VanishedPlayers) {
                    CoreClient theirClient = Get(staff);
                    if(theirClient.getRank().getPermissionLevel() >= client.getRank().getPermissionLevel()) {
                        new BukkitRunnable() {
                            public void run() {
                                event.getPlayer().hidePlayer(Core.getCore(), staff);
                            }
                        }.runTask(Core.getCore());
                    }
                }

                // Setup nametags
                new BukkitRunnable() {
                    public void run() {
//                        setNametags(event.getPlayer());
//                        newTag(event.getPlayer());
                    }
                }.runTask(Core.getCore());

            }
        }.runTaskAsynchronously(Core.getCore());
        event.setJoinMessage("§7" + event.getPlayer().getName() + " joined the game.");
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event) {
        event.setQuitMessage("§7" + event.getPlayer().getName() + " left the game.");
        _CoreClients.remove(event.getPlayer());
        // Make sure to also remove their entry from _DisguisedPlayers as well when they leave!!!
        _DisguisedPlayers.remove(event.getPlayer());

        for(Player staff : _VanishedPlayers) {
            event.getPlayer().showPlayer(Core.getCore(), staff);
        }

        //removeTag(event.getPlayer());
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

    public void vanish(Player admin) {
        _VanishedPlayers.add(admin);
        Get(admin).setVanished(true);
        for(Player pl : Bukkit.getOnlinePlayers()) {
            PermissionGroup myRank = Get(admin).getRank();
            PermissionGroup theirRank = Get(pl).getRank();
            if(theirRank.getPermissionLevel() < myRank.getPermissionLevel()) {
                pl.hidePlayer(Core.getCore(), admin);
            }
        }
    }

    public void unvanish(Player admin) {
        _VanishedPlayers.remove(admin);
        Get(admin).setVanished(false);
        for(Player pl : Bukkit.getOnlinePlayers()) {
            pl.showPlayer(Core.getCore(), admin);
        }
    }

    public boolean isVanished(Player player) {
        return _VanishedPlayers.contains(player);
    }

    public void setNametags(Player player) { // Create scoreboard and the teams
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for(PermissionGroup group : PermissionGroup.values()) {
            Team team = player.getScoreboard().registerNewTeam(group.getPermissionLevel() + group.name());
            team.setPrefix(group.getPrefixForChat() + " ");
            //Bukkit.getLogger().info(F.main(getName(), "Registered Team: " + group.getName() + " - Prefix: " + group.getPrefixForChat() + " " + " - Player: " + player));
        }
    }

    public void newTag(Player player) { // assigns players to their team
        for(Player target : Bukkit.getOnlinePlayers()) {
            PermissionGroup group = Get(player).getRank();
            target.getScoreboard().getTeam(group.getPermissionLevel() + group.name()).addEntry(player.getName());
        }
    }
    public void removeTag(Player player) { // Remove player from all scoreboards
        for(Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }
}
