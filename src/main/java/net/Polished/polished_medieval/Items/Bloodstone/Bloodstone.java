package net.Polished.polished_medieval.Items.Bloodstone;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.Polished.polished_medieval.Polished_medieval.MOD_ID;
import static net.Polished.polished_medieval.Registry.itemRegistry.registerItem;

public class Bloodstone {
    public static Item bloodstone = registerItem("bloodstone");
    public static Item bucket_of_crystallized_blood = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "bucket_of_crystallized_blood"), new Item(new Item.Settings().maxCount(1)));
}
