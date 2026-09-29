package net.Polished.polished_medieval.Tools;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.Polished.polished_medieval.Polished_medieval.MOD_ID;

public class toolRegistry {
    public static SwordItem swordRegister(String name, ToolMaterial material) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new SwordItem(material, new Item.Settings()));
    }
    public static PickaxeItem pickaxeRegister(String name, ToolMaterial material) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new PickaxeItem(material, new Item.Settings()));
    }
    public static AxeItem axeRegister(String name, ToolMaterial material) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new AxeItem(material, new Item.Settings()));
    }
    public static ShovelItem shovelRegister(String name, ToolMaterial material) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new ShovelItem(material, new Item.Settings()));
    }
    public static HoeItem hoeRegister(String name, ToolMaterial material) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new HoeItem(material, new Item.Settings()));
    }

    public static void registerItems() {
        Item bloodStoneSword = swordRegister("bloodstone_sword", new BloodstoneMaterial());
        Item bloodStoneShovel = shovelRegister("bloodstone_shovel", new BloodstoneMaterial());
        Item bloodStoneAxe = axeRegister("bloodstone_axe", new BloodstoneMaterial());
        Item bloodStonePickaxe = pickaxeRegister("bloodstone_pickaxe", new BloodstoneMaterial());
        Item bloodStoneHoe = hoeRegister("bloodstone_hoe", new BloodstoneMaterial());
    }
}
