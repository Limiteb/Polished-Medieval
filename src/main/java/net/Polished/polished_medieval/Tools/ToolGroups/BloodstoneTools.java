package net.Polished.polished_medieval.Tools.ToolGroups;

import net.Polished.polished_medieval.Registry.toolRegistry;
import net.Polished.polished_medieval.Tools.Materials.BloodstoneMaterial;
import net.minecraft.item.Item;

import static net.Polished.polished_medieval.Registry.inventoryRegister.creativeRegister;

public class BloodstoneTools extends toolRegistry {
    public static Item bloodstoneSword = swordRegister("bloodstone_sword", new BloodstoneMaterial());
    public static Item bloodstonePickaxe = pickaxeRegister("bloodstone_pickaxe", new BloodstoneMaterial());
    public static Item bloodstoneShovel = shovelRegister("bloodstone_shovel", new BloodstoneMaterial());
    public static Item bloodstoneAxe = axeRegister("bloodstone_axe", new BloodstoneMaterial());
    public static Item bloodstoneHoe = hoeRegister("bloodstone_hoe", new BloodstoneMaterial());
}
