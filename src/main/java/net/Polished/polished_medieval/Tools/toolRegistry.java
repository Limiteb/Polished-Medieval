package net.Polished.polished_medieval.Tools;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.Polished.polished_medieval.Polished_medieval.MOD_ID;

public class toolRegistry {
    public static void swordRegister(String name, ToolMaterial material) {
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new SwordItem(material, new Item.Settings()));
    }
    public static void pickaxeRegister(String name, ToolMaterial material) {
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new PickaxeItem(material, new Item.Settings()));
    }
    public static void axeRegister(String name, ToolMaterial material) {
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new AxeItem(material, new Item.Settings()));
    }
    public static void shovelRegister(String name, ToolMaterial material) {
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new ShovelItem(material, new Item.Settings()));
    }
    public static void hoeRegister(String name, ToolMaterial material) {
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new HoeItem(material, new Item.Settings()));
    }

    public static void registerItems() {
        swordRegister("bloodstone_sword", new BloodstoneMaterial());
        shovelRegister("bloodstone_shovel", new BloodstoneMaterial());
        axeRegister("bloodstone_axe", new BloodstoneMaterial());
        pickaxeRegister("bloodstone_pickaxe", new BloodstoneMaterial());
        hoeRegister("bloodstone_hoe", new BloodstoneMaterial());
    }
}
