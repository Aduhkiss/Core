package lol.sunshinevalley.core.punish.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.punish.Punish;
import org.bukkit.entity.Player;

public class BlacklistCommand extends CloudCommand {

    public BlacklistCommand(Punish punish) {
        super(new String[]{"blacklist"}, PermissionGroup.ADMIN);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}