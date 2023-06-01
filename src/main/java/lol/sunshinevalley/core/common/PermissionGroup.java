package lol.sunshinevalley.core.common;

import org.bukkit.ChatColor;

public enum PermissionGroup {
    // Default Player Rank
    PLAYER("No Rank", "", ChatColor.WHITE, 0),
    // Donator Ranks
    PLUS("Plus", "plus", ChatColor.AQUA, 1),
    PREMIUM("Premium", "premium", ChatColor.LIGHT_PURPLE, 2),

    // Content Creator Ranks
    YOUTUBE("YouTube", "youtube", ChatColor.RED, 17),
    TWITCH("Twitch", "twitch", ChatColor.DARK_PURPLE, 17),
    EVENT("Event Host", "event", ChatColor.WHITE, 17),

    // Staff Ranks

    BUILDER("Builder", "builder", ChatColor.BLUE, 20),
    HELPER("Helper", "helper", ChatColor.BLUE, 21),
    JNR_MODERATOR("Jr.Mod", "jr.mod", ChatColor.GOLD, 22),
    MODERATOR("Mod", "mod", ChatColor.GOLD, 23),
    SNR_MODERATOR("Sr.Mod", "sr.mod", ChatColor.GOLD, 24),
    //

    // Paid-Staff Ranks
    SUPPORT("Support", "support", ChatColor.BLUE, 25),
    ADMIN("Admin", "admin", ChatColor.DARK_RED, 26),
    DEVELOPER("Developer", "Dev", ChatColor.GOLD, 27),
    MANAGER("Manager", "manager", ChatColor.DARK_RED, 30),
    OWNER("Owner", "owner", ChatColor.DARK_RED, 100);

    String name;
    String prefix;
    ChatColor color;
    int permissionLevel = 0;

    PermissionGroup(String name, String prefix, ChatColor color, int permissionLevel) {
        this.name = name;
        this.prefix = prefix;
        this.color = color;
        this.permissionLevel = permissionLevel;
    }

    // maybe i dont need this??
//    PermissionGroup(String name, String prefix, ChatColor color) {
//        this.name = name;
//        this.prefix = prefix;
//        this.color = color;
//    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPrefixForChat() {
        return getColor() + "§l" + getPrefix().toUpperCase() + "";
    }

    public ChatColor getColor() {
        return color;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public boolean Has(PermissionGroup group) {
        if(getPermissionLevel() >= group.getPermissionLevel()) {
            return true;
        }
        return false;
    }
}
