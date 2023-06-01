package lol.sunshinevalley.core.chat.cmd;

import lol.sunshinevalley.core.chat.Chat;
import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.util.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageAdminCommand extends CloudCommand {

    Chat chat;

    public MessageAdminCommand(Chat chat) {
        super(new String[]{"messageadmin", "ma", "mao"}, PermissionGroup.JNR_MODERATOR);
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
