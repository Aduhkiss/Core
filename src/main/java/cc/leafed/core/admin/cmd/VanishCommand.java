package cc.leafed.core.admin.cmd;

import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.admin.AdminCore;
import org.bukkit.entity.Player;

public class VanishCommand extends CloudCommand {

    AdminCore adminCore;

    public VanishCommand(AdminCore adminCore) {
        super(new String[]{"vanish", "v", "incognito"}, "Appear offline for normal users", PermissionGroup.MODERATOR);
        this.adminCore = adminCore;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            if(adminCore.isVanished(player)) {
                adminCore.unvanish(player);
                player.sendMessage("§7You are no longer incognito.");
            } else {
                adminCore.vanish(player);
                player.sendMessage("§7You are now incognito. You are now invisible to normal players.");
            }
        } else {

        }
    }
}
