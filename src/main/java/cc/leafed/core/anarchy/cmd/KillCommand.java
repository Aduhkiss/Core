package cc.leafed.core.anarchy.cmd;

import cc.leafed.core.account.CoreClientManager;
import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.util.F;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KillCommand extends CloudCommand {

    CoreClientManager clientManager;

    public KillCommand(CoreClientManager clientManager) {
        super(new String[]{"kill", "suicide"}, "Send yourself back to spawn", PermissionGroup.PLAYER);
        this.clientManager = clientManager;
    }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length != 0) {
            if(clientManager.Get(player).getRank().Has(PermissionGroup.ADMIN)) {
                Player target = Bukkit.getPlayer(args[0]);
                target.setHealth(0.00);
                player.sendMessage(F.main("Suicide", "Killed §e" + target.getName() + "§7."));
            }
        } else {
            player.setHealth(0.00);
            player.sendMessage(F.main("Suicide", "You killed yourself."));
        }
    }
}
