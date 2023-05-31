package lol.animevalley.core.admin.cmd;

import lol.animevalley.core.common.CloudCommand;
import lol.animevalley.core.common.PermissionGroup;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameModeCommand extends CloudCommand {

    public GameModeCommand() { super(new String[]{"gm"}, PermissionGroup.ADMIN); }

    @Override
    public void Execute(Player player, String[] args) {
        if(args.length == 0 || args == null) {
            if(player.getGameMode() == GameMode.CREATIVE) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage("§e" + player.getName() + "§7 Creative Mode: §cFalse");
            } else {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage("§e" + player.getName() + "§7 Creative Mode: §aTrue");
            }
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                player.sendMessage("§cNo player with that name was found!");
            } else {
                if(target.getGameMode() == GameMode.CREATIVE) {
                    target.setGameMode(GameMode.SURVIVAL);
                    target.sendMessage("§e" + target.getName() + "§7 Creative Mode: §cFalse");
                    player.sendMessage("§e" + target.getName() + "§7 Creative Mode: §cFalse");
                } else {
                    target.setGameMode(GameMode.CREATIVE);
                    target.sendMessage("§e" + target.getName() + "§7 Creative Mode: §aTrue");
                    player.sendMessage("§e" + target.getName() + "§7 Creative Mode: §aTrue");
                }
            }
        }
    }
}