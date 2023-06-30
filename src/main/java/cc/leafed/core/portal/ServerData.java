package cc.leafed.core.portal;

public class ServerData {

    String serverName;
    boolean staffOnly;

    public ServerData(String serverName) {
        this.serverName = serverName;
    }
    public ServerData(String serverName, boolean isStaffOnly) {
        this.serverName = serverName;
        this.staffOnly = isStaffOnly;
    }

    public String getServerName() {
        return serverName;
    }
    public boolean isStaffOnly() {
        return staffOnly;
    }
}
