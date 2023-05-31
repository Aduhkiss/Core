package lol.animevalley.core.punish.cmd;

import lol.animevalley.core.common.CloudCommand;
import lol.animevalley.core.common.PermissionGroup;
import org.bukkit.entity.Player;

public class WarnCommand extends CloudCommand {

    public WarnCommand() {
        super(new String[]{"warn"}, PermissionGroup.JNR_MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}
