package lol.animevalley.core.chat;

import lol.animevalley.core.MiniPlugin;
import lol.animevalley.core.account.CoreClientManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat extends MiniPlugin {

    private CoreClientManager clientManager;

    public Chat(CoreClientManager clientManager) {
        super("Chat");
        this.clientManager = clientManager;
    }

    @Override
    public void Startup() {

    }

    @EventHandler
    public void onChatted(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        String username = clientManager.Get(event.getPlayer()).getUsername();
        String prefix = clientManager.Get(event.getPlayer()).getRank().getPrefixForChat();
        String message = event.getMessage();//TODO: Maybe some basic chat monitoring feature at some point?

        for(Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendMessage(prefix + " §e" + username + "§f: " + message);
        }

        return;
    }
}
