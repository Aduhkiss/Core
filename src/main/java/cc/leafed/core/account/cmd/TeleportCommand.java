package cc.leafed.core.account.cmd;

import cc.leafed.core.account.CoreClient;
import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.util.C;
import cc.leafed.core.util.F;
import cc.leafed.core.util.Finder;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportCommand extends CloudCommand {

    public TeleportCommand() {
        super(new String[]{"teleport", "tele", "tp"}, "Teleport players", PermissionGroup.SNR_MODERATOR);
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0) {
            sendHelp(player);
            return;
        }
        // /tele Aduhkiss
        if(args.length == 1) {
            Player target = Finder.findPlayer(args[0]);
            CoreClient targetClient = Finder.findCoreClient(args[0]);
            if(target == null) { player.sendMessage(F.main("Online Player Search", C.mElem + "0 " + C.mBody + "matches for [" + C.mElem + args[0] + C.mBody + "].")); return; }
            player.teleport(target);
            player.sendMessage(F.main("Teleporter", "Teleported to " + C.mElem + targetClient.getUsername() + C.mBody + "."));
            return;
        }
        // /tele Aduhkiss OhGumball
        if(args.length == 2) {
            Player target = Finder.findPlayer(args[0]);
            CoreClient targetClient = Finder.findCoreClient(args[0]);
            Player aac = Finder.findPlayer(args[1]);
            CoreClient aacClient = Finder.findCoreClient(args[1]);
            if(target == null) { player.sendMessage(F.main("Online Player Search", C.mElem + "0 " + C.mBody + "matches for [" + C.mElem + args[0] + C.mBody + "].")); return; }
            if(aac == null) { player.sendMessage(F.main("Online Player Search", C.mElem + "0 " + C.mBody + "matches for [" + C.mElem + args[1] + C.mBody + "].")); return; }

            target.teleport(aac);
            player.sendMessage(F.main("Teleporter", "Teleported " + C.mElem + targetClient.getUsername() + C.mBody + " to " + C.mElem + aacClient.getUsername() + C.mBody + "."));
            return;
        }
        // /tele 10 12 30
        if(args.length == 3) {
            Location location = new Location(player.getWorld(), Double.valueOf(args[0]), Double.valueOf(args[1]), Double.valueOf(args[2]));
            if(location != null) {
                player.teleport(location);
                player.sendMessage(F.main("Teleporter", "Teleported to " + C.mElem + Double.valueOf(args[0]) + C.mBody + ", " + C.mElem + Double.valueOf(args[0]) + C.mBody + ", " + C.mElem + Double.valueOf(args[0]) + C.mBody + "."));
                return;
            }
        }
        player.sendMessage(F.main("Command Center", C.cRed + C.Bold + "Incorrect Command Usage."));
        return;
    }
}
