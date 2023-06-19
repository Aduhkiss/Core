package lol.sunshinevalley.core.admin;

import lol.sunshinevalley.core.Core;
import lol.sunshinevalley.core.account.CoreClient;
import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.admin.cmd.UpdateRankCommand;
import lol.sunshinevalley.core.admin.cmd.VanishCommand;
import lol.sunshinevalley.core.command.CommandCenter;
import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.admin.cmd.GameModeCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.database.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminCore extends MiniPlugin {

    CommandCenter commandCenter;
    CoreClientManager clientManager;
    Database database;
    private List<Player> _VanishedPlayers = new ArrayList<>();

    public AdminCore(CommandCenter commandCenter, CoreClientManager clientManager, Database database) {
        super("Admin Commands");
        this.commandCenter = commandCenter;
        this.clientManager = clientManager;
        this.database = database;

        commandCenter.addCommand(new GameModeCommand());
        commandCenter.addCommand(new UpdateRankCommand(clientManager));
        commandCenter.addCommand(new VanishCommand(this));

        new BukkitRunnable() {

            @Override
            public void run() {
                String serverName = Core.getCore().getConfig().getString("serverstatus.name");
                updateMOTD(serverName);
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 0, (20 * 60) * 5);
    }

    public void vanish(Player admin) {
        _VanishedPlayers.add(admin);
        for(Player pl : Bukkit.getOnlinePlayers()) {
            PermissionGroup myRank = clientManager.Get(admin).getRank();
            PermissionGroup theirRank = clientManager.Get(pl).getRank();
            if(theirRank.getPermissionLevel() < myRank.getPermissionLevel()) {
                pl.hidePlayer(Core.getCore(), admin);
            }
        }
    }

    public void unvanish(Player admin) {
        _VanishedPlayers.remove(admin);
        for(Player pl : Bukkit.getOnlinePlayers()) {
            pl.showPlayer(Core.getCore(), admin);
        }
    }

    public boolean isVanished(Player player) {
        return _VanishedPlayers.contains(player);
    }

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        CoreClient client = clientManager.Get(event.getPlayer());
        try {
            /*
            I do not know what is going wrong. for some reason some bit of code is throwing null pointers everytime a client joins and I dont know how to fix it
            if someone else can fix it, I would love that!
            thanks
             */
            for(Player staff : _VanishedPlayers) {
                PermissionGroup myRank = clientManager.Get(event.getPlayer()).getRank();
                PermissionGroup staffRank = clientManager.Get(staff).getRank();
                if(myRank.getPermissionLevel() < staffRank.getPermissionLevel()) {
                    event.getPlayer().hidePlayer(Core.getCore(), staff);
                }
            }
        } catch(NullPointerException ex) {}
        // Is the server soft-capped
//        if(Bukkit.getOnlinePlayers().size() >= maxPlayers) {
//            // Check if the user is Staff or a Donator (or YouTube Rank)
//            if(!client.getRank().Has(PermissionGroup.PLUS)) {
//                // If they do not, remove them from the server
//                event.getPlayer().kickPlayer("§cKicked whilst connecting to " + Core.getCore().getConfig().getString("serverstatus.name") + ": The Server is full!\n\n" +
//                        "§fThe Server is currently full! You can purchase the PLUS status to bypass the player limit.\n\n§aPurchase at: " + Core.getCore().getConfig().getString("serverstatus.website"));
//                return;
//            } else {
//                // allow the connection lmao
//            }
//        }
        event.setJoinMessage("§8[§a+§8] §7" + event.getPlayer().getName());
    }
    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        for(Player staff : _VanishedPlayers) {
            event.getPlayer().showPlayer(Core.getCore(), staff);
        }
    }

    String line_one = null;
    String line_two = null;
    int maxPlayers = 0;

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMotd(line_one + "\n" + line_two);
        event.setMaxPlayers(maxPlayers);
    }

    public void updateMOTD(String server) {
        new BukkitRunnable() {

            @Override
            public void run() {
                try {
                    ResultSet pull = database.query("SELECT * FROM `serverlistdata` WHERE `server` = '" + server + "';");
                    while(pull.next()) {
                        line_one = pull.getString("lineone");
                        line_two = pull.getString("linetwo");
                        maxPlayers = pull.getInt("maxplayers");
                    }
                } catch (SQLException e) {
                    // err
                    line_one = "§cB068   ERR CONNECT TO DATABASE";
                    line_two = "         ADMIN PLEASE INVESTIGATE.";
                }
            }
        }.runTaskAsynchronously(Core.getCore());
    }
}
