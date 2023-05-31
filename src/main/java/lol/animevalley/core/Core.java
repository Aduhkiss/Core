package lol.animevalley.core;

import lol.animevalley.core.account.CoreClientManager;
import lol.animevalley.core.chat.Chat;
import lol.animevalley.core.command.CommandCenter;
import lol.animevalley.core.admin.AdminCore;
import lol.animevalley.core.database.Database;
import lol.animevalley.core.essentials.Essentials;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static Core core;

    @Override
    public void onEnable() {
        // Plugin startup logic
        core = this;

        Database database = new Database();
        CoreClientManager clientManager = new CoreClientManager(database);
        CommandCenter commandCenter = new CommandCenter(clientManager);
        new AdminCore(commandCenter, clientManager);
        new Chat(clientManager);
        new Essentials(commandCenter);
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
