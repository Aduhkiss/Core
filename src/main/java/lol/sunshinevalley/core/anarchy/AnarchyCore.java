package lol.sunshinevalley.core.anarchy;

import lol.sunshinevalley.core.Core;
import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.anarchy.cmd.SpyCommand;
import lol.sunshinevalley.core.anarchy.cmd.TellCommand;
import lol.sunshinevalley.core.anarchy.util.IllegalChecker;
import lol.sunshinevalley.core.command.CommandCenter;
import lol.sunshinevalley.core.util.F;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
        Inventory inv = event.getInventory();
        // Scan the whole inventory for illegals
        for(ItemStack item : inv.getContents()) {
            if(item != null) {
                boolean flag = IllegalChecker.disallowedItem(item);
                if(flag) {
                    //TODO: Log any illegals in the database so we can pull and recover them in the future
                    Bukkit.getLogger().info(F.main(getName(), "Removed illegal item: " + item.getType().toString()));
                    inv.remove(item);
                }
            }
        }
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
