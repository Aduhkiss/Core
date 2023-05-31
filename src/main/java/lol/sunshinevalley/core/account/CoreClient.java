package lol.sunshinevalley.core.account;

import lol.sunshinevalley.core.common.PermissionGroup;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CoreClient {

    Player player;
    String username;
    UUID uuid;

    PermissionGroup permissionGroup;

    public CoreClient(Player player) {
        Bukkit.getLogger().info("Client Manager> Loading data for " + player.getName() + "...");
        this.player = player;
        this.username = player.getName();
        this.uuid = player.getUniqueId();
    }

    public Player getPlayer() {
        return player;
    }

    public String getUsername () {
        return username;
    }

    public UUID getUUID() {
        return uuid;
    }

    // Make sure to have getters and setters for all data that the CoreClientManager has to change that way we can change it!!
    public PermissionGroup getRank() {
        return permissionGroup;
    }
    public void setRank(PermissionGroup rank) {
        permissionGroup = rank;
    }
}
