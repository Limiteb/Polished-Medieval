package net.Polished.polished_medieval.Tools;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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
    public static Item bloodstoneSword = swordRegister("bloodstone_sword", new BloodstoneMaterial());
    public static Item bloodstonePickaxe = pickaxeRegister("bloodstone_pickaxe", new BloodstoneMaterial());
    public static Item bloodstoneShovel = shovelRegister("bloodstone_shovel", new BloodstoneMaterial());
    public static Item bloodstoneAxe = axeRegister("bloodstone_axe", new BloodstoneMaterial());
    public static Item bloodstoneHoe = hoeRegister("bloodstone_hoe", new BloodstoneMaterial());
    public static void registerItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(bloodstoneSword));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.add(bloodstonePickaxe));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.add(bloodstoneShovel));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.add(bloodstoneAxe));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(bloodstoneAxe));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.add(bloodstoneHoe));
    }
}
