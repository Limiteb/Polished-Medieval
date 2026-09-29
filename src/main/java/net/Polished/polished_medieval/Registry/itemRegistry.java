package net.Polished.polished_medieval.Registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.Polished.polished_medieval.Polished_medieval.MOD_ID;

public class itemRegistry {
    public static Item registerItem(String name) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new Item(new Item.Settings()));
    }
}
