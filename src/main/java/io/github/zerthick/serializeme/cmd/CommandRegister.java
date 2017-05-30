package io.github.zerthick.serializeme.cmd;

import io.github.zerthick.serializeme.SerializeMe;
import io.github.zerthick.serializeme.cmd.executors.DeserializeExecutor;
import io.github.zerthick.serializeme.cmd.executors.SerializeExecutor;
import io.github.zerthick.serializeme.cmd.executors.SerializeMeExecutor;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;

public class CommandRegister {

    public static void registerCommands(SerializeMe plugin) {

        CommandSpec deserialize = CommandSpec.builder()
                .permission("serializeme.command.deserialize")
                .executor(new DeserializeExecutor(plugin))
                .arguments(GenericArguments.remainingJoinedStrings(CommandArgs.SERIALIZED_ITEM))
                .build();

        CommandSpec serialize = CommandSpec.builder()
                .permission("serializeme.command.serialize")
                .executor(new SerializeExecutor(plugin))
                .arguments(GenericArguments.flags().flag("c").buildWith(GenericArguments.none()))
                .build();

        CommandSpec serializeMe = CommandSpec.builder()
                .permission("serializeme.command.info")
                .executor(new SerializeMeExecutor(plugin))
                .child(serialize, "serialize", "s")
                .child(deserialize, "deserialize", "d")
                .build();

        Sponge.getCommandManager().register(plugin, serializeMe, "serializeme", "sm");
    }
}
