package net.Polished.polished_medieval.Blocks.InfusionTable;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class InfusionTableBlock extends BlockWithEntity {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public InfusionTableBlock(Settings settings) {
        super(settings);

        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
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

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        System.out.println(canPlace(pos, state.get(FACING), world));
        return canPlace(pos, state.get(FACING), world);
    }

    protected boolean canPlace(BlockPos pos, Direction blockDirection, WorldView world) {

        switch (blockDirection) {
            case SOUTH:
                return
                        world.getBlockState(pos.up()).isReplaceable()
                        && world.getBlockState(pos.east().up()).isReplaceable()
                        && world.getBlockState(pos.east()).isReplaceable();
            case WEST:
                return
                        world.getBlockState(pos.up()).isReplaceable()
                                && world.getBlockState(pos.south().up()).isReplaceable()
                                && world.getBlockState(pos.south()).isReplaceable();
            case NORTH:
                return
                        world.getBlockState(pos.up()).isReplaceable()
                                && world.getBlockState(pos.west().up()).isReplaceable()
                                && world.getBlockState(pos.west()).isReplaceable();
            case EAST:
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
