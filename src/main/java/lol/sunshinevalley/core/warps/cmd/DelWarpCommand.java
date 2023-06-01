package lol.sunshinevalley.core.warps.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.warps.PlayerWarps;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class DelWarpCommand extends CloudCommand {
    PlayerWarps warps;

    public DelWarpCommand(PlayerWarps warps) {
        super(new String[]{"delwarp"}, PermissionGroup.PLAYER);
        this.warps = warps;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            player.sendMessage("§c/delwarp <Name>");
        } else {
            //String name = StringUtils.combine(args, 0);
            String name = args[0];

            // Check if the player has too many warps
            try {
                // Make sure we dont have any warps with that same name!!
                if (!warps.warpExists(name)) {
                    player.sendMessage("§cA warp with that name does not exist!");
                    return;
                }
                // Make sure this person owns that warp
                if(!player.getName().equals(warps.getWarpOwner(name).getName())) {
                    player.sendMessage("§cYou do not own this warp!");
                    return;
                }
                // Then if they dont, add the warp
                warps.removeWarp(name);
                // and send a success message
                player.sendMessage("§7Successfully removed warp §6" + name + "§7!");
            } catch (SQLException e) {
                player.sendMessage("§cThere was an issue while executing this command. Please try again later.");
            }
        }
    }
}