package cc.leafed.core.portal.cmd;

import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.portal.Portal;
import org.bukkit.entity.Player;

public class FindCommand extends CloudCommand {

    public FindCommand(Portal portal) {
        super(new String[]{"find", "locate"}, "Find what server a given player is currently connected to", PermissionGroup.MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}
