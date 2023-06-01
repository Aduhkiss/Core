package lol.sunshinevalley.core.customersupport.repo;


import lol.sunshinevalley.core.customersupport.PackageType;
import lol.sunshinevalley.core.customersupport.Packages;
import org.bukkit.OfflinePlayer;

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
