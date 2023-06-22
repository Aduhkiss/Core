package lol.sunshinevalley.core.disguise.cmd;

import lol.sunshinevalley.core.account.CoreClient;
import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.util.F;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import xyz.haoshoku.nick.api.NickAPI;

import java.sql.SQLException;

public class DisguiseCommand extends CloudCommand {
    CoreClientManager clientManager;
    public DisguiseCommand(CoreClientManager clientManager) {
        super(new String[]{"disguise", "nick"}, "Disguise as another Minecraft account", PermissionGroup.YOUTUBE);
        this.clientManager = clientManager;
    }

    @Override
    public void Execute(Player caller, String[] args) {
        if(args.length == 0) {
            if(clientManager.Get(caller).isDisguised()) {
                caller.sendMessage("§8Processing      Please Wait...");
                NickAPI.resetNick( caller );
                NickAPI.resetSkin( caller );
                NickAPI.resetUniqueId( caller );
                NickAPI.resetGameProfileName( caller );
                NickAPI.refreshPlayer( caller );
                clientManager.undisguise(caller);
                caller.sendMessage(F.main("Disguise", "§7You are no longer disguised!"));
            } else {
                sendHelp(caller);
            }
            return;
        } else {
            CoreClient client = clientManager.Get(caller);
            // Check if you typed RESET
            if(args[0].equalsIgnoreCase("RESET")) {
                caller.sendMessage("§8Processing      Please Wait...");
                NickAPI.resetNick( caller );
                NickAPI.resetSkin( caller );
                NickAPI.resetUniqueId( caller );
                NickAPI.resetGameProfileName( caller );
                NickAPI.refreshPlayer( caller );
                clientManager.undisguise(caller);
                caller.sendMessage(F.main("Disguise", "§7You are no longer disguised!"));
                return;
            } else {
                // Pull Offline player data
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
//                try {
//                    if(clientManager.Exists(target)) {
//                        if(clientManager.getOfflineRank(target).Has(PermissionGroup.YOUTUBE)) {
//                            caller.sendMessage("§cYou cannot disguise as staff, youtubers, or twitchers.");
//                            return;
//                        }
//                    }
//                } catch (SQLException e) {
//                    caller.sendMessage("§cThere was an error while contacting the controller. Please try again later.");
//                    return;
//                }
                caller.sendMessage("§8Processing      Please Wait...");
                NickAPI.nick(caller, target.getName());
                NickAPI.setSkin(caller, target.getName());
                NickAPI.setUniqueId( caller, target.getName() );
                NickAPI.setGameProfileName( caller, target.getName() );
                NickAPI.refreshPlayer( caller );
                clientManager.disguise(caller, target.getName());
                caller.sendMessage(F.main("Disguise", "§7You are now playing as §6" + target.getName() + "§7!"));
                return;
            }
        }
    }
}
