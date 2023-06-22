package lol.sunshinevalley.core.command;

import lol.sunshinevalley.core.account.CoreClient;
import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.util.F;
import lol.sunshinevalley.core.util.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandCenter extends MiniPlugin {

    private CoreClientManager clientManager;
    private List<CloudCommand> _Commands = new ArrayList<>();
    private List<String> _BlockedCommands = new ArrayList<>();

    public CommandCenter(CoreClientManager clientManager) {
        super("Command Center");
        this.clientManager = clientManager;
        _BlockedCommands.add("me");
        _BlockedCommands.add("minecraft:help");
        _BlockedCommands.add("trigger");
        _BlockedCommands.add("version");
        _BlockedCommands.add("plugins");
        _BlockedCommands.add("pl");
    }

    public void addCommand(CloudCommand command) {
        _Commands.add(command);
        //Bukkit.getLogger().info(ChatColor.BLUE + "Command Center> " + ChatColor.GRAY + "Registered /" + command.getExecutors()[0]);
        say("Registered /" + command.getExecutors()[0]);
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        // Figure out if the command is one of ours
        //Bukkit.getLogger().info("Debug> " + event.getMessage());
        // If the command being called is one of the ones on the _BlockedCommands list
        CoreClient client = clientManager.Get(event.getPlayer());
        if(event.getMessage().startsWith("/help")) {
            event.setCancelled(true);
            for(CloudCommand c : _Commands) {
                if(client.getRank().Has(c.getGroup())) {
                    c.sendHelp(event.getPlayer());
                }
            }
            return;
        }
        for(String cmdd : _BlockedCommands) {
            if(event.getMessage().startsWith("/" + cmdd) || event.getMessage().contains(":")) {
                // Verify they have ADMIN
                PermissionGroup required = PermissionGroup.OWNER;
                if(!client.getRank().Has(required)) {
                    event.getPlayer().sendMessage(F.main(getName(), "§7This requires permission rank [§9" + required.toString() + "§7]!"));
                    event.setCancelled(true);
                    return;
                }
            }
        }
        for(CloudCommand cmd : _Commands) {
            for(String alias : cmd.getExecutors()) {
                if(event.getMessage().startsWith("/" + alias)) {
                    // Check if the user has access to this rank
                    if(client.getRank().Has(cmd.getGroup())) {
                        String[] moddedArray = Arrays.copyOfRange(StringUtils.toArray(event.getMessage()), 1, StringUtils.toArray(event.getMessage()).length);
                        cmd.Execute(event.getPlayer(), moddedArray);
                    } else {
                        event.getPlayer().sendMessage(F.main(getName(), "§7This requires permission rank [§9" + cmd.getGroup().toString() + "§7]!"));
                    }
                    event.setCancelled(true);
                }
            }
        }
    }
}
