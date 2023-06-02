package lol.sunshinevalley.core.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import lol.sunshinevalley.core.Core;
import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.account.CoreClient;
import lol.sunshinevalley.core.account.CoreClientManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Scoreboard extends MiniPlugin {

    CoreClientManager clientManager;

    public Scoreboard(CoreClientManager clientManager) {
        super("Scoreboard");
        this.clientManager = clientManager;
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Map.Entry<UUID, FastBoard> b : _boards.entrySet()) {
                    Player player = Bukkit.getPlayer(b.getKey());
                    CoreClient client = clientManager.Get(player);
                    updateBoard(b.getValue(),
                            "§b§lWorld",
                            "§f" + player.getWorld().getName(),
                            "",
                            "§a§lMoney",
                            "§f" + "$0.00", // hard coded value because yes
                            "",
                            "§e§lRank",
                            "§f" + client.getRank().getName(),
                            "",
                            "§6§lWebsite",
                            "§f" + Core.getCore().getConfig().getString("serverstatus.website"),
                            "",
                            "-----------------");
                }
            }
        }.runTaskTimerAsynchronously(Core.getCore(), 0, 10);
    }

    private final Map<UUID, FastBoard> _boards = new HashMap<>();

    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        FastBoard b = new FastBoard(event.getPlayer());
        b.updateTitle("§6§lSUNSHINE VALLEY");
        _boards.put(event.getPlayer().getUniqueId(), b);
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event){
        FastBoard b = _boards.remove(event.getPlayer().getUniqueId());
        if(b != null) {
            b.delete();
        }
    }

    private void updateBoard(FastBoard board, String ... lines) {
        board.updateLines(lines);
    }
}
