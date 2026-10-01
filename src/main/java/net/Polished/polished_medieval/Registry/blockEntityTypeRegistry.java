package net.Polished.polished_medieval.Registry;

import net.Polished.polished_medieval.Blocks.InfusionTable.InfusionTableBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.Polished.polished_medieval.Blocks.Blocks.infusion_table;
import static net.Polished.polished_medieval.Polished_medieval.MOD_ID;

public class blockEntityTypeRegistry {
    public static final BlockEntityType<InfusionTableBlockEntity> INFUSION_TABLE_BLOCK_ENTITY = Registry
            .register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of(MOD_ID, "infusion_table_block_entity"),
                    BlockEntityType.Builder.create(InfusionTableBlockEntity::new, infusion_table).build()
            );

    public static void initializeBlockEntityTypes() {

    }
}
