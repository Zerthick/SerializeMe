package io.github.zerthick.serializeme.cmd.executors;

import io.github.zerthick.serializeme.SerializeMe;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.plugin.PluginContainer;

public abstract class AbstractCmdExecutor implements CommandExecutor {

    protected PluginContainer container;
    protected SerializeMe plugin;

    public AbstractCmdExecutor(SerializeMe plugin) {
        super();
        this.plugin = plugin;
        container = plugin.getInstance();
    }

}
