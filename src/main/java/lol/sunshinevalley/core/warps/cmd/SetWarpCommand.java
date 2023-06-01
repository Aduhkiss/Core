package lol.sunshinevalley.core.warps.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.util.StringUtils;
import lol.sunshinevalley.core.warps.PlayerWarps;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class SetWarpCommand extends CloudCommand {

    PlayerWarps warps;

    public SetWarpCommand(PlayerWarps warps) {
        super(new String[]{"setwarp"}, PermissionGroup.PLAYER);
        this.warps = warps;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            player.sendMessage("§c/setwarp <Name>");
        } else {
            //String name = StringUtils.combine(args, 0);
            String name = args[0];

            // Check if the player has too many warps
            try {
                //TODO: Make a way to increase the number of player warps that donators, youtubers, and staff can have
                if(warps.getWarpsOwned(player) >= 5) {
                    player.sendMessage("§cYou are only allowed to have 5 player warps!");
                    return;
                }
                // Make sure we dont have any warps with that same name!!
                if(warps.warpExists(name)) {
                    player.sendMessage("§cA warp with that name already exists!");
                    return;
                }
                // Then if they dont, add the warp
                warps.setWarp(player, player.getLocation(), name);
                // and send a success message
                player.sendMessage("§7Successfully added warp §6" + name + "§7!");
            } catch (SQLException e) {
                player.sendMessage("§cThere was an issue while executing this command. Please try again later.");
            }

        }
    }
}
