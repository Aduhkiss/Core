package cc.leafed.core.common;

import org.bukkit.entity.Player;

public abstract class CloudCommand {

    private String[] executors;
    private PermissionGroup group;
    String description;

//    public CloudCommand(String[] executors, PermissionGroup group) {
//        this.executors = executors;
//        this.group = group;
//        this.description = "No Description Provided";
//    }

    public CloudCommand(String[] executors, String description, PermissionGroup group) {
        this.executors = executors;
        this.group = group;
        this.description = description;
    }

    public PermissionGroup getGroup() {
        return group;
    }

    public abstract void Execute(Player player, String[] args);

    public String[] getExecutors() {
        return executors;
    }

    public void sendHelp(Player player) {
        player.sendMessage(getGroup().getColor() + "/" + getExecutors()[0] + " §7" + description + " " + getGroup().getColor() + getGroup().getName());
    }
}
