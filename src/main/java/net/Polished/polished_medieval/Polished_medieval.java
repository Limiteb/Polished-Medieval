package net.Polished.polished_medieval;

import net.Polished.polished_medieval.Items.itemRegistry;
import net.Polished.polished_medieval.Blocks.blockRegistry;
import net.fabricmc.api.ModInitializer;

public class Polished_medieval implements ModInitializer {

    @Override
    public void onInitialize() {
        itemRegistry.registerItems();
        blockRegistry.LoadBlockRegistry();
    }
}
