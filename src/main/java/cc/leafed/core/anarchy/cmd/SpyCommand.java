package cc.leafed.core.anarchy.cmd;

import cc.leafed.core.anarchy.AnarchyCore;
import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import org.bukkit.entity.Player;

public class SpyCommand extends CloudCommand {

    private AnarchyCore anarchy;

    public SpyCommand(AnarchyCore anarchy) {
        super(new String[]{"spy", "socialspy"}, "Allows you to see players private messages", PermissionGroup.MODERATOR);
        this.anarchy = anarchy;
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}
