package net.Polished.polished_medieval.Tools;

import net.minecraft.item.ItemGroups;

import static net.Polished.polished_medieval.Registry.inventoryRegister.creativeRegister;
import static net.Polished.polished_medieval.Tools.ToolGroups.BloodstoneTools.*;

public class Tools {
    public static void initializeTools() {
        creativeRegister(ItemGroups.COMBAT, bloodstoneSword);
        creativeRegister(ItemGroups.COMBAT, bloodstoneAxe);
        creativeRegister(ItemGroups.TOOLS, bloodstoneAxe);
        creativeRegister(ItemGroups.TOOLS, bloodstonePickaxe);
        creativeRegister(ItemGroups.TOOLS, bloodstoneShovel);
        creativeRegister(ItemGroups.TOOLS, bloodstoneHoe);
    }
}
