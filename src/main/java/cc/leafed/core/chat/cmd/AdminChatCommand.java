package cc.leafed.core.chat.cmd;

import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.util.StringUtils;
import cc.leafed.core.chat.Chat;
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
