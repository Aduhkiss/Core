package cc.leafed.core.customersupport.repo;


import cc.leafed.core.customersupport.Packages;
import cc.leafed.core.customersupport.PackageType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class DBPackage {
    /*
    bit of a confusing one, but this class exists only to be used when pulling data from the packages table on the mysql, to populate into one of these to work with.
    @Author Atticus
     */
    private PackageType packageType;
    private Packages packageName;
    OfflinePlayer target;

    public DBPackage(PackageType packageType, Packages packageName, OfflinePlayer target) {
        this.packageType = packageType;
        this.packageName = packageName;
        this.target = target;
    }
    public DBPackage(PackageType packageType, Packages packageName, String uuid) {
        this.packageType = packageType;
        this.packageName = packageName;
        this.target = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
    }

    public PackageType getPackageType() {
        return packageType;
    }

    public Packages getPackageName() {
        return packageName;
    }

    public OfflinePlayer getTarget() {
        return target;
    }
}
