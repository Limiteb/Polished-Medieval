package net.Polished.polished_medieval.Blocks.InfusionTable;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class InfusionTableBlock extends BlockWithEntity {

    public InfusionTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        System.out.println("Block entity created at: " + pos);
        return new InfusionTableBlockEntity(pos, state);
    }

    @Override
    protected ActionResult onUse(
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            BlockHitResult hit
    ) {
        System.out.println(pos);
        return ActionResult.SUCCESS;
    }

//    @Override
//    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
//
//        return canPlace();
//    }

    protected boolean canPlace(ItemPlacementContext context, BlockState state) {
        String lookDirection = context.getHorizontalPlayerFacing().toString();
        BlockPos pos = context.getBlockPos();
        WorldView world = context.getWorld();

        switch (lookDirection) {
            case "North":
                return
                        world.getBlockState(pos.up()).isReplaceable()
                        && world.getBlockState(pos.east().up()).isReplaceable()
                        && world.getBlockState(pos.east()).isReplaceable();
            case "East":
                return
                        world.getBlockState(pos.up()).isReplaceable()
                                && world.getBlockState(pos.south().up()).isReplaceable()
                                && world.getBlockState(pos.south()).isReplaceable();
            case "South":
                return
                        world.getBlockState(pos.up()).isReplaceable()
                                && world.getBlockState(pos.west().up()).isReplaceable()
                                && world.getBlockState(pos.west()).isReplaceable();
            case "West":
                return
                        world.getBlockState(pos.up()).isReplaceable()
                                && world.getBlockState(pos.north().up()).isReplaceable()
                                && world.getBlockState(pos.north()).isReplaceable();
        }
        return false;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(InfusionTableBlock::new);
    }
}
