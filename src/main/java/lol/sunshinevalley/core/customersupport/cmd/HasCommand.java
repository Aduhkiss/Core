package lol.sunshinevalley.core.customersupport.cmd;

import lol.sunshinevalley.core.common.CloudCommand;
import lol.sunshinevalley.core.common.PermissionGroup;
import lol.sunshinevalley.core.customersupport.CustomerSupport;
import org.bukkit.entity.Player;

public class HasCommand extends CloudCommand {

    CustomerSupport support;
    public HasCommand(CustomerSupport support) {
        super(new String[]{"has", "haspackage"}, PermissionGroup.SUPPORT);
        this.support = support;
    }

    @Override
    public void Execute(Player player, String[] args) {

    }
}
