package lol.sunshinevalley.core.punish;

import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class Punishment {

    private PunishmentType type;
    private String caller;
    private UUID target;
    private String reason;
    private long timestamp;
    private long expires;

    public Punishment(PunishmentType type, String caller, UUID target, String reason, long timestamp, long expires) {
        this.type = type;
        this.caller = caller;
        this.target = target;
        this.reason = reason;
        this.timestamp = timestamp;
        this.expires = expires;
    }

    public PunishmentType getType() {
        return type;
    }

    public String getCaller() {
        return caller;
    }

    public UUID getTarget() {
        return target;
    }

    public String getReason() {
        return reason;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getExpires() {
        return expires;
    }
}
