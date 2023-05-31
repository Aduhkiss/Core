package lol.sunshinevalley.core;

import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.chat.Chat;
import lol.sunshinevalley.core.command.CommandCenter;
import lol.sunshinevalley.core.admin.AdminCore;
import lol.sunshinevalley.core.database.Database;
import lol.sunshinevalley.core.essentials.Essentials;
import lol.sunshinevalley.core.punish.Punish;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core core;

    @Override
    public void onEnable() {
        // Plugin startup logic
        core = this;
        saveDefaultConfig();

        Database database = new Database();
        CoreClientManager clientManager = new CoreClientManager(database);
        CommandCenter commandCenter = new CommandCenter(clientManager);
        new AdminCore(commandCenter, clientManager);
        new Chat(clientManager);
        new Essentials(commandCenter);
        new Punish(commandCenter, database);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        core = null;
    }

    public static Core getCore() {
        return core;
    }
}
