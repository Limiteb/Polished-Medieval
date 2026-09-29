package net.Polished.polished_medieval.Registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;

public class inventoryRegister {
    public static void creativeRegister(RegistryKey<ItemGroup> itemGroup, Item tool) {
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> content.add(tool));
    }
}
