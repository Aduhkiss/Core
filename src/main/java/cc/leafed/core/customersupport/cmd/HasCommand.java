package cc.leafed.core.customersupport.cmd;

import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.customersupport.CustomerSupport;
import cc.leafed.core.customersupport.Packages;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class HasCommand extends CloudCommand {

    CustomerSupport support;
    public HasCommand(CustomerSupport support) {
        super(new String[]{"has", "haspackage"}, "Check if an account owns a certain package", PermissionGroup.SUPPORT);
        this.support = support;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length != 2) {
            sendHelp(player);
        } else {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            Packages pack = null;
            try {
                pack = Packages.valueOf(args[1]);
            } catch(IllegalArgumentException ex) {
                player.sendMessage("§cInvalid package name. Check Support docs for more information.");
                return;
            }

            // Now check if this user has this package
            player.sendMessage("§7Checking §6" + target.getName() + "§7 for §6" + pack.getName() + "§7....");
            try {
                boolean has = support.hasPackage(target, pack);
                if(has) {
                    player.sendMessage("§6" + target.getName() + "§7 - §6" + pack.getName() + "§7: §aTrue");
                } else {
                    player.sendMessage("§6" + target.getName() + "§7 - §6" + pack.getName() + "§7: §cFalse");
                }
            } catch(SQLException ex) {
                player.sendMessage("§cAn Error occured. Please contact an admin or developer.");
                return;
            }
        }
    }
}
