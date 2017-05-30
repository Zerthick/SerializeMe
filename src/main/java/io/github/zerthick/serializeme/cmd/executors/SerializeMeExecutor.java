package io.github.zerthick.serializeme.cmd.executors;

import io.github.zerthick.serializeme.SerializeMe;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

/**
 * Created by chase on 5/30/17.
 */
public class SerializeMeExecutor extends AbstractCmdExecutor {

    public SerializeMeExecutor(SerializeMe plugin) {
        super(plugin);
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

        src.sendMessage(Text.of(TextColors.DARK_GREEN, container.getName(),
                TextColors.GREEN, " version: ", TextColors.DARK_GREEN,
                container.getVersion().orElse(""), TextColors.GREEN, " by ",
                TextColors.DARK_GREEN, "Zerthick"));

        return CommandResult.success();
    }
}
