package lol.animevalley.core.admin;

import lol.animevalley.core.Core;
import lol.animevalley.core.account.CoreClientManager;
import lol.animevalley.core.admin.cmd.UpdateRankCommand;
import lol.animevalley.core.admin.cmd.VanishCommand;
import lol.animevalley.core.command.CommandCenter;
import lol.animevalley.core.MiniPlugin;
import lol.animevalley.core.admin.cmd.GameModeCommand;
import lol.animevalley.core.common.PermissionGroup;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;

public class AdminCore extends MiniPlugin {

    private CommandCenter commandCenter;
    private CoreClientManager clientManager;
    private List<Player> _VanishedPlayers = new ArrayList<>();

    public AdminCore(CommandCenter commandCenter, CoreClientManager clientManager) {
        super("Admin Commands");
        this.commandCenter = commandCenter;
        this.clientManager = clientManager;

        commandCenter.addCommand(new GameModeCommand());
        commandCenter.addCommand(new UpdateRankCommand(clientManager));
        commandCenter.addCommand(new VanishCommand(this));
    }

    @Override
    public void Startup() {
    }

    public void vanish(Player admin) {
        _VanishedPlayers.add(admin);
        for(Player pl : Bukkit.getOnlinePlayers()) {
            PermissionGroup myRank = clientManager.Get(admin).getRank();
            PermissionGroup theirRank = clientManager.Get(pl).getRank();
            if(theirRank.getPermissionLevel() < myRank.getPermissionLevel()) {
                pl.hidePlayer(Core.getCore(), admin);
            }
        }
    }

    public void unvanish(Player admin) {
        _VanishedPlayers.remove(admin);
        for(Player pl : Bukkit.getOnlinePlayers()) {
            pl.showPlayer(Core.getCore(), admin);
        }
    }

    public boolean isVanished(Player player) {
        return _VanishedPlayers.contains(player);
    }

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        try {
            /*
            I do not know what is going wrong. for some reason some bit of code is throwing null pointers everytime a client joins and I dont know how to fix it
            if someone else can fix it, I would love that!
            thanks
             */
            for(Player staff : _VanishedPlayers) {
                PermissionGroup myRank = clientManager.Get(event.getPlayer()).getRank();
                PermissionGroup staffRank = clientManager.Get(staff).getRank();
                if(myRank.getPermissionLevel() < staffRank.getPermissionLevel()) {
                    event.getPlayer().hidePlayer(Core.getCore(), staff);
                }
            }
        } catch(NullPointerException ex) {}
    }
    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        for(Player staff : _VanishedPlayers) {
            event.getPlayer().showPlayer(Core.getCore(), staff);
        }
    }
}
