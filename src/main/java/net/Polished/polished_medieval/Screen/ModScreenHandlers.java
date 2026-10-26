package net.Polished.polished_medieval.Screen;

import net.Polished.polished_medieval.Screen.InfusionTable.InfusionTableScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import static net.Polished.polished_medieval.Polished_medieval.MOD_ID;

public class ModScreenHandlers {
    public static final ScreenHandlerType<InfusionTableScreenHandler> INFUSION_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MOD_ID, "infusion_table_screen_handler"),
                    new ExtendedScreenHandlerType<>(InfusionTableScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {

    }
}
