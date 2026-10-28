package net.Polished.polished_medieval.Registry;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.Polished.polished_medieval.Polished_medieval.MOD_ID;

public class toolRegistry {
    public static SwordItem swordRegister(String name, ToolMaterial material, Item.Settings settings) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new SwordItem(material, settings));
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
}
