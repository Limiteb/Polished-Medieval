package net.Polished.polished_medieval.Registry;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.Polished.polished_medieval.Polished_medieval.MOD_ID;

public class toolRegistry {
    public static Item swordRegister(String name, ToolMaterial material) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new SwordItem(material, new Item.Settings()));
    }
    public static Item pickaxeRegister(String name, ToolMaterial material) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new PickaxeItem(material, new Item.Settings()));
    }
    public static Item axeRegister(String name, ToolMaterial material) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new AxeItem(material, new Item.Settings()));
    }
    public static Item shovelRegister(String name, ToolMaterial material) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new ShovelItem(material, new Item.Settings()));
    }
    public static Item hoeRegister(String name, ToolMaterial material) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), new HoeItem(material, new Item.Settings()));
    }
}
