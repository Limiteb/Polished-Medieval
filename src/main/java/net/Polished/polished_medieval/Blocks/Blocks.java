package net.Polished.polished_medieval.Blocks;

import net.Polished.polished_medieval.Blocks.InfusionTable.InfusionTableBlock;
import net.Polished.polished_medieval.Registry.blockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroups;

import static net.Polished.polished_medieval.Registry.inventoryRegister.creativeRegister;

public class Blocks extends blockRegistry{
    public static final Block infusion_table = registerBlock("infusion_table", new InfusionTableBlock(AbstractBlock.Settings.create()));
    // When registering the block I need to register it as a new InfusionTableBlock, otherwise the class is unused.

    public static void initializeBlocks() {
        creativeRegister(ItemGroups.FUNCTIONAL, infusion_table);
    }
}
