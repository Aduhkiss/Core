package lol.sunshinevalley.core.anarchy;

import lol.sunshinevalley.core.Core;
import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.anarchy.cmd.SpyCommand;
import lol.sunshinevalley.core.anarchy.cmd.TellCommand;
import lol.sunshinevalley.core.command.CommandCenter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class AnarchyCore extends MiniPlugin {

    CoreClientManager clientManager;
    CommandCenter commandCenter;

    private List<Player> _SocialSpyEnabled = new ArrayList<>();

    public AnarchyCore(CoreClientManager clientManager, CommandCenter commandCenter) {
        super("Anarchy Core");
        this.clientManager = clientManager;

        if(Core.getCore().getConfig().getString("serverstatus.group").equalsIgnoreCase("lobby")) {
            say("Server has been programmed as a LOBBY or HUB. Setting up...");
            commandCenter.addCommand(new TellCommand(clientManager));
            commandCenter.addCommand(new SpyCommand(this));
        }
        if(Core.getCore().getConfig().getString("serverstatus.group").equalsIgnoreCase("arcade")) {
            say("Server has been programmed as an ARCADE.");
        }
    }

    @EventHandler
    public void checkForIllegals(InventoryOpenEvent event) {
    }


    @EventHandler
    public void antiNetherRoofMove(PlayerMoveEvent event) {
        Location location = event.getPlayer().getLocation();
        double y = location.getY();
        if(!event.getPlayer().getWorld().getName().equalsIgnoreCase("world_nether")) return;

        if(y >= 127) { // Above the nether roof?
            event.getPlayer().teleport(new Location(location.getWorld(), location.getX(), (y - 1), location.getZ()));
            //event.getPlayer().sendMessage("§cIllegal Movement Patterns. Sending you down...");
        }
    }

    @EventHandler
    public void antiNetherRoofBuild(BlockPlaceEvent event) {
        // Verify it is placed in the nether
        Block block = event.getBlockPlaced();
        if(!block.getWorld().getName().equalsIgnoreCase("world_nether")) return;
        if(block.getY() >= 127) {
            event.setCancelled(true);
        }
    }
}
