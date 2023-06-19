package lol.sunshinevalley.core.security;

import lol.sunshinevalley.core.Core;
import lol.sunshinevalley.core.MiniPlugin;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Security extends MiniPlugin {
    List<UUID> _ServerOperators = new ArrayList<>();
    public Security() {
        super("Security System");
        _ServerOperators.add(UUID.fromString("1bb3381f-a22a-4675-88ce-df54ea6be6cf")); // Aduhkiss

        for(OfflinePlayer pl : Bukkit.getOperators()) {
            pl.setOp(false);
            say("Removed " + pl.getName() + " from server operator status.");
        }

        for(UUID uuid : _ServerOperators) {
            OfflinePlayer a = Bukkit.getOfflinePlayer(uuid);
            a.setOp(true);
            say("Added " + a.getName() + " as server operator status.");
        }
    }

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        if(event.getPlayer().isOp()) {
            if(!_ServerOperators.contains(event.getPlayer().getUniqueId())) {
                event.getPlayer().kickPlayer("§cYou are permanently banned from this server.\n\n§7Reason: §f" +
                        "Your account has a security risk.\n\n§7Find out more at: " + Core.getCore().getConfig().getString("server.website"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ban " + event.getPlayer().getName() + " Your account has a security risk. [GG-554698]");
            }
        }
    }
}
