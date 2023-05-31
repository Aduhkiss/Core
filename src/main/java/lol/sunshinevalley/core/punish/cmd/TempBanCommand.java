package lol.sunshinevalley.core.punish.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import org.bukkit.entity.Player;

public class TempBanCommand extends CloudCommand {

    public TempBanCommand() {
        super(new String[]{"tempban"}, PermissionGroup.JNR_MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}