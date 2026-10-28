package net.Polished.polished_medieval.Tools.Dagger;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

import static net.Polished.polished_medieval.Tools.Dagger.Dagger.BloodstoneDagger;
import static net.Polished.polished_medieval.Tools.Dagger.Dagger.DaggerEntityType;

public class DaggerEntity extends ThrownItemEntity {
    public DaggerEntity(World world, LivingEntity owner) {
        super(DaggerEntityType, owner, world);
    }
    public DaggerEntity(World world, double x, double y, double z) {
        super(DaggerEntityType, x, y, z, world);
    }

    public DaggerEntity(EntityType<? extends DaggerEntity> entityType, World world) {
        super(entityType, world);
    }
    @Override
    protected Item getDefaultItem() {
        return BloodstoneDagger;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().damage(this.getDamageSources().thrown(this, this.getOwner()), 5.0F);
    }
    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient()) {
            this.dropItem(this.getDefaultItem());
            this.discard();
        }
    }
}
