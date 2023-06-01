package lol.sunshinevalley.core.punish;

public enum PunishmentType {
    BAN("banned"),
    TEMPBAN("tempbanned"),
    MUTE("muted"),
    TEMPMUTE("tempmuted"),
    WARNING("warned"),
    IPBAN("IP-Banned"),
    BLACKLIST("blacklisted"),
    KICK("kicked");

    String action;

    PunishmentType(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
