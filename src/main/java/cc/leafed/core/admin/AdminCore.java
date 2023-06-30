package cc.leafed.core.admin;

import cc.leafed.core.Core;
import cc.leafed.core.MiniPlugin;
import cc.leafed.core.account.CoreClientManager;
import cc.leafed.core.account.cmd.TeleportCommand;
import cc.leafed.core.admin.cmd.GameModeCommand;
import cc.leafed.core.admin.cmd.GiveCommand;
import cc.leafed.core.admin.cmd.UpdateRankCommand;
import cc.leafed.core.command.CommandCenter;
import cc.leafed.core.database.Database;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminCore extends MiniPlugin {
    CoreClientManager clientManager;
    Database database;


    public AdminCore(CommandCenter commandCenter, CoreClientManager clientManager, Database database) {
        super("Admin Commands");
        this.clientManager = clientManager;
        this.database = database;

        commandCenter.addCommand(new GameModeCommand());
        commandCenter.addCommand(new UpdateRankCommand(clientManager));
        commandCenter.addCommand(new TeleportCommand());
        commandCenter.addCommand(new GiveCommand());

        new BukkitRunnable() {

            @Override
            public void run() {
                String serverName = Core.getCore().getConfig().getString("serverstatus.name");
                updateMOTD(serverName);
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 0, (20 * 60) * 5);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onConnect(PlayerJoinEvent event) {
    }
    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {

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
