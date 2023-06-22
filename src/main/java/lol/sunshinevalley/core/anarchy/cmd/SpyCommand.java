package lol.sunshinevalley.core.anarchy.cmd;

import lol.sunshinevalley.core.anarchy.AnarchyCore;
import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
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
