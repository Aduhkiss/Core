package cc.leafed.core.chat.cmd;

import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AnnounceCommand extends CloudCommand {

    public AnnounceCommand() {
        super(new String[]{"announce", "sbc"}, "Broadcast a message to the whole server", PermissionGroup.ADMIN);
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            sendHelp(player);
            return;
        }
        String message = StringUtils.combine(args, 0);

        for(Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendTitle("§eAnnouncement", "§f" + message, 10, 70, 20);
            pl.sendMessage("§9Announcement> §b" + message);
        }
    }
}
