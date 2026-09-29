package net.Polished.polished_medieval.Items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;

import static net.Polished.polished_medieval.Registry.inventoryRegister.creativeRegister;
import static net.Polished.polished_medieval.Registry.itemRegistry.registerItem;

public class Items {
    public static Item bloodshard = registerItem("bloodshard");
    public static Item ichor = registerItem("ichor");

    public static void initializeItems() {
        creativeRegister(ItemGroups.INGREDIENTS, bloodshard);
        creativeRegister(ItemGroups.INGREDIENTS, ichor);
    }
}
