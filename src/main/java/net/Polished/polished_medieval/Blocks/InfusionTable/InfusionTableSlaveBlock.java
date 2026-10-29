package net.Polished.polished_medieval.Blocks.InfusionTable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class InfusionTableSlaveBlock extends Block {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public InfusionTableSlaveBlock(Settings settings) {
        super(settings.pistonBehavior(PistonBehavior.BLOCK));
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockPos masterPos = pos.offset(state.get(FACING));

            if (world.getBlockState(masterPos).isOf(InfusionTable.infusion_table)) {
                world.removeBlock(masterPos, false);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);

    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockPos masterPos = pos.offset(state.get(FACING));
        BlockState masterState = world.getBlockState(masterPos);

        if (masterState.isOf(InfusionTable.infusion_table)) {
            return ((InfusionTableBlock) masterState.getBlock()).onUseWithItem(stack, masterState, world, masterPos, player, hand, hit);
        }
        
        System.out.println("Master block not found at expected position: " + masterPos);
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected ActionResult onUse(
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            BlockHitResult hit
    ) {

        // Open the table's handled screen on the server, using its block entity as the screen
        // handler factory. The client receives the screen through the normal handled-screen flow.
        if (!world.isClient()) {
            BlockPos masterPos = pos.offset(state.get(FACING));

            if (world.getBlockEntity(masterPos) instanceof InfusionTableBlockEntity infusionTableBlockEntity) {
                player.openHandledScreen(infusionTableBlockEntity);
            }
        }

        // Report the interaction as handled so it does not fall through to other block-use behavior.
        return ActionResult.SUCCESS;
    }
}
