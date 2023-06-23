package cc.leafed.core.admin.cmd;

import cc.leafed.core.common.CloudCommand;
import cc.leafed.core.common.PermissionGroup;
import cc.leafed.core.util.F;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameModeCommand extends CloudCommand {

    public GameModeCommand() { super(new String[]{"gm"}, "Change between CREATIVE and SURVIVAL", PermissionGroup.ADMIN); }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0 || args == null) {
            if(player.getGameMode() == GameMode.CREATIVE) {
                player.setGameMode(GameMode.SURVIVAL);
                //player.sendMessage();
                player.sendMessage(F.main("Gamemode", "§e" + player.getName() + "§7 Creative Mode: §cFalse"));
            } else {
                player.setGameMode(GameMode.CREATIVE);
                //player.sendMessage();
                player.sendMessage(F.main("Gamemode", "§e" + player.getName() + "§7 Creative Mode: §aTrue"));
            }
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                player.sendMessage("§cNo player with that name was found!");
            } else {
                if(target.getGameMode() == GameMode.CREATIVE) {
                    target.setGameMode(GameMode.SURVIVAL);
                    //target.sendMessage();
                    //player.sendMessage();
                    player.sendMessage(F.main("Gamemode", "§e" + target.getName() + "§7 Creative Mode: §cFalse"));
                    target.sendMessage(F.main("Gamemode", "§e" + target.getName() + "§7 Creative Mode: §cFalse"));
                } else {
                    target.setGameMode(GameMode.CREATIVE);
                    //target.sendMessage("§e" + target.getName() + "§7 Creative Mode: §aTrue");
                    //player.sendMessage("§e" + target.getName() + "§7 Creative Mode: §aTrue");

                    target.sendMessage(F.main("Gamemode", "§e" + target.getName() + "§7 Creative Mode: §aTrue"));
                    player.sendMessage(F.main("Gamemode", "§e" + target.getName() + "§7 Creative Mode: §aTrue"));
                }
            }
        }
    }
}