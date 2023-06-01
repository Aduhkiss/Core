package lol.sunshinevalley.core.punish.cmd;

import lol.sunshinevalley.core.Core;
import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.punish.Punish;
import lol.sunshinevalley.core.punish.Punishment;
import lol.sunshinevalley.core.punish.PunishmentType;
import lol.sunshinevalley.core.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class KickCommand extends CloudCommand {

    Punish punish;

    public KickCommand(Punish punish) {
        super(new String[]{"kick"}, PermissionGroup.JNR_MODERATOR);
        this.punish = punish;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            player.sendMessage("§6/kick <Player> [Reason] §7Remove player from the server §6" + getGroup().getName());
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            String reason = null;
            if(args.length == 1) {
                // The operator provided a user to kick, but didnt write a reason, so lets just use the default reason
                reason = "No Reason Provided";
            } else {
                reason = StringUtils.combine(args, 1);
            }
            if(target == null) {
                player.sendMessage("§cNo matching player found");
                return;
            }

            try {
                punish.addPunishment(new Punishment(PunishmentType.KICK, player.getName(), target.getUniqueId(), reason, System.currentTimeMillis(), -1));
            } catch (SQLException e) {
                e.printStackTrace();
                player.sendMessage("§cThere was an issue contacting the database. Please contact a developer or admin.");
                return;
            }

            String appeal = Core.getCore().getConfig().getString("serverstatus.website");

            //TODO: Move this appeal link to a config file at some point
            target.kickPlayer("§c§lYou were kicked by " + player.getName() + "\n§f" + reason + "\n" + "§2Unfair Punishment? Appeal at " + appeal);
        }
    }
}