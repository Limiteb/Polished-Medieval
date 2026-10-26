package net.Polished.polished_medieval.client;

import net.Polished.polished_medieval.client.Screen.InfusionTable.InfusionTableScreen;
import net.Polished.polished_medieval.Screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class Polished_medievalClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Initializes the Handled Screen
        // InfusionTableScreen is a method reference, specifically the constructor of the InfusionTableScreen class.
        HandledScreens.register(ModScreenHandlers.INFUSION_TABLE_SCREEN_HANDLER, InfusionTableScreen::new);
    }
}
