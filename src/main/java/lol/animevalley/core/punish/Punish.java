package lol.animevalley.core.punish;

import lol.animevalley.core.MiniPlugin;
import lol.animevalley.core.command.CommandCenter;
import lol.animevalley.core.database.Database;

public class Punish extends MiniPlugin {

    CommandCenter commandCenter;
    Database database;

    public Punish(CommandCenter commandCenter, Database database) {
        super("Punish");
        this.commandCenter = commandCenter;
        this.database = database;
    }

    @Override
    public void Startup() {

    }
}
