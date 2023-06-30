package cc.leafed.core;

import cc.leafed.core.account.cmd.VanishCommand;
import cc.leafed.core.command.CommandCenter;
import cc.leafed.core.database.Database;
import cc.leafed.core.account.CoreClientManager;
import cc.leafed.core.anarchy.AnarchyCore;
import cc.leafed.core.anarchy.ElytraFlyPatch;
import cc.leafed.core.chat.Chat;
import cc.leafed.core.admin.AdminCore;
import cc.leafed.core.customersupport.CustomerSupport;
import cc.leafed.core.disguise.Disguise;
import cc.leafed.core.portal.Portal;
import cc.leafed.core.security.Security;
import cc.leafed.core.tablist.Tablist;
import cc.leafed.core.util.F;
import cc.leafed.core.util.Finder;
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

        // ugh i fucking hate this code
        commandCenter.addCommand(new VanishCommand(clientManager));

        new AdminCore(commandCenter, clientManager, database);
        new Chat(clientManager, commandCenter);
        new CustomerSupport(commandCenter, clientManager, database);
        new Security();

        new AnarchyCore(clientManager, commandCenter);
        new Disguise(commandCenter, clientManager);
        new Tablist(clientManager);
        new ElytraFlyPatch();
        Finder finder = new Finder(clientManager);

        Portal portal = new Portal(commandCenter);

        long now = System.currentTimeMillis();
        Bukkit.getLogger().info(F.main("The Cloudy Co", "Total Infrastructure took " + TimeUnit.MILLISECONDS.toSeconds(now - enable) + " seconds " + "(" + (now - enable) + " millis) " + "to load."));
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
