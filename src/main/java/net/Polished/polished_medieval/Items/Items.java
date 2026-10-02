package net.Polished.polished_medieval.Items;

import net.minecraft.item.ItemGroups;

import static net.Polished.polished_medieval.Items.Bloodshard.Bloodshard.bloodshard;
import static net.Polished.polished_medieval.Items.Ichor.Ichor.ichor;
import static net.Polished.polished_medieval.Registry.inventoryRegister.creativeRegister;

public class Items {
    public static void initializeItems() {
        creativeRegister(ItemGroups.INGREDIENTS, bloodshard);
        creativeRegister(ItemGroups.INGREDIENTS, ichor);
    }
}
