package lol.sunshinevalley.core.disguise;

import lol.sunshinevalley.core.MiniPlugin;
import lol.sunshinevalley.core.account.CoreClientManager;
import lol.sunshinevalley.core.command.CommandCenter;
import lol.sunshinevalley.core.disguise.cmd.DisguiseCommand;

public class Disguise extends MiniPlugin {

    public Disguise(CommandCenter commandCenter, CoreClientManager clientManager) {
        super("Disguise Core");
        commandCenter.addCommand(new DisguiseCommand(clientManager));

    }
}
