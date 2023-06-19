package lol.sunshinevalley.core.tablist;

import lol.sunshinevalley.core.Core;
import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.common.PermissionGroup;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Tablist extends MiniPlugin {
    CoreClientManager clientManager;
    public Tablist(CoreClientManager clientManager) {
        super("Tablist");
        this.clientManager = clientManager;

        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    int count = Bukkit.getOnlinePlayers().size();
                    PermissionGroup rank = clientManager.Get(player).getRank();

                    String header = "§7§lLEAFED.CC\n\n§61.30 update soon.";
                    String footer = "\n§e" + count + " players online -- My Rank: " + rank.getName();

                    player.setPlayerListHeaderFooter("\n" + header + "\n", footer + "\n");
                }
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 100, 100);
    }
}
