package cc.leafed.core.account;

import cc.leafed.core.Core;
import cc.leafed.core.common.PermissionGroup;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CoreClient {

    Player player;

    UUID uuid;
    // If the CoreClient is currently disguised
    boolean disguised;
    // Will return either the real-name or the Disguised Username
    String username;
    Player lastMessagedMe;

    PermissionGroup permissionGroup;
    boolean isVanished;

    public CoreClient(Player player) {
        Bukkit.getLogger().info("Client Manager> Loading data for " + player.getName() + "...");
        this.player = player;
        this.username = player.getName();
        this.uuid = player.getUniqueId();
        //TODO: Load this from Database so you can stay disguised when you switch servers or rejoin
        this.disguised = false;
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

    public void setUsername(String name) {
        this.username = name;
    }

    public void setDisguised(boolean flag) {
        this.disguised = flag;
    }
    public boolean isDisguised() {
        return disguised;
    }
    public Player getWhoLastMessagedMe() {
        return lastMessagedMe;
    }
    public void setWhoLastMessagedMe(Player pl) {
        lastMessagedMe = pl;
    }

    // Make sure to have getters and setters for all data that the CoreClientManager has to change that way we can change it!!
    public PermissionGroup getRank() {
        return permissionGroup;
    }
    public void setRank(PermissionGroup rank) {
        permissionGroup = rank;
    }
    public boolean isVanished() {
        return isVanished;
    }
    public void setVanished(boolean f) {
        this.isVanished = f;
    }

    /*
    Send this CoreClient to a specified server on a BungeeCord network
     */
    public void send(String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(Core.getCore(), "BungeeCord", out.toByteArray());
    }
}
