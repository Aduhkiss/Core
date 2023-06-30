package cc.leafed.core.portal;

import cc.leafed.core.Core;
import cc.leafed.core.MiniPlugin;
import cc.leafed.core.command.CommandCenter;
import cc.leafed.core.portal.cmd.FindCommand;
import cc.leafed.core.portal.cmd.SendCommand;
import cc.leafed.core.portal.cmd.ServerCommand;
import cc.leafed.core.util.F;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Portal extends MiniPlugin {

    private String serverName;
    private String serverGroup;

    public Portal(CommandCenter commandCenter) {
        super("Portal");
        Core.getCore().getServer().getMessenger().registerOutgoingPluginChannel(Core.getCore(), "BungeeCord");
        //Core.getCore().getServer().getMessenger().registerIncomingPluginChannel(Core.getCore(), "BungeeCord", this);
        serverName = Core.getCore().getConfig().getString("serverstatus.name");
        serverGroup = Core.getCore().getConfig().getString("serverstatus.group");

        commandCenter.addCommand(new ServerCommand(this));
        commandCenter.addCommand(new FindCommand(this));
        commandCenter.addCommand(new SendCommand(this));
    }

    public void sendToServer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(Core.getCore(), "BungeeCord", out.toByteArray());
        player.sendMessage(F.main(getName(), "You have been sent from §6" + serverName + "§7 to §6" + server + "§7"));
    }

    //TODO: Determine how many hub servers are configured, then load balance between them
    public void sendToHub() {

    }

//    public boolean doesServerExist(String serverName) {
//        ByteArrayDataOutput out = ByteStreams.newDataOutput();
//        Player randomPerson = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
//
//        out.writeUTF("GetServers");
//        randomPerson.sendPluginMessage(Core.getCore(), "BungeeCord", out.toByteArray());
//    }

    public String getServerName() {
        return serverName;
    }
    public String getServerGroup() {
        return serverGroup;
    }
}
