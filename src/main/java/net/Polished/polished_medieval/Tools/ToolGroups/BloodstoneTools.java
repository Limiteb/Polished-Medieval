package net.Polished.polished_medieval.Tools.ToolGroups;

import net.Polished.polished_medieval.Registry.toolRegistry;
import net.Polished.polished_medieval.Tools.Materials.BloodstoneMaterial;
import net.minecraft.item.*;

public class BloodstoneTools extends toolRegistry {
    public static SwordItem bloodstoneSword = swordRegister("bloodstone_sword", BloodstoneMaterial.INSTANCE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(BloodstoneMaterial.INSTANCE, 2, -1.6F)));
    public static PickaxeItem bloodstonePickaxe = pickaxeRegister("bloodstone_pickaxe", BloodstoneMaterial.INSTANCE);
    public static ShovelItem bloodstoneShovel = shovelRegister("bloodstone_shovel", BloodstoneMaterial.INSTANCE);
    public static AxeItem bloodstoneAxe = axeRegister("bloodstone_axe", BloodstoneMaterial.INSTANCE);
    public static HoeItem bloodstoneHoe = hoeRegister("bloodstone_hoe", BloodstoneMaterial.INSTANCE);
}
