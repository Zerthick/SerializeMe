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

package io.github.zerthick.serializeme.util;

import com.google.common.reflect.TypeToken;
import com.typesafe.config.ConfigRenderOptions;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;

import java.io.*;

public class ItemStackHOCONSerializer {

    public static String serializeSnapShot(ItemStackSnapshot snapshot, boolean concise) throws ObjectMappingException, IOException {
        ConfigurationNode node = HoconConfigurationLoader.builder().build().createEmptyNode();
        StringWriter stringWriter = new StringWriter();

        node.setValue(TypeToken.of(ItemStackSnapshot.class), snapshot);
        HoconConfigurationLoader.Builder builder = HoconConfigurationLoader.builder().setSink(() -> new BufferedWriter(stringWriter));

        if (concise) {
            builder.setRenderOptions(ConfigRenderOptions.concise());
        }

        builder.build().save(node);
        return stringWriter.toString();
    }

    public static ItemStackSnapshot deserializeSnapShot(String serializedSnapshot) throws ObjectMappingException, IOException {
        ConfigurationNode node = HoconConfigurationLoader.builder().setSource(() -> new BufferedReader(new StringReader(serializedSnapshot))).build().load();
        return node.getValue(TypeToken.of(ItemStackSnapshot.class));
    }

}