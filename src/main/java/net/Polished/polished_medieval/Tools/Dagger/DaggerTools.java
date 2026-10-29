package net.Polished.polished_medieval.Tools.Dagger;

import net.Polished.polished_medieval.Tools.Materials.BloodstoneMaterial;
import net.Polished.polished_medieval.Tools.Materials.IchorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.Polished.polished_medieval.Polished_medieval.MOD_ID;

public class DaggerTools {

    private static Item registerDagger(String path, ToolMaterial material, int attackDamage, float attackSpeed) {
        return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, path), new Dagger(material, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(material, attackDamage, attackSpeed)), attackDamage));
    }

    public static Item BloodstoneDagger = registerDagger("bloodstone_dagger", BloodstoneMaterial.INSTANCE, 7, -1.6F);
    public static Item IchorDagger = registerDagger("ichor_dagger", IchorMaterial.INSTANCE, 8, -1.6F);

    public static Item WoodenDagger = registerDagger("wooden_dagger", ToolMaterials.WOOD, 1, -1.6F);
    public static Item StoneDagger = registerDagger("stone_dagger", ToolMaterials.STONE, 2, -1.6F);
    public static Item IronDagger = registerDagger("iron_dagger", ToolMaterials.IRON, 3, -1.6F);
    public static Item GoldDagger = registerDagger("gold_dagger", ToolMaterials.GOLD, 4, -1.6F);
    public static Item DiamondDagger = registerDagger("diamond_dagger", ToolMaterials.DIAMOND, 5, -1.6F);
    public static Item NetheriteDagger = registerDagger("netherite_dagger", ToolMaterials.NETHERITE, 6, -1.6F);



}
