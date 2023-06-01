package lol.sunshinevalley.core.punish.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.punish.Punish;
import org.bukkit.entity.Player;

public class WarnCommand extends CloudCommand {

    public WarnCommand(Punish punish) {
        super(new String[]{"warn"}, PermissionGroup.JNR_MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}
