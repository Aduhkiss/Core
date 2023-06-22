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
                    double tps = Bukkit.getTPS()[0];
                    String tpss = String.valueOf(tps).substring(0, 5);

                    String header = Core.getCore().getConfig().getString("tablist.server-title") + "\n\n" + Core.getCore().getConfig().getString("tablist.message");
                    String footer = "\n§eTPS: " + tpss + " -- " + count + " players online -- My Rank: " + rank.getName();

                    player.setPlayerListHeaderFooter("\n" + header + "\n", footer + "\n");
                }
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 20, 20);
    }
}
