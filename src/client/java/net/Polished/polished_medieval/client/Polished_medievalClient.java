package net.Polished.polished_medieval.client;

import net.Polished.polished_medieval.client.Screen.InfusionTable.InfusionTableScreen;
import net.Polished.polished_medieval.Screen.ModScreenHandlers;
import net.Polished.polished_medieval.Tools.Dagger.Dagger;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class Polished_medievalClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Initializes the Handled Screen
        // InfusionTableScreen is a method reference, specifically the constructor of the InfusionTableScreen class.
        HandledScreens.register(ModScreenHandlers.INFUSION_TABLE_SCREEN_HANDLER, InfusionTableScreen::new);
        EntityRendererRegistry.register(Dagger.DaggerEntityType, FlyingItemEntityRenderer::new);
    }
}
