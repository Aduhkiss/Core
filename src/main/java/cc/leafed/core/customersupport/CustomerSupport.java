package cc.leafed.core.customersupport;

import cc.leafed.core.MiniPlugin;
import cc.leafed.core.command.CommandCenter;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.customersupport.cmd.CheckCommand;
import cc.leafed.core.customersupport.cmd.GrantPackageCommand;
import cc.leafed.core.customersupport.repo.DBPackage;
import cc.leafed.core.database.Database;
import cc.leafed.core.account.CoreClientManager;
import cc.leafed.core.customersupport.cmd.HasCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerSupport extends MiniPlugin {

    CoreClientManager clientManager;
    CommandCenter commandCenter;
    Database database;

    public CustomerSupport(CommandCenter commandCenter, CoreClientManager clientManager, Database database) {
        super("Customer Support");
        this.clientManager = clientManager;
        this.database = database;

        commandCenter.addCommand(new HasCommand(this));
        commandCenter.addCommand(new GrantPackageCommand(this));
        commandCenter.addCommand(new CheckCommand(this, clientManager));
    }

    //TOOD: Add support for a discord server / discord bot
    // Allow players to recieve their rewards on discord when they purchase things on the store

    public void addPackage(OfflinePlayer target, Package pack) throws SQLException {
        addPackage(target, pack, true, "CONSOLE");
    }
    public void addPackage(OfflinePlayer target, Package pack, boolean admin, String giver) throws SQLException {
        // First check if the player is online
        if(target.isOnline()) {
            // Send the player a thank you message
            if(admin) {
                ((Player) target).sendMessage("§e" + giver + "§a has added §e" + pack.getType().getName() + " §ato your account.");
                ((Player) target).sendMessage("Please re-log if your rank does not show up immediately.");
            } else {
                ((Player) target).sendMessage("§aThank you for purchasing §e" + pack.getType().getName() + "§a! Please re-log if your rank does not show up immediately.");
                ((Player) target).sendMessage("§aIf you need any help, please reach out to Customer Support on our Discord Server.");
            }
        }

        // Now check to see what it is that they bought
        if(pack.getType().getPackageType() == PackageType.RANK) {
            // If they purchased a rank, lets apply it to their account
            // Doing it through the CoreClientManager should send them a message if they are online
            PermissionGroup donorRank = pack.getRank();
            clientManager.updateRank(donorRank, target);
            say("Added package '" + pack.getType().getName() + "' to account " + target.getName() + "!");
        }
        if(pack.getType().getPackageType() == PackageType.COMMAND) {
            // Replace any placeholders
            String cmd = pack.getCommand();
            cmd = cmd.replace("{username}", target.getName());
            cmd = cmd.replace("{uuid}", target.getUniqueId().toString());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
            say("Added package '" + pack.getType().getName() + "' to account " + target.getName() + "!");
        }
        // Toss it into the database to track customer packages
        database.update("INSERT INTO `packages` (`package_type`, `package_name`, `target_uuid`) VALUES ('" + pack.getType().getPackageType().toString() + "', '" + pack.getType().toString() + "', '" + target.getUniqueId() + "');");
    }

    public List<DBPackage> getPurchasedPackages(OfflinePlayer player) throws SQLException {
        ResultSet result = database.query("SELECT * FROM `packages` WHERE `target_uuid` = '" + player.getUniqueId() + "';");
        List<DBPackage> packs = new ArrayList<>();
        while(result.next()) {
            packs.add(new DBPackage(PackageType.valueOf(result.getString("package_type")), Packages.valueOf(result.getString("package_name")), result.getString("target_uuid")));
        }
        return packs;
    }

    public boolean hasPackage(OfflinePlayer player, Packages packag) throws SQLException {
        boolean f = false;
        for(DBPackage pa : getPurchasedPackages(player)) {
            if(pa.getPackageName() == packag) {
                f = true;
            }
        }
        return f;
    }
}
