package lol.sunshinevalley.core.punish;

import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.account.CoreClient;
import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.command.CommandCenter;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.database.Database;
import lol.sunshinevalley.core.punish.cmd.*;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Punish extends MiniPlugin {

    CommandCenter commandCenter;
    Database database;
    CoreClientManager clientManager;

    public Punish(CommandCenter commandCenter, Database database, CoreClientManager clientManager) {
        super("Punish");
        this.commandCenter = commandCenter;
        this.database = database;
        this.clientManager = clientManager;

        commandCenter.addCommand(new KickCommand(this));
        commandCenter.addCommand(new BanCommand(this));
        commandCenter.addCommand(new BlacklistCommand(this));
        commandCenter.addCommand(new IPBanCommand(this));
        commandCenter.addCommand(new MuteCommand(this));
        commandCenter.addCommand(new TempBanCommand(this));
        commandCenter.addCommand(new TempMuteCommand(this));
        commandCenter.addCommand(new WarnCommand(this));
}

    public void addPunishment(Punishment punishment) throws SQLException {
        database.update("INSERT INTO `accountpunishments` (`type`, `caller`, `target`, `reason`, `timestamp`, `expires`) VALUES ('" + punishment.getType().toString() +
        "', '" + punishment.getCaller() + "', '" + punishment.getTarget() + "', '" + punishment.getReason() + "', '" + punishment.getTimestamp() + "', '" + punishment.getExpires() + "');");

        OfflinePlayer tar = Bukkit.getOfflinePlayer(punishment.getTarget());
        for(Player pl : Bukkit.getOnlinePlayers()) {
            CoreClient cl = clientManager.Get(pl);
            if(cl.getRank().Has(PermissionGroup.JNR_MODERATOR)) {
                pl.sendMessage("§a" + punishment.getCaller() + "§f has " + punishment.getType().getAction() + " §a" + tar.getName() + "§f for the reason §a" + punishment.getReason());
            }
        }
    }
}
