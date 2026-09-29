package net.Polished.polished_medieval.Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class blockRegistry {

    private static void registerBlockItem(String name, Block block) {
        Registry.register(
                Registries.ITEM,
                Identifier.of("polished_medieval", name),
                new BlockItem(block, new Item.Settings())
        );
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(
                Registries.BLOCK,
                Identifier.of("polished_medieval", name),
                block
        );
    }

    public static final Block testBlock = registerBlock(
            "test_block",
            new Block(AbstractBlock.Settings.create())
    );

    public static void LoadBlockRegistry() {
    }
}
