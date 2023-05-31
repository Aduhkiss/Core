package lol.animevalley.core.punish.cmd;

import lol.animevalley.core.common.CloudCommand;
import lol.animevalley.core.common.PermissionGroup;
import org.bukkit.entity.Player;

public class MuteCommand extends CloudCommand {

    public MuteCommand() {
        super(new String[]{"mute"}, PermissionGroup.MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}