package net.Polished.polished_medieval.mixin;

import net.Polished.polished_medieval.player.ReputationData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements ReputationData {
    @Unique
    private int reputation = 0;

    @Override
    public int getReputation() {
        return this.reputation;
    }

    @Override
    public void setReputation(int value) {
        this.reputation = value;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("MyModCustomScore", this.reputation);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("MyModCustomScore")) {
            this.reputation = nbt.getInt("MyModCustomScore");
        }
    }
}
