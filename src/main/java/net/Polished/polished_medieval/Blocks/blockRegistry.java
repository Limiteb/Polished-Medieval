package net.Polished.polished_medieval.Blocks;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class blockRegistry {
    // Load BLock Registry
    public static void LoadBlockRegistry() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> content.add(infusion_table));
    }

    // Block Item Registry Method
    private static void registerBlockItem(String name, Block block) {
        Registry.register(
                Registries.ITEM,
                Identifier.of("polished_medieval", name),
                new BlockItem(block, new Item.Settings())
        );
    }

    // Block Registry Method
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(
                Registries.BLOCK,
                Identifier.of("polished_medieval", name),
                block
        );
    }

    // Initialize Block
    public static final Block infusion_table = registerBlock(
            "infusion_table",
            new Block(AbstractBlock.Settings.create())
    );
}
