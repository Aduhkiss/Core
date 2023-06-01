package lol.sunshinevalley.core.warps;

import lol.sunshinevalley.core.Core;
import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.command.CommandCenter;
import lol.sunshinevalley.core.database.Database;
import lol.sunshinevalley.core.warps.cmd.DelWarpCommand;
import lol.sunshinevalley.core.warps.cmd.SetWarpCommand;
import lol.sunshinevalley.core.warps.cmd.WarpCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerWarps extends MiniPlugin {

    CommandCenter commandCenter;
    Database database;

    public PlayerWarps(Database database, CommandCenter commandCenter) {
        super("Player Warps");
        this.commandCenter = commandCenter;
        this.database = database;

        commandCenter.addCommand(new SetWarpCommand(this));
        commandCenter.addCommand(new WarpCommand(this));
        commandCenter.addCommand(new DelWarpCommand(this));
    }

    public void setWarp(Player caller, Location location, String name) throws SQLException {
        database.update("INSERT INTO `playerwarps` (`owned_by_uuid`, `name`, `world`, `x`, `y`, `z`, `yaw`, `pitch`) VALUES ('" + caller.getUniqueId() + "', '" + name + "', '" + location.getWorld().getName() +
                "', '" + location.getX() + "', '" + location.getY() + "', '" + location.getZ() + "', '" + location.getYaw() + "', '" + location.getPitch() + "');");
    }

    public int getWarpsOwned(Player player) throws SQLException {
        ResultSet result = database.query("SELECT * FROM `playerwarps` WHERE `owned_by_uuid` = '" + player.getUniqueId() + "';");
        int i = 0;
        while(result.next()) {
            i++;
        }
        return i;
    }

    public boolean warpExists(String name) throws SQLException {
        ResultSet result = database.query("SELECT * FROM `playerwarps` WHERE `name` = '" + name + "';");
        boolean f = false;
        while(result.next()) {
            f = true;
        }
        return f;
    }

    public Location getLocationFromWarp(String name) throws SQLException {
        ResultSet result = database.query("SELECT * FROM `playerwarps` WHERE `name` = '" + name + "';");
        Location location = null;
        while(result.next()) {
            location = new Location(Bukkit.getWorld(result.getString("world")), result.getDouble("x"), result.getDouble("y"), result.getDouble("z"), (float) result.getDouble("yaw"), (float) result.getDouble("pitch"));
        }
        return location;
    }

    public OfflinePlayer getWarpOwner(String name) throws SQLException {
        ResultSet result = database.query("SELECT * FROM `playerwarps` WHERE `name` = '" + name + "';");
        OfflinePlayer owner = null;
        while(result.next()) {
            owner = Bukkit.getOfflinePlayer(UUID.fromString(result.getString("owned_by_uuid")));
        }
        return owner;
    }

    // hopefully we dont have multiple warps with the same name....
    public void removeWarp(String name) throws SQLException {
        database.update("DELETE FROM `playerwarps` WHERE `name` = '" + name + "'");
    }


}
