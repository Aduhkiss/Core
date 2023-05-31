package lol.animevalley.core.punish.cmd;

import lol.animevalley.core.common.CloudCommand;
import lol.animevalley.core.common.PermissionGroup;
import org.bukkit.entity.Player;

public class IPBanCommand extends CloudCommand {

    public IPBanCommand() {
        super(new String[]{"ipban"}, PermissionGroup.SNR_MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}