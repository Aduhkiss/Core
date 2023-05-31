package lol.animevalley.core.punish.cmd;

import lol.animevalley.core.common.CloudCommand;
import lol.animevalley.core.common.PermissionGroup;
import lol.animevalley.core.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KickCommand extends CloudCommand {

    public KickCommand() {
        super(new String[]{"kick"}, PermissionGroup.JNR_MODERATOR);
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

            //TODO: Move this appeal link to a config file at some point
            target.kickPlayer("§c§lYou were kicked by " + player.getName() + "\n§f" + reason + "\n" + "§2Unfair Punishment? Appeal at www.example.com");
        }
    }
}