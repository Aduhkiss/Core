package lol.sunshinevalley.core.warps.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.warps.PlayerWarps;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class WarpCommand extends CloudCommand {

    PlayerWarps warps;

    public WarpCommand(PlayerWarps warps) {
        super(new String[]{"warp"}, PermissionGroup.PLAYER);
        this.warps = warps;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            player.sendMessage("§c/warp <Name>");
        } else {
            String name = args[0];
            // First lets check if this warp exists
            try {
                if(!warps.warpExists(name)) {
                    player.sendMessage("§cNo warp exists with this name!");
                } else {
                    // otherwise teleport the player
                    Location loc = warps.getLocationFromWarp(name);
                    player.teleport(loc);
                    player.sendMessage("§7You teleported to §6" + name);
                }
            } catch (SQLException e) {
                player.sendMessage("§cThere was an issue while executing this command. Please try again later.");
            }
        }
    }
}
