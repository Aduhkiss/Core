package cc.leafed.core.anarchy.cmd;

import cc.leafed.core.account.CoreClient;
import cc.leafed.core.account.CoreClientManager;
import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TellCommand extends CloudCommand {
    CoreClientManager clientManager;

    public TellCommand(CoreClientManager clientManager) {
        super(new String[]{"tell", "whisper", "message", "msg"}, "Privately message another user on the server", PermissionGroup.PLAYER);
        this.clientManager = clientManager;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length < 1) {
            sendHelp(player);
        } else {
            CoreClient ME = clientManager.Get(player);
            String typedUsername = args[0];
            // THIS IS THE ACTUAL TARGET OF WHOS GETTING THE MESSAGE!!!
            String message = StringUtils.combine(args, 1);

            // First lets check to see if this user is actually logged into the server
            Player tar = Bukkit.getPlayer(typedUsername);
            if(tar != null) {
                tar.sendMessage("§dFrom " + ME.getUsername() + ": " + message);
                player.sendMessage("§dTo " + tar.getName() + ": " + message);
                return;
            } else {
                // Then after we've verified they don't, lets then check if anyone is disguised as this person
                // Ask the CoreClientManager to hand over the player
                Player disguisedPlayer = clientManager.getDisguisedPlayer(typedUsername);
                if(disguisedPlayer != null) {
                    // Send the message but keep them hidden
                    disguisedPlayer.sendMessage("§dFrom " + ME.getUsername() + ": " + message);
                    player.sendMessage("§dTo " + typedUsername + ": " + message);
                    return;
                } else {
                    // Well shit, this person literally doesnt exist lmao
                    player.sendMessage("§cThere is no player matching that username.");
                    return;
                }
            }

        }
    }
}
