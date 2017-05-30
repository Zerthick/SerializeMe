package io.github.zerthick.serializeme.util;

import com.google.common.reflect.TypeToken;
import com.typesafe.config.ConfigRenderOptions;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.io.*;

public class ItemStackHOCONSerializer {

    public static String serializeSnapShot(ItemStackSnapshot snapshot) throws ObjectMappingException, IOException {
        ConfigurationNode node = HoconConfigurationLoader.builder().build().createEmptyNode();
        StringWriter stringWriter = new StringWriter();

        node.setValue(TypeToken.of(ItemStackSnapshot.class), snapshot);
        HoconConfigurationLoader.builder().setSink(() -> new BufferedWriter(stringWriter)).build().save(node);

        return stringWriter.toString();
    }

    public static String serializeSnapShotConcise(ItemStackSnapshot snapshot) throws ObjectMappingException, IOException {
        ConfigurationNode node = HoconConfigurationLoader.builder().build().createEmptyNode();
        StringWriter stringWriter = new StringWriter();

        node.setValue(TypeToken.of(ItemStackSnapshot.class), snapshot);
        HoconConfigurationLoader.builder().setRenderOptions(ConfigRenderOptions.concise()).setSink(() -> new BufferedWriter(stringWriter)).build().save(node);

        return stringWriter.toString();
    }

    public static ItemStackSnapshot deserializeSnapShot(String serializedSnapshot) throws ObjectMappingException, IOException {
        ConfigurationNode node = HoconConfigurationLoader.builder().setSource(() -> new BufferedReader(new StringReader(serializedSnapshot))).build().load();
        return node.getValue(TypeToken.of(ItemStackSnapshot.class));
    }

}