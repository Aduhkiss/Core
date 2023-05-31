package lol.animevalley.core.punish.cmd;

import lol.animevalley.core.common.CloudCommand;
import lol.animevalley.core.common.PermissionGroup;
import org.bukkit.entity.Player;

public class BlacklistCommand extends CloudCommand {

    public BlacklistCommand() {
        super(new String[]{"blacklist"}, PermissionGroup.ADMIN);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}