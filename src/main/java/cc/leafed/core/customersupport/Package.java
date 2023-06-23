package cc.leafed.core.customersupport;

import cc.leafed.core.common.PermissionGroup;
import org.bukkit.OfflinePlayer;

public class Package {

    Packages pack;
    OfflinePlayer target;

    public Package(Packages type, OfflinePlayer target) {
        this.pack = type;
        this.target = target;
    }

    public Packages getType() {
        return pack;
    }

    public OfflinePlayer getTarget() {
        return target;
    }
    public PermissionGroup getRank() {
        if(getType().getPackageType() == PackageType.RANK) {
            return PermissionGroup.valueOf(getType().getRankOrCommand());
        }
        return null;
    }
    public String getCommand() {
        if(getType().getPackageType() == PackageType.COMMAND) {
            return getType().getRankOrCommand();
        }
        return null;
    }
}
