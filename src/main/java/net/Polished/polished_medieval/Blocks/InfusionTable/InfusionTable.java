package net.Polished.polished_medieval.Blocks.InfusionTable;

import net.Polished.polished_medieval.Registry.blockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class InfusionTable extends blockRegistry {
    public static final Block infusion_table = registerBlock("infusion_table", new net.Polished.polished_medieval.Blocks.InfusionTable.InfusionTableBlock(AbstractBlock.Settings.create()));
}
