package lol.sunshinevalley.core.anarchy;

import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.anarchy.cmd.TellCommand;
import lol.sunshinevalley.core.command.CommandCenter;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class AnarchyCore extends MiniPlugin {

    CoreClientManager clientManager;
    CommandCenter commandCenter;

    public AnarchyCore(CoreClientManager clientManager, CommandCenter commandCenter) {
        super("Anarchy Core");
        this.clientManager = clientManager;
        commandCenter.addCommand(new TellCommand(clientManager));
    }

    @EventHandler
    public void checkForIllegals(InventoryOpenEvent event) {
    }


    @EventHandler
    public void antiNetherRoof(PlayerMoveEvent event) {
        Location location = event.getPlayer().getLocation();
        double y = location.getY();
        if(!event.getPlayer().getWorld().getName().equalsIgnoreCase("world_nether")) return;

        if(y >= 127) { // Above the nether roof?
            event.getPlayer().teleport(new Location(location.getWorld(), location.getX(), (y - 1), location.getZ()));
            //event.getPlayer().sendMessage("§cIllegal Movement Patterns. Sending you down...");
        }
    }
}
