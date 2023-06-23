package cc.leafed.core.customersupport.cmd;

import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.customersupport.Packages;
import cc.leafed.core.customersupport.CustomerSupport;
import cc.leafed.core.customersupport.Package;
import cc.leafed.core.customersupport.PackageType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class GrantPackageCommand extends CloudCommand {
    CustomerSupport support;
    public GrantPackageCommand(CustomerSupport support) {
        super(new String[]{"grant", "grantpackage"}, "Add a package to an account", PermissionGroup.SUPPORT);
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


            player.sendMessage("§7Adding §6" + pack.getName() + "§7 to §6" + target.getName() + "'s§7 account...");
            try {
                // If the package you are trying to add to the account is a rank, verify the account doesnt already own that package.
                if(pack.getPackageType() == PackageType.RANK) {
                    if(support.hasPackage(target, pack)) {
                        player.sendMessage("§cThis account already owns that package! Contact Support Admin for additional help.");
                        return;
                    }
                }

                support.addPackage(target, new Package(pack, target), true, player.getName());
            } catch(SQLException ex) {
                player.sendMessage("§cAn Error occured. Please contact an admin or developer.");
                return;
            }
        }
    }
}
