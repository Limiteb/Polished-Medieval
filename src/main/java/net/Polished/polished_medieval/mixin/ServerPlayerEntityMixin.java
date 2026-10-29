package net.Polished.polished_medieval.mixin;

import net.Polished.polished_medieval.player.ReputationData;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "copyFrom", at = @At("TAIL"))
    private void copyReputation(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        ((ReputationData) this).setReputation(((ReputationData) oldPlayer).getReputation());
    }
}
