package lol.sunshinevalley.core.chat.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastCommand extends CloudCommand {

    public BroadcastCommand() {
        super(new String[]{"ss"}, PermissionGroup.SNR_MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            player.sendMessage("§6/ss <Message> §7Broadcast your message to the whole server §6" + getGroup().getName());
        } else {
            String message = StringUtils.combine(args, 0);
            for(Player pl : Bukkit.getOnlinePlayers()) {
                pl.sendMessage("§f§l" + player.getName() + " §r§b" + message);
            }
        }
    }
}
