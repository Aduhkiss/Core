package cc.leafed.core.portal.cmd;

import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.portal.Portal;
import cc.leafed.core.util.F;
import org.bukkit.entity.Player;

public class ServerCommand extends CloudCommand {

    Portal portal;

    public ServerCommand(Portal portal) {
        super(new String[]{"server", "portal", "join"}, "Change what server you are playing on", PermissionGroup.PLAYER);
        this.portal = portal;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            player.sendMessage(F.main("Portal", "You are currently on server: §6" + portal.getServerName()));
        } else {
            String name = args[0];
            //TODO: Check if this server exists
            portal.sendToServer(player, name);
            return;
        }
    }
}
