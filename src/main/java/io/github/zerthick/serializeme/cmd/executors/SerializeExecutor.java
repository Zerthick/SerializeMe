package io.github.zerthick.serializeme.cmd.executors;

import io.github.zerthick.serializeme.SerializeMe;
import io.github.zerthick.serializeme.util.ItemStackHOCONSerializer;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.command.CommandException;
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

public class SerializeExecutor extends AbstractCmdExecutor {

    public SerializeExecutor(SerializeMe plugin) {
        super(plugin);
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

        if (src instanceof Player) {
            Player player = (Player) src;
            Optional<ItemStack> itemStackOptional = player.getItemInHand(HandTypes.MAIN_HAND);
            if (itemStackOptional.isPresent()) {
                try {

                    String serializeItem = ItemStackHOCONSerializer.serializeSnapShot(itemStackOptional.get().createSnapshot(),
                            args.hasAny("c"));

                    player.sendMessage(Text.of(serializeItem));
                } catch (ObjectMappingException | IOException e) {
                    player.sendMessage(Text.of(TextColors.RED, "Error serializing itemstack! Error: ", e.getMessage()));
                }
            } else {
                src.sendMessage(Text.of(TextColors.RED, "You must hold an item in your main hand to be serialized!"));
            }
        } else {
            src.sendMessage(Text.of("You must be a player to serialize an item!"));
        }

        return CommandResult.success();
    }
}
