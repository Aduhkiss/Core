package lol.sunshinevalley.core.punish;

import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.command.CommandCenter;
import lol.sunshinevalley.core.database.Database;
import lol.sunshinevalley.core.punish.cmd.*;

public class Punish extends MiniPlugin {

    CommandCenter commandCenter;
    Database database;

    public Punish(CommandCenter commandCenter, Database database) {
        super("Punish");
        this.commandCenter = commandCenter;
        this.database = database;

        commandCenter.addCommand(new KickCommand());
        commandCenter.addCommand(new BanCommand());
        commandCenter.addCommand(new BlacklistCommand());
        commandCenter.addCommand(new IPBanCommand());
        commandCenter.addCommand(new MuteCommand());
        commandCenter.addCommand(new TempBanCommand());
        commandCenter.addCommand(new TempMuteCommand());
        commandCenter.addCommand(new WarnCommand());
    }

    @Override
    public void Startup() {

    }
}
