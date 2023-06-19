package lol.sunshinevalley.core.chat.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.util.StringUtils;
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
