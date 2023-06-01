package lol.sunshinevalley.core.essentials.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeleportCommand extends CloudCommand {

    public TeleportCommand() {
        // Require JNR_MODERATOR for the base command, only show the operator what they have access to, and require higher ranks for other sub commands
        super(new String[]{"tele", "teleport", "tp"}, PermissionGroup.JNR_MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            player.sendMessage("§6/tp <Player> §7Teleport to a player §6" + getGroup().getName());
            player.sendMessage("§4/tp all §7Teleport all players to you §4" + PermissionGroup.OWNER.getName());
            return;
        } else {

            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                player.sendMessage("§cNo player matching that name was found!");
                return;
            }
            player.teleport(target);
            player.sendMessage("§7You teleported to §6" + target.getName());
            return;

            //TODO: do this later when I feel like it
//            if(args[0].equalsIgnoreCase("all")) {
//                // Verify that they have OWNER rank
//                // Teleport all players
//                if(p);
//            } else {
//                Player target = Bukkit.getPlayer(args[0]);
//                player.teleport(target);
//                player.sendMessage("§7You teleported to §6" + target.getName());
//                return;
//            }
        }
    }
}
