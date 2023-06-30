package cc.leafed.core.admin.cmd;

import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.util.C;
import cc.leafed.core.util.F;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand extends CloudCommand {

    public GiveCommand() {
        super(new String[]{"give"}, "Create ItemStacks in real-time", PermissionGroup.ADMIN);
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            sendHelp(player);
            return;
        }
        // /give Aduhkiss
        if(args.length == 1) {
            player.sendMessage(F.main("Give", "Missing ItemStack Arguments"));
            return;
        }
        ItemStack item = null;
        int amnt = 0;
        // /give Aduhkiss APPLE
        if(args.length == 2) {
            Material m = null;
            try {
                m = Material.valueOf(args[1].toUpperCase());
            } catch(IllegalArgumentException e) {
                player.sendMessage(F.main("Give", "Unknown Material " + C.mElem + args[1].toUpperCase() + C.mBody + "."));
                return;
            }
            // Create the itemstack
            amnt = 1;
            item = new ItemStack(m, amnt);
        }
        // /give Aduhkiss APPLE 3
        if(args.length == 3) {
            Material m = null;
            try {
                m = Material.valueOf(args[1].toUpperCase());
            } catch(IllegalArgumentException e) {
                player.sendMessage(F.main("Give", "Unknown Material " + C.mElem + args[1].toUpperCase() + C.mBody + "."));
                return;
            }
            // Create the itemstack
            amnt = Integer.valueOf(args[2]);
            item = new ItemStack(m, amnt);
        }

        // Add the newly created ItemStack to the players inventory
        player.getInventory().addItem(item);
        player.sendMessage(F.main("Give", "Gave you " + C.mElem + amnt + C.mBody + " of " + C.mElem + item.getType().toString() + C.mBody + "."));
        return;
    }
}
