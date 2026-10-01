package net.Polished.polished_medieval.Blocks;

import net.Polished.polished_medieval.Registry.blockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroups;

import static net.Polished.polished_medieval.Blocks.BloodstoneLantern.bloodstone_lantern;
import static net.Polished.polished_medieval.Registry.inventoryRegister.creativeRegister;

public class Blocks extends blockRegistry{
    public static final Block infusion_table = registerBlock("infusion_table", new Block(AbstractBlock.Settings.create().hardness(3.5F)));

    public static void initializeBlocks() {
        creativeRegister(ItemGroups.FUNCTIONAL, infusion_table);
        creativeRegister(ItemGroups.BUILDING_BLOCKS, bloodstone_lantern);
    }
}
