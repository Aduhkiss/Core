package lol.animevalley.core.common;

import org.bukkit.entity.Player;

public abstract class CloudCommand {

    private String[] executors;
    private PermissionGroup group;

    public CloudCommand(String[] executors, PermissionGroup group) {
        this.executors = executors;
        this.group = group;
    }

    public PermissionGroup getGroup() {
        return group;
    }

    public abstract void Execute(Player player, String[] args);

    public String[] getExecutors() {
        return executors;
    }
}
