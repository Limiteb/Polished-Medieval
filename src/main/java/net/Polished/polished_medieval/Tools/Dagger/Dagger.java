package net.Polished.polished_medieval.Tools.Dagger;

import net.Polished.polished_medieval.Tools.Materials.BloodstoneMaterial;
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
    public Dagger(ToolMaterial material, Item.Settings settings) {
        super(material, settings);
    }
    public static Item BloodstoneDagger = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "bloodstone_dagger"), new Dagger(BloodstoneMaterial.INSTANCE,  new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(BloodstoneMaterial.INSTANCE, 2, -1.6F))));
    //public static Item IchorDagger = Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "ichor_dagger"), new Dagger(BloodstoneMaterial.INSTANCE,  new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(BloodstoneMaterial.INSTANCE, 4, -1.6F))));
    public static EntityType<DaggerEntity> DaggerEntityType = Registry.register(Registries.ENTITY_TYPE, Identifier.of(MOD_ID, "dagger_entitytype"), EntityType.Builder.<DaggerEntity>create(DaggerEntity::new, SpawnGroup.MISC).dimensions(0.25f,0.25f).build());

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        DaggerEntity daggerEntity = new DaggerEntity(world, user);
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient()) {
            daggerEntity.setItem(itemStack);
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
        return daggerEntity;
    }
}
