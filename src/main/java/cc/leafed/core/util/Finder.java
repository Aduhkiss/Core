package cc.leafed.core.util;

import cc.leafed.core.MiniPlugin;
import cc.leafed.core.account.CoreClient;
import cc.leafed.core.account.CoreClientManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Finder extends MiniPlugin {
    static CoreClientManager clientManager;

    public Finder(CoreClientManager clientManager) {
        super("Finder");
        this.clientManager = clientManager;
    }

    public static Player findPlayer(String name) {
        // Check if this is an actual player logged into the server
        Player tar = Bukkit.getPlayer(name);
        // Check if it is not null, then if that player is logged in, just return them
        if(tar != null) { return tar; }
        // If the player wasnt found, lets ask the CoreClientManager if anyone is disguised with that name
        Player disguisedPlayer = clientManager.getDisguisedPlayer(name);
        if(disguisedPlayer != null) { return disguisedPlayer; }
        return null;
    }

    public static CoreClient findCoreClient(String name) {
        return clientManager.Get(findPlayer(name));
    }
}
