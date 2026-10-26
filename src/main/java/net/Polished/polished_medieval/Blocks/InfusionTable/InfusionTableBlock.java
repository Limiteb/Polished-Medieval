package net.Polished.polished_medieval.Blocks.InfusionTable;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
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
        // Blocks have a default state that is used when no state is specified, including with /setblock.
        // This table defaults to facing north; player placement selects a direction in getPlacementState.
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    // Registers FACING as one of this block's state properties.
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        // FACING is a vanilla horizontal-direction property; registering it makes it part of this block's states.
        builder.add(FACING);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient()) {
            BlockPos slavePos = getSlavePos(pos, state.get(FACING));

            Direction slaveToMaster = getSlaveDirection(state.get(FACING));

            world.setBlockState(
                    slavePos,
                    InfusionTable.infusion_table_slave.getDefaultState().with(InfusionTableSlaveBlock.FACING, slaveToMaster)
            );
        }
    }

    private Direction getSlaveDirection(Direction masterFacing) {
        return switch (masterFacing) {
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            default -> Direction.NORTH;
        };
    }

    private BlockPos getSlavePos(BlockPos masterPos, Direction facing) {
        return switch (facing) {
            case SOUTH -> masterPos.east();
            case WEST -> masterPos.south();
            case NORTH -> masterPos.west();
            case EAST -> masterPos.north();
            default -> masterPos;
        };
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockPos slavePos = getSlavePos(pos, state.get(FACING));

            if (world.getBlockState(slavePos).isOf(InfusionTable.infusion_table_slave)) {
                world.removeBlock(slavePos, false);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);

    }

    // This is a check essentially to see what direction the block should be facing when placed
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
    }

    // Creates the block entity for this table's world position. Its BlockState (including FACING) is
    // passed in so the entity can initialize with the state of the placed block.
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        System.out.println("Block entity created at: " + pos);
        return new InfusionTableBlockEntity(pos, state);
    }


    // Called when a player uses the block, such as by right-clicking it.
    @Override
    protected ActionResult onUse(
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            BlockHitResult hit
    ) {
//        if (!world.isClient()) {
//            player.openHandledScreen(createScreenHandlerFactory(state, world, pos));
//            System.out.println(pos);
//        }

        // Open the table's handled screen on the server, using its block entity as the screen
        // handler factory. The client receives the screen through the normal handled-screen flow.
        if (!world.isClient()) {
            if (world.getBlockEntity(pos) instanceof InfusionTableBlockEntity infusionTableBlockEntity) {
                player.openHandledScreen(infusionTableBlockEntity);
            }
        }

        // Report the interaction as handled so it does not fall through to other block-use behavior.
        return ActionResult.SUCCESS;
    }

    // Minecraft calls this placement-validity check before placing the block. Reject placement if
    // the additional cell occupied by the table's structure is not replaceable.
    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canPlace(pos, state.get(FACING), world);
    }


    // This boolean is used to check if the block can be placed in a 1x1X2 area.
    protected boolean canPlace(BlockPos pos, Direction blockDirection, WorldView world) {
        switch (blockDirection) {
            case SOUTH:
                return
                        world.getBlockState(pos.east()).isReplaceable();
            case WEST:
                return
                        world.getBlockState(pos.south()).isReplaceable();
            case NORTH:
                return
                        world.getBlockState(pos.west()).isReplaceable();
            case EAST:
                return
                        world.getBlockState(pos.north()).isReplaceable();
        }
        return false;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(InfusionTableBlock::new);
    }
}
