package net.Polished.polished_medieval.Tools;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;

public class BloodstoneMaterial implements ToolMaterial {

    @Override
    public int getDurability() {
        return 1500;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 10;
    }

    @Override
    public float getAttackDamage() {
        return 6;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return null;
    }

    @Override
    public int getEnchantability() {
        return 30;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
