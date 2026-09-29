package net.Polished.polished_medieval;

import net.Polished.polished_medieval.Items.itemRegistry;
import net.Polished.polished_medieval.Tools.toolRegistry;
import net.fabricmc.api.ModInitializer;

public class Polished_medieval implements ModInitializer {
    public static String MOD_ID = "polished_medieval";
    @Override
    public void onInitialize() {
        toolRegistry.registerItems();
    }
}
