package net.Polished.polished_medieval.Blocks;

import net.Polished.polished_medieval.Registry.blockRegistry;
import net.minecraft.item.ItemGroups;

import static net.Polished.polished_medieval.Blocks.BloodstoneLantern.BloodstoneLantern.bloodstone_lantern;
import static net.Polished.polished_medieval.Blocks.InfusionTable.InfusionTable.infusion_table;
import static net.Polished.polished_medieval.Registry.inventoryRegister.creativeRegister;

public class Blocks extends blockRegistry{

    public static void initializeBlocks() {
        creativeRegister(ItemGroups.FUNCTIONAL, infusion_table);
        creativeRegister(ItemGroups.BUILDING_BLOCKS, bloodstone_lantern);
    }
}
