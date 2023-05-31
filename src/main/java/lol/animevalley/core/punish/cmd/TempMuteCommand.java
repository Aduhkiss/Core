package lol.animevalley.core.punish.cmd;

import lol.animevalley.core.common.CloudCommand;
import lol.animevalley.core.common.PermissionGroup;
import org.bukkit.entity.Player;

public class TempMuteCommand extends CloudCommand {

    public TempMuteCommand() {
        super(new String[]{"tempmute"}, PermissionGroup.JNR_MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}
