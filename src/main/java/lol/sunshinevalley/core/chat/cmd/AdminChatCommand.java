package lol.sunshinevalley.core.chat.cmd;

import lol.sunshinevalley.core.chat.Chat;
import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.util.StringUtils;
import org.bukkit.entity.Player;

public class AdminChatCommand extends CloudCommand {

    Chat chat;

    public AdminChatCommand(Chat chat) {
        super(new String[]{"admin", "helpop", "ao"}, "Post a message in admin only chat", PermissionGroup.PLAYER);
        this.chat = chat;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            player.sendMessage("§cErr.. something wrong?");
        } else {
            String message = StringUtils.combine(args, 0);
            chat.helpop(player, message);
        }
    }
}
