package net.Polished.polished_medieval.Items.Ichor;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.Polished.polished_medieval.Polished_medieval.MOD_ID;
import static net.Polished.polished_medieval.Registry.itemRegistry.registerItem;

public class Ichor {
    public static Item vial_of_ichor = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "vial_of_ichor"), new Item(new Item.Settings().maxCount(16)));
    public static Item drop_of_ichor = registerItem("drop_of_ichor");
    public static Item bucket_of_ichor = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "bucket_of_ichor"), new Item(new Item.Settings().maxCount(1)));
}
