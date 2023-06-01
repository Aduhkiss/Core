package lol.sunshinevalley.core.punish.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.punish.Punish;
import org.bukkit.entity.Player;

public class IPBanCommand extends CloudCommand {

    public IPBanCommand(Punish punish) {
        super(new String[]{"ipban"}, PermissionGroup.SNR_MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}