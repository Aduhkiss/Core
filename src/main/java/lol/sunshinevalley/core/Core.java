package lol.sunshinevalley.core;

import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.chat.Chat;
import lol.sunshinevalley.core.command.CommandCenter;
import lol.sunshinevalley.core.admin.AdminCore;
import lol.sunshinevalley.core.database.Database;
import lol.sunshinevalley.core.essentials.Essentials;
import lol.sunshinevalley.core.punish.Punish;
import lol.sunshinevalley.core.warps.PlayerWarps;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class Core extends JavaPlugin {

    private static Core core;

    @Override
    public void onEnable() {
        long enable = System.currentTimeMillis();
        // Plugin startup logic
        core = this;
        saveDefaultConfig();

        Database database = new Database();
        CoreClientManager clientManager = new CoreClientManager(database);
        CommandCenter commandCenter = new CommandCenter(clientManager);
        new AdminCore(commandCenter, clientManager, database);
        new Chat(clientManager, commandCenter);
        // :( we cant have these finished in time...
        //new Essentials(commandCenter);
        //new Punish(commandCenter, database, clientManager);
        new PlayerWarps(database, commandCenter);

        long now = System.currentTimeMillis();
        Bukkit.getLogger().info("The Cloudy Co> " + "Total Infrastructure took " + TimeUnit.MILLISECONDS.toSeconds(now - enable) + " seconds " + "(" + (now - enable) + " millis) " + "to load.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        core = null;

        //TODO: Plugin messaging for bungeecord servers to send all players off this server when it shuts down, that way they dont have to reconnect
    }

    public static Core getCore() {
        return core;
    }
}
