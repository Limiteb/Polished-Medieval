package net.Polished.polished_medieval.Items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class itemRegistry {
    public static Item registerItem(String namespace, String name) {
        return Registry.register(Registries.ITEM, Identifier.of(namespace, name), new Item(new Item.Settings()));
    }
    public static void registerItems() {
        Item testItem = registerItem("polished_medieval", "testitem");
    }
}
