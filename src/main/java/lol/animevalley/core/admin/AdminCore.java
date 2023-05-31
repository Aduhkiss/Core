package lol.animevalley.core.admin;

import lol.animevalley.core.account.CoreClientManager;
import lol.animevalley.core.admin.cmd.UpdateRankCommand;
import lol.animevalley.core.command.CommandCenter;
import lol.animevalley.core.MiniPlugin;
import lol.animevalley.core.admin.cmd.GameModeCommand;

public class AdminCore extends MiniPlugin {

    private CommandCenter commandCenter;
    private CoreClientManager clientManager;

    public AdminCore(CommandCenter commandCenter, CoreClientManager clientManager) {
        super("Admin Commands");
        this.commandCenter = commandCenter;
        this.clientManager = clientManager;

        commandCenter.addCommand(new GameModeCommand());
        commandCenter.addCommand(new UpdateRankCommand(clientManager));
    }

    @Override
    public void Startup() {
    }
}
