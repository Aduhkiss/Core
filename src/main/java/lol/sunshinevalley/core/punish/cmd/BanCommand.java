package lol.sunshinevalley.core.punish.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.punish.Punish;
import org.bukkit.entity.Player;

public class BanCommand extends CloudCommand {

    public BanCommand(Punish punish) {
        super(new String[]{"ban"}, PermissionGroup.MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}
