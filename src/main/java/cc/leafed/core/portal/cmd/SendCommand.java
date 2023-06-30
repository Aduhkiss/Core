package cc.leafed.core.portal.cmd;

import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.portal.Portal;
import org.bukkit.entity.Player;

public class SendCommand extends CloudCommand {

    public SendCommand(Portal portal) {
        super(new String[]{"send"}, "Manually send a player on your server to another server", PermissionGroup.ADMIN);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}