package net.Polished.polished_medieval.Tools.ToolGroups;

import net.Polished.polished_medieval.Registry.toolRegistry;
import net.Polished.polished_medieval.Tools.Materials.IchorMaterial;
import net.minecraft.item.Item;

public class IchorTools extends toolRegistry {
    public static Item ichorSword = swordRegister("ichor_sword", new IchorMaterial());
    public static Item ichorPickaxe = pickaxeRegister("ichor_pickaxe", new IchorMaterial());
    public static Item ichorShovel = shovelRegister("ichor_shovel", new IchorMaterial());
    public static Item ichorAxe = axeRegister("ichor_axe", new IchorMaterial());
    public static Item ichorHoe = hoeRegister("ichor_hoe", new IchorMaterial());
}
