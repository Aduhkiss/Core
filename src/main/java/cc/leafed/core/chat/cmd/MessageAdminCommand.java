package cc.leafed.core.chat.cmd;

import cc.leafed.core.chat.Chat;
import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageAdminCommand extends CloudCommand {

    Chat chat;

    public MessageAdminCommand(Chat chat) {
        super(new String[]{"mao", "messageadmin"}, "Privately reply to an admin only message", PermissionGroup.JNR_MODERATOR);
        this.chat = chat;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length < 2) {
            player.sendMessage("§cErr.. something wrong?");
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            String message = StringUtils.combine(args, 1);
            chat.helpopMessage(player, target, message);
        }
    }
}
