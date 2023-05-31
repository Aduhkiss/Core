package lol.animevalley.core.admin.cmd;

import lol.animevalley.core.admin.AdminCore;
import lol.animevalley.core.common.CloudCommand;
import lol.animevalley.core.common.PermissionGroup;
import org.bukkit.entity.Player;

public class VanishCommand extends CloudCommand {

    AdminCore adminCore;

    public VanishCommand(AdminCore adminCore) {
        super(new String[]{"vanish", "v", "incognito"}, PermissionGroup.MODERATOR);
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
