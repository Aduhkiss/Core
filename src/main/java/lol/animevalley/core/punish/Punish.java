package lol.animevalley.core.punish;

import lol.animevalley.core.MiniPlugin;
import lol.animevalley.core.command.CommandCenter;
import lol.animevalley.core.database.Database;
import lol.animevalley.core.punish.cmd.*;

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
