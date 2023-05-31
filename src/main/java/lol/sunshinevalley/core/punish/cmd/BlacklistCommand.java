package lol.sunshinevalley.core.punish.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import org.bukkit.entity.Player;

public class BlacklistCommand extends CloudCommand {

    public BlacklistCommand() {
        super(new String[]{"blacklist"}, PermissionGroup.ADMIN);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}