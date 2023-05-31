package lol.animevalley.core;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class MiniPlugin implements Listener {

    private String moduleName;

    public MiniPlugin(String moduleName) {
        this.moduleName = moduleName;
        Bukkit.getPluginManager().registerEvents(this, Core.getCore());
        Startup();
        Bukkit.getLogger().info(moduleName + "> " + "Loaded.");
    }

    public String getName() {
        return moduleName;
    }

    public abstract void Startup();

    public void say(String message) {
        Bukkit.getLogger().info("" + moduleName + "> " + message);
    }

}
