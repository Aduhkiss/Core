package lol.sunshinevalley.core.customersupport.cmd;

import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.customersupport.CustomerSupport;
import lol.sunshinevalley.core.customersupport.repo.DBPackage;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class CheckCommand extends CloudCommand {

    CustomerSupport support;
    CoreClientManager clientManager;

    public CheckCommand(CustomerSupport support, CoreClientManager clientManager) {
        super(new String[]{"check", "checkuser", "checkplayer", "purchasehistory"}, "Display all packages a user has purchased", PermissionGroup.SUPPORT);
        this.clientManager = clientManager;
        this.support = support;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            sendHelp(player);
        } else {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            // First lets make sure the account has logged into the server ever before, because we dont want to check someone that hasnt
            // Lets just take some code from /updaterank to check
            try {
                if(!clientManager.Exists(target)) {
                    player.sendMessage("§cNo such player exists.");
                } else {
                    // They do exist.
                    // Pull all packages that they have purchased and display them to the operator
                    player.sendMessage("§7Showing all purchases for account §6" + target.getName());
                    for(DBPackage pack : support.getPurchasedPackages(target)) {
                        player.sendMessage("§7- " + pack.getPackageName());
                    }
                }
            } catch (SQLException e) {
                player.sendMessage("§cAn error has occured. Please contact an admin or developer.");
            }
        }
    }
}
