package net.Polished.polished_medieval.Tools;

import net.Polished.polished_medieval.Registry.toolRegistry;
import net.Polished.polished_medieval.Tools.Materials.BloodstoneMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;

public class BloodstoneTools extends toolRegistry {
    // Bloodstone tools
    public static Item bloodstoneSword = swordRegister("bloodstone_sword", new BloodstoneMaterial());
    public static Item bloodstonePickaxe = pickaxeRegister("bloodstone_pickaxe", new BloodstoneMaterial());
    public static Item bloodstoneShovel = shovelRegister("bloodstone_shovel", new BloodstoneMaterial());
    public static Item bloodstoneAxe = axeRegister("bloodstone_axe", new BloodstoneMaterial());
    public static Item bloodstoneHoe = hoeRegister("bloodstone_hoe", new BloodstoneMaterial());
    //ICHOR tools


    public static void creativeInventoryRegister() {
        creativeRegister(ItemGroups.COMBAT, bloodstoneSword);
        creativeRegister(ItemGroups.COMBAT, bloodstoneAxe);
        creativeRegister(ItemGroups.TOOLS, bloodstoneAxe);
        creativeRegister(ItemGroups.TOOLS, bloodstonePickaxe);
        creativeRegister(ItemGroups.TOOLS, bloodstoneShovel);
        creativeRegister(ItemGroups.TOOLS, bloodstoneHoe);
    }
}
