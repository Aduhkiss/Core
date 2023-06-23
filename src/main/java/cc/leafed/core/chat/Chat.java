package cc.leafed.core.chat;

import cc.leafed.core.MiniPlugin;
import cc.leafed.core.account.CoreClient;
import cc.leafed.core.account.CoreClientManager;
import cc.leafed.core.chat.cmd.AdminChatCommand;
import cc.leafed.core.chat.cmd.AnnounceCommand;
import cc.leafed.core.chat.cmd.BroadcastCommand;
import cc.leafed.core.command.CommandCenter;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.chat.cmd.MessageAdminCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
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
        commandCenter.addCommand(new AnnounceCommand());
    }

    @EventHandler
    public void onChatted(AsyncPlayerChatEvent event) {
        //TODO: Also maybe make a preference for staff to disable chat while they're vanished?
        event.setCancelled(true);
        String username = clientManager.Get(event.getPlayer()).getUsername();
        String prefix = clientManager.Get(event.getPlayer()).getRank().getPrefixForChat();
        // Disable our chat filter lmao
        String message = event.getMessage();
        //String message = BasicFilter.filterMessage(event.getMessage());
        CoreClient ME = null;
        if(clientManager.Get(event.getPlayer()).isDisguised()) {
        } else {
            ME = clientManager.Get(event.getPlayer());
        }

        // Notify the user if someone mentions their name in chat
        // Break the message into a String array
//        String[] words = StringUtils.toArray(event.getMessage());
//        Player checker = null;
//        for(String word : words) {
//            // For each word, check if it is the IGN of an online player
//            checker = Bukkit.getPlayer(word);
//        }
//        // Check to see if checker is null
//        if(checker != null) {
//            // If its not, then this person mentioned them in chat
//            // Let them know (Make sure it's disguise compatible)
//            checker.sendMessage("§b" + username + " mentioned you in chat!");
//            checker.playSound(checker.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 10f, 3f);
//        }

        for(Player pl : Bukkit.getOnlinePlayers()) {

            TextComponent mainMessage = new TextComponent("<" + username + "> " + message);
            mainMessage.setColor(ChatColor.WHITE);
            mainMessage.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Click to direct message").create()));
            mainMessage.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + username + " "));
            pl.spigot().sendMessage(mainMessage);
        }

        return;
    }

    public boolean canSeeHelpOp(Player player) {
        CoreClient callerClient = clientManager.Get(player);
        return callerClient.getRank().Has(PermissionGroup.JNR_MODERATOR);
    }

    public void helpop(Player caller, String message) {
        CoreClient callerClient = clientManager.Get(caller);
        for(Player pl : Bukkit.getOnlinePlayers()) {
            CoreClient coreClient = clientManager.Get(pl);
            if(coreClient.getRank().Has(PermissionGroup.JNR_MODERATOR)) {
                pl.sendMessage(callerClient.getRank().getColor() + callerClient.getRank().getName() + " §6" + caller.getName() + " §d" + message);
                pl.playSound(pl.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 20f, 4f);
            }
            else if(pl.getName().equalsIgnoreCase(caller.getName())) {
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
