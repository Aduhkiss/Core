package cc.leafed.core.account.cmd;

import cc.leafed.core.account.CoreClientManager;
import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.admin.AdminCore;
import org.bukkit.entity.Player;

public class VanishCommand extends CloudCommand {

    CoreClientManager clientManager;

    public VanishCommand(CoreClientManager clientManager) {
        super(new String[]{"vanish", "incognito"}, "Appear offline for normal users", PermissionGroup.MODERATOR);
        this.clientManager = clientManager;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            if(clientManager.isVanished(player)) {
                clientManager.unvanish(player);
                player.sendMessage("§7You are no longer incognito.");
            } else {
                clientManager.vanish(player);
                player.sendMessage("§7You are now incognito. You are now invisible to normal players.");
            }
        } else {

        }
    }
}
