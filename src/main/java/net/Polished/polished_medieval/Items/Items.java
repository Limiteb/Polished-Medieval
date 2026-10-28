package net.Polished.polished_medieval.Items;

import net.minecraft.item.ItemGroups;

import static net.Polished.polished_medieval.Items.Bloodstone.Bloodstone.bloodstone;
import static net.Polished.polished_medieval.Items.Bloodstone.Bloodstone.bucket_of_crystallized_blood;
import static net.Polished.polished_medieval.Items.Ichor.Ichor.drop_of_ichor;
import static net.Polished.polished_medieval.Items.Ichor.Ichor.vial_of_ichor;
import static net.Polished.polished_medieval.Items.Ichor.Ichor.bucket_of_ichor;
import static net.Polished.polished_medieval.Registry.inventoryRegister.creativeRegister;

public class Items {
    public static void initializeItems() {
        creativeRegister(ItemGroups.INGREDIENTS, bloodstone);
        creativeRegister(ItemGroups.TOOLS, bucket_of_crystallized_blood);
        creativeRegister(ItemGroups.INGREDIENTS, drop_of_ichor);
        creativeRegister(ItemGroups.INGREDIENTS, vial_of_ichor);
        creativeRegister(ItemGroups.FOOD_AND_DRINK, vial_of_ichor);
        creativeRegister(ItemGroups.TOOLS, bucket_of_ichor);
    }
}
