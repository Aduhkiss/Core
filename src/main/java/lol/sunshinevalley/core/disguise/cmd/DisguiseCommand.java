package lol.sunshinevalley.core.disguise.cmd;

import lol.sunshinevalley.core.account.CoreClient;
import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import xyz.haoshoku.nick.api.NickAPI;

public class DisguiseCommand extends CloudCommand {
    CoreClientManager clientManager;
    public DisguiseCommand(CoreClientManager clientManager) {
        super(new String[]{"disguise", "nick"}, "Disguise as another Minecraft account", PermissionGroup.MODERATOR);
        this.clientManager = clientManager;
    }

    @Override
    public void Execute(Player caller, String[] args) {
        if(args.length == 0) {
            sendHelp(caller);
            return;
        } else {
            CoreClient client = clientManager.Get(caller);
            // Check if you typed *RESET
            if(args[0].equalsIgnoreCase("*RESET")) {
                caller.sendMessage("§8Processing. Please Wait.....");
                NickAPI.resetNick( caller );
                NickAPI.resetSkin( caller );
                NickAPI.resetUniqueId( caller );
                NickAPI.resetGameProfileName( caller );
                NickAPI.refreshPlayer( caller );
                clientManager.undisguise(caller);
                caller.sendMessage("§7You are now playing as §6" + caller.getName() + "§7!");
                return;
            } else {
                // Pull Offline player data
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                //TODO: Pull rank from maybe LuckPerms API, and dont let the caller disguise as Staff, YouTubers or Twitchers. And also maybe blacklist some Mojang Accounts too
                caller.sendMessage("§8Processing. Please Wait.....");
                NickAPI.nick(caller, target.getName());
                NickAPI.setSkin(caller, target.getName());
                NickAPI.setUniqueId( caller, target.getName() );
                NickAPI.setGameProfileName( caller, target.getName() );
                NickAPI.refreshPlayer( caller );
//                client.setDisguised(true);
//                client.setUsername(target.getName());
                clientManager.disguise(caller, target.getName());
                caller.sendMessage("§7You are now playing as §6" + target.getName() + "§7!");
                return;
            }
        }
    }
}
