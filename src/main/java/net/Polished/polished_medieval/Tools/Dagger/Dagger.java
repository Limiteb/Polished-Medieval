package net.Polished.polished_medieval.Tools.Dagger;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

import static net.Polished.polished_medieval.Polished_medieval.MOD_ID;

public class Dagger extends SwordItem implements ProjectileItem {
    private final int attackDamage;
    public Dagger(ToolMaterial material, Item.Settings settings, int attackDamage) {
        super(material, settings);
        this.attackDamage = attackDamage;
    }
    public static EntityType<DaggerEntity> DaggerEntityType = Registry.register(Registries.ENTITY_TYPE, Identifier.of(MOD_ID, "dagger_entitytype"), EntityType.Builder.<DaggerEntity>create(DaggerEntity::new, SpawnGroup.MISC).dimensions(0.25f,0.25f).build());

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        DaggerEntity daggerEntity = new DaggerEntity(world, user);
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient()) {
            daggerEntity.setItem(itemStack);
            daggerEntity.setDamage(attackDamage);
            daggerEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F, 0.0F);
            world.spawnEntity(daggerEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }


    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        DaggerEntity daggerEntity = new DaggerEntity(world, pos.getX(), pos.getY(), pos.getZ());
        daggerEntity.setItem(stack);
        if (stack.getItem() instanceof Dagger dagger) {
            daggerEntity.setDamage(dagger.attackDamage);
        }
        return daggerEntity;
    }
}
