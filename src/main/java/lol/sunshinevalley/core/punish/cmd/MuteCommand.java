package lol.sunshinevalley.core.punish.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import org.bukkit.entity.Player;

public class MuteCommand extends CloudCommand {

    public MuteCommand() {
        super(new String[]{"mute"}, PermissionGroup.MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}