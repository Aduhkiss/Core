package lol.sunshinevalley.core.customersupport;

public enum Packages {
    /*
    Basically all of the packages that we sell are stored here
     */

    SLASH_FLY_COMMAND("/fly Command", "perms user {username} permission set essentials.fly", PackageType.COMMAND),
    PLUS_RANK_PACKAGE("Plus Rank Package", "PLUS", PackageType.RANK),
    PREMIUM_RANK_PACKAGE("Premium Rank Package", "PREMIUM", PackageType.RANK);

    String name;
    PackageType type;
    String rankOrCommand;

    Packages(String name, String rankOrCommand, PackageType type) {
        this.name = name;
        this.rankOrCommand = rankOrCommand;
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public String getRankOrCommand() {
        return rankOrCommand;
    }
    public PackageType getPackageType() {
        return type;
    }
}
