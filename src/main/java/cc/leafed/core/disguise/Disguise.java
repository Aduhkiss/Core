package cc.leafed.core.disguise;

import cc.leafed.core.MiniPlugin;
import cc.leafed.core.account.CoreClientManager;
import cc.leafed.core.command.CommandCenter;
import cc.leafed.core.disguise.cmd.DisguiseCommand;

public class Disguise extends MiniPlugin {

    public Disguise(CommandCenter commandCenter, CoreClientManager clientManager) {
        super("Disguise Core");
        commandCenter.addCommand(new DisguiseCommand(clientManager));

    }
}
