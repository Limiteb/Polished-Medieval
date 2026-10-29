package net.Polished.polished_medieval.Tools;

import net.minecraft.item.ItemGroups;

import static net.Polished.polished_medieval.Registry.inventoryRegister.creativeRegister;
import static net.Polished.polished_medieval.Tools.Dagger.DaggerTools.*;
import static net.Polished.polished_medieval.Tools.ToolGroups.BloodstoneTools.*;
import static net.Polished.polished_medieval.Tools.ToolGroups.IchorTools.*;

public class Tools {
    public static void initializeTools() {
        creativeRegister(ItemGroups.COMBAT, bloodstoneSword);
        creativeRegister(ItemGroups.COMBAT, bloodstoneAxe);
        creativeRegister(ItemGroups.COMBAT, ichorSword);
        creativeRegister(ItemGroups.COMBAT, ichorAxe);
        creativeRegister(ItemGroups.TOOLS, ichorAxe);
        creativeRegister(ItemGroups.TOOLS, ichorPickaxe);
        creativeRegister(ItemGroups.TOOLS, ichorShovel);
        creativeRegister(ItemGroups.TOOLS, ichorHoe);
        creativeRegister(ItemGroups.TOOLS, bloodstoneAxe);
        creativeRegister(ItemGroups.TOOLS, bloodstonePickaxe);
        creativeRegister(ItemGroups.TOOLS, bloodstoneShovel);
        creativeRegister(ItemGroups.TOOLS, bloodstoneHoe);

        creativeRegister(ItemGroups.COMBAT, BloodstoneDagger);
        creativeRegister(ItemGroups.COMBAT, IchorDagger);
        creativeRegister(ItemGroups.COMBAT, WoodenDagger);
        creativeRegister(ItemGroups.COMBAT, StoneDagger);
        creativeRegister(ItemGroups.COMBAT, IronDagger);
        creativeRegister(ItemGroups.COMBAT, GoldDagger);
        creativeRegister(ItemGroups.COMBAT, DiamondDagger);
        creativeRegister(ItemGroups.COMBAT, NetheriteDagger);
    }
}
