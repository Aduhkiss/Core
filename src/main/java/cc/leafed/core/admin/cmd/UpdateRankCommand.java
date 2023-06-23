package cc.leafed.core.admin.cmd;

import cc.leafed.core.Core;
import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.util.F;
import cc.leafed.core.account.CoreClientManager;
import cc.leafed.core.common.PermissionGroup;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

public class UpdateRankCommand extends CloudCommand {

    private CoreClientManager clientManager;

    public UpdateRankCommand(CoreClientManager clientManager) {
        super(new String[]{"updaterank", "rank"}, "Update a players rank on the network", PermissionGroup.ADMIN);
        this.clientManager = clientManager;
    }

    PermissionGroup rank = null;

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length != 2) {
            sendHelp(player);
        } else {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            //PermissionGroup rank = PermissionGroup.valueOf(args[1]);
            try {
                rank = PermissionGroup.valueOf(args[1]);
            } catch(IllegalArgumentException ex) {
                player.sendMessage(F.main(clientManager.getName(), "§c§lInvalid Rank!"));
                return;
            }
            try {
                if(!clientManager.Exists(target)) {
                    player.sendMessage(F.main(clientManager.getName(), "§c§lInvalid Player!"));
                    return;
                } else {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            try {
                                clientManager.updateRank(rank, target);
                                player.sendMessage(F.main(clientManager.getName(), "§7You have updated " + target.getName() + "'s rank to " + rank.getName() + "§7!"));
                            } catch (SQLException e) {
                                player.sendMessage("§cAn internal network error occured while trying to update ranks! Please try again later.");
                            }
                        }
                    }.runTaskAsynchronously(Core.getCore());
                }
            } catch (SQLException e) {
                player.sendMessage("§cThere was an error while running this command! Please try again later.");
                return;
            }

        }
    }
}
