/*
 * Copyright (C) 2018  Zerthick
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

package io.github.zerthick.serializeme.cmd.executors;

import io.github.zerthick.serializeme.SerializeMe;
import io.github.zerthick.serializeme.cmd.CommandArgs;
import io.github.zerthick.serializeme.util.ItemStackHOCONSerializer;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.io.IOException;
import java.util.Optional;

public class DeserializeExecutor extends AbstractCmdExecutor {

    public DeserializeExecutor(SerializeMe plugin) {
        super(plugin);
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {

        if (src instanceof Player) {
            Player player = (Player) src;

            Optional<String> serializedItemOptional = args.getOne(CommandArgs.SERIALIZED_ITEM);

            if (serializedItemOptional.isPresent()) {
                Optional<ItemStack> itemStackOptional = player.getItemInHand(HandTypes.MAIN_HAND);
                if (itemStackOptional.isPresent()) {
                    src.sendMessage(Text.of(TextColors.RED, "You must hold nothing in your main hand to deserialize an item!"));
                } else {
                    try {
                        player.setItemInHand(HandTypes.MAIN_HAND, ItemStackHOCONSerializer.deserializeSnapShot(serializedItemOptional.get()).createStack());
                    } catch (ObjectMappingException | IOException e) {
                        player.sendMessage(Text.of(TextColors.RED, "Error deserializing itemstack! Error: ", e.getMessage()));
                    }
                }
            }
        } else {
            src.sendMessage(Text.of("You must be a player to serialize an item!"));
        }

        return CommandResult.success();
    }
}
