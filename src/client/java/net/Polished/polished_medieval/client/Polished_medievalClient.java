package net.Polished.polished_medieval.client;

import net.Polished.polished_medieval.Tools.Dagger.Dagger;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class Polished_medievalClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Dagger.DaggerEntityType, FlyingItemEntityRenderer::new);
    }
}
