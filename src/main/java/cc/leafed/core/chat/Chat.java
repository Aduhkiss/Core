package cc.leafed.core.chat;

import cc.leafed.core.Core;
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

        // Pull message data from config
        String configMessage = Core.getCore().getConfig().getString("chat.messages");

        for(Player pl : Bukkit.getOnlinePlayers()) {

            configMessage = configMessage.replace("%username%", username);
            configMessage = configMessage.replace("%message%", message);
            configMessage = configMessage.replace("%message-filtered%", BasicFilter.filterMessage(message));
            configMessage = configMessage.replace("%prefix%", prefix);

            TextComponent mainMessage = new TextComponent(configMessage);
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
        target.sendMessage("§6-> " + op.getRank().getColor() + op.getRank().getName() + " §6" + caller.getName() + " §d" + message);
        caller.sendMessage("§6<- " + op.getRank().getColor() + op.getRank().getName() + " §6" + caller.getName() + " §d" + message);
    }
}
