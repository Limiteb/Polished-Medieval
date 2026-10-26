package net.Polished.polished_medieval.Blocks.BloodstoneLantern;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

import static net.Polished.polished_medieval.Registry.blockRegistry.registerBlock;

public class BloodstoneLantern {
    // Bloodstone Lantern block registry, includes some basic block settings.
    public static final Block bloodstone_lantern = registerBlock("bloodstone_lantern", new Block(AbstractBlock.Settings.create().luminance(blockstate -> 15)));

}
