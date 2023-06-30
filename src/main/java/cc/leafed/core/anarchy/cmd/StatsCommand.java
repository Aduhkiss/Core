package cc.leafed.core.anarchy.cmd;

import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import org.bukkit.entity.Player;

public class StatsCommand extends CloudCommand {

    public StatsCommand() {
        super(new String[]{"stats"}, "View server statistics", PermissionGroup.PLAYER);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}
