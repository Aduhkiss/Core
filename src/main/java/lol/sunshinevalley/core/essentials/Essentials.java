package lol.sunshinevalley.core.essentials;

import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.command.CommandCenter;
import lol.sunshinevalley.core.essentials.cmd.TeleportCommand;

public class Essentials extends MiniPlugin {

    CommandCenter commandCenter;

    public Essentials(CommandCenter commandCenter) {
        super("Essentials");
        this.commandCenter = commandCenter;

        commandCenter.addCommand(new TeleportCommand());
    }
}
