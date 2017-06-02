/*
 * Copyright (C) 2017  Zerthick
 *
 * This file is part of SerializeMe.
 *
 * SerializeMe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * SerializeMe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SerializeMe.  If not, see <http://www.gnu.org/licenses/>.
 */

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
