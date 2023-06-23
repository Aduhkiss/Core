package cc.leafed.core.chat.cmd;

import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastCommand extends CloudCommand {

    public BroadcastCommand() {
        super(new String[]{"ss"}, "Broadcast your message to the whole server", PermissionGroup.SNR_MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            sendHelp(player);
        } else {
            String message = StringUtils.combine(args, 0);
            for(Player pl : Bukkit.getOnlinePlayers()) {
                pl.sendMessage("§f§l" + player.getName() + " §r§b" + message);
            }
        }
    }
}
