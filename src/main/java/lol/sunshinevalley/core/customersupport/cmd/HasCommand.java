package lol.sunshinevalley.core.customersupport.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.customersupport.CustomerSupport;
import lol.sunshinevalley.core.customersupport.Packages;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class HasCommand extends CloudCommand {

    CustomerSupport support;
    public HasCommand(CustomerSupport support) {
        super(new String[]{"has", "haspackage"}, PermissionGroup.SUPPORT);
        this.support = support;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length != 2) {
            player.sendMessage("§9/has <OfflinePlayer> <Package> §7Check if an account owns a certain package §9" + getGroup().getName());
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
