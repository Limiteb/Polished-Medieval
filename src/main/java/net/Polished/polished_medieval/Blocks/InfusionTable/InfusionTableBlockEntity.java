package net.Polished.polished_medieval.Blocks.InfusionTable;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

import static net.Polished.polished_medieval.Registry.blockEntityTypeRegistry.INFUSION_TABLE_BLOCK_ENTITY;

public class InfusionTableBlockEntity extends BlockEntity {

    public InfusionTableBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(INFUSION_TABLE_BLOCK_ENTITY, blockPos, blockState);
    }

}
