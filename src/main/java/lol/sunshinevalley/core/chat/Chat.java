package lol.sunshinevalley.core.chat;

import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.account.CoreClient;
import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.chat.cmd.AdminChatCommand;
import lol.sunshinevalley.core.chat.cmd.BroadcastCommand;
import lol.sunshinevalley.core.chat.cmd.MessageAdminCommand;
import lol.sunshinevalley.core.command.CommandCenter;
import lol.sunshinevalley.core.common.PermissionGroup;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat extends MiniPlugin {

    private CoreClientManager clientManager;

    public Chat(CoreClientManager clientManager, CommandCenter commandCenter) {
        super("Chat");
        this.clientManager = clientManager;
        commandCenter.addCommand(new BroadcastCommand());
        commandCenter.addCommand(new AdminChatCommand(this));
        commandCenter.addCommand(new MessageAdminCommand(this));
    }

    @EventHandler
    public void onChatted(AsyncPlayerChatEvent event) {
        //TODO: Also maybe make a preference for staff to disable chat while they're vanished?
        event.setCancelled(true);
        String username = clientManager.Get(event.getPlayer()).getUsername();
        String prefix = clientManager.Get(event.getPlayer()).getRank().getPrefixForChat();
        String message = BasicFilter.filterMessage(event.getMessage());

        for(Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendMessage(prefix + " §e" + username + "§f: " + message);
        }

        return;
    }

    public void helpop(Player caller, String message) {
        for(Player pl : Bukkit.getOnlinePlayers()) {
            CoreClient callerClient = clientManager.Get(caller);
            CoreClient coreClient = clientManager.Get(pl);
            if(coreClient.getRank().Has(PermissionGroup.JNR_MODERATOR)) {
//                if(caller.getName().equals(pl.getName())) {
//                    // If you are the same person as the one who sent the message
//                }
                pl.sendMessage(callerClient.getRank().getColor() + callerClient.getRank().getName() + " §6" + caller.getName() + " §d" + message);
                pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 20f, 4f);
            }
        }
    }

    public void helpopMessage(Player caller, Player target, String message) {
        CoreClient op = clientManager.Get(caller);
        target.sendMessage("§6--> " + op.getRank().getColor() + op.getRank().getName() + " §6" + caller.getName() + " §d" + message);
        caller.sendMessage("§6<-- " + op.getRank().getColor() + op.getRank().getName() + " §6" + caller.getName() + " §d" + message);
    }
}
