package net.Polished.polished_medieval.Blocks.InfusionTable;

import net.Polished.polished_medieval.Registry.blockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.sound.BlockSoundGroup;

public class InfusionTable extends blockRegistry {
    // Infusion table block registry
    public static final Block infusion_table = registerBlock("infusion_table", new net.Polished.polished_medieval.Blocks.InfusionTable.InfusionTableBlock(AbstractBlock.Settings.create().strength(2.5f).sounds(BlockSoundGroup.WOOD)));
    public static final Block infusion_table_slave = registerBlockWithoutItem("infusion_table_slave", new net.Polished.polished_medieval.Blocks.InfusionTable.InfusionTableSlaveBlock(AbstractBlock.Settings.create().strength(2.5f).sounds(BlockSoundGroup.WOOD)));
}
