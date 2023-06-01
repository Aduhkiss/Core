package lol.sunshinevalley.core.customersupport.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.customersupport.CustomerSupport;
import org.bukkit.entity.Player;

public class GrantPackageCommand extends CloudCommand {
    CustomerSupport support;
    public GrantPackageCommand(CustomerSupport support) {
        super(new String[]{"grant", "grantpackage"}, PermissionGroup.SUPPORT);
        this.support = support;
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}
