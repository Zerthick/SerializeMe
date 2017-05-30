package io.github.zerthick.serializeme;

import com.google.inject.Inject;
import io.github.zerthick.serializeme.cmd.CommandRegister;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

@Plugin(
        id = "serializeme",
        name = "SerializeMe",
        version = "0.0.1",
        description = "Serialize an ItemStack to HOCON!",
        authors = {
                "Zerthick"
        }
)
public class SerializeMe {

    @Inject
    private Logger logger;
    @Inject
    private PluginContainer instance;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {

        // Register Commands
        CommandRegister.registerCommands(this);

        // Log Start Up to Console
        getLogger().info(
                instance.getName() + " version " + instance.getVersion().orElse("")
                        + " enabled!");
    }


    public Logger getLogger() {
        return logger;
    }

    public PluginContainer getInstance() {
        return instance;
    }
}
