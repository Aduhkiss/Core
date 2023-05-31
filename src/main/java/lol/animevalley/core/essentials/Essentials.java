package lol.animevalley.core.essentials;

import lol.animevalley.core.MiniPlugin;
import lol.animevalley.core.command.CommandCenter;
import lol.animevalley.core.essentials.cmd.TeleportCommand;

public class Essentials extends MiniPlugin {

    CommandCenter commandCenter;

    public Essentials(CommandCenter commandCenter) {
        super("Essentials");
        this.commandCenter = commandCenter;

        commandCenter.addCommand(new TeleportCommand());
    }

    @Override
    public void Startup() {

    }
}
