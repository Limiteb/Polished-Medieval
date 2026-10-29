package net.Polished.polished_medieval.Blocks.InfusionTable;

import com.mojang.serialization.MapCodec;
import net.Polished.polished_medieval.Items.Ichor.Ichor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.Polished.polished_medieval.Items.Bloodstone.Bloodstone.bloodstone;
import static net.Polished.polished_medieval.Items.Bloodstone.Bloodstone.bucket_of_crystallized_blood;
import static net.Polished.polished_medieval.Items.Ichor.Ichor.*;

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
        if (!state.isOf(newState.getBlock()) && !world.isClient()) {
            BlockPos slavePos = getSlavePos(pos, state.get(FACING));

            if (world.getBlockState(slavePos).isOf(InfusionTable.infusion_table_slave)) {
                world.removeBlock(slavePos, false);
            }

            if (world.getBlockEntity(pos) instanceof InfusionTableBlockEntity infusionTableBlockEntity) {

                ItemStack bloodstoneStack = new ItemStack(bloodstone, infusionTableBlockEntity.getBloodstoneQuantity());
                ItemStack ichorStack = new ItemStack(drop_of_ichor, infusionTableBlockEntity.getIchorQuantity());
                ItemStack infusionTableStack = new ItemStack(InfusionTable.infusion_table, 1);

                ItemScatterer.spawn(world, pos, infusionTableBlockEntity);
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), infusionTableStack);
                if (!bloodstoneStack.isEmpty()) {
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), bloodstoneStack);
                }
                if (!ichorStack.isEmpty()) {
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), ichorStack);
                }
            }

        }
        super.onStateReplaced(state, world, pos, newState, moved);

    }

    // This is a check essentially to see what direction the block should be facing when placed
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Crafting Logic
        if (!world.isClient() && world.getBlockEntity(pos) instanceof InfusionTableBlockEntity infusionTableBlockEntity && stack.isOf(Items.BRUSH)) {
            if (infusionTableBlockEntity.getStack(0).isEmpty()) {
                return ItemActionResult.FAIL;
            }

            if (infusionTableBlockEntity.craftItem(infusionTableBlockEntity)) {
                return ItemActionResult.SUCCESS;

            } else {
                return ItemActionResult.FAIL;

            }

        }

        // Bloodstone Logic
        if (!world.isClient() && (stack.isOf(bloodstone) || stack.isOf(bucket_of_crystallized_blood)) && world.getBlockEntity(pos) instanceof InfusionTableBlockEntity infusionTableBlockEntity) {
            int quantity = infusionTableBlockEntity.getBloodstoneQuantity();
            int quantityToAdd = 0;

            switch (stack.getItem().toString()) {
                case "polished_medieval:bloodstone" -> quantityToAdd = 1;
                case "polished_medieval:bucket_of_crystallized_blood" -> quantityToAdd = 9;
            }

            if (quantity + quantityToAdd <= 9) {
                infusionTableBlockEntity.setBloodstoneQuantity(quantity + quantityToAdd);
                return getItemActionResult(world, pos, player, hand, quantity, quantityToAdd, bucket_of_crystallized_blood);
            } else {
                return ItemActionResult.FAIL;
            }

            // Ichor Logic
        } else if (!world.isClient() && (stack.isOf(drop_of_ichor) || stack.isOf(vial_of_ichor) || stack.isOf(bucket_of_ichor)) && world.getBlockEntity(pos) instanceof InfusionTableBlockEntity infusionTableBlockEntity) {
            int quantity = infusionTableBlockEntity.getIchorQuantity();
            int quantityToAdd = 0;

            switch (stack.getItem().toString()) {
                case "polished_medieval:drop_of_ichor" -> quantityToAdd = 1;
                case "polished_medieval:vial_of_ichor" -> quantityToAdd = 3;
                case "polished_medieval:bucket_of_ichor" -> quantityToAdd = 9;
            }

            if (quantity + quantityToAdd <= 9) {
                infusionTableBlockEntity.setIchorQuantity(quantity + quantityToAdd);
                return getItemActionResult(world, pos, player, hand, quantity, quantityToAdd, Ichor.bucket_of_ichor);
            } else {
                return ItemActionResult.FAIL;
            }

        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @NotNull
    private ItemActionResult getItemActionResult(World world, BlockPos pos, PlayerEntity player, Hand hand, int quantity, int quantityToAdd, Item bucketOfCrystallizedBlood) {
        if (!player.isCreative()) {
            ItemStack handStack = player.getStackInHand(hand);
            if (handStack.isOf(bucketOfCrystallizedBlood)) {
                handStack.decrement(1);
                // If the consumed bucket was the only item (usual case, max stack is 1):
                if (handStack.isEmpty()) {
                    player.setStackInHand(hand, new ItemStack(Items.BUCKET, 1));
                } else {
                    player.getInventory().insertStack(new ItemStack(Items.BUCKET, 1));
                }
            } else {
                handStack.decrement(1);
            }
            world.playSound(
                    null,
                    pos,
                    SoundEvents.BLOCK_END_PORTAL_FRAME_FILL,
                    SoundCategory.BLOCKS,
                    1.0F,
                    (float) (quantity + quantityToAdd) / 2.0F
            );
        }
        return ItemActionResult.SUCCESS;
    }

    // Creates the block entity for this table's world position. Its BlockState (including FACING) is
    // passed in so the entity can initialize with the state of the placed block.
    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
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

        // Open the table's handled screen on the server, using its block entity as the screen
        // handler factory. The client receives the screen through the normal handled-screen flow.
        if (!world.isClient()) {
            if (world.getBlockEntity(pos) instanceof InfusionTableBlockEntity infusionTableBlockEntity) {
                if (player.getMainHandStack().isEmpty() && player.isSneaking() && !infusionTableBlockEntity.getStack(0).isEmpty()) {
                    player.setStackInHand(Hand.MAIN_HAND, infusionTableBlockEntity.getStack(0));
                    infusionTableBlockEntity.setStack(0, ItemStack.EMPTY);
                } else {
                    player.openHandledScreen(infusionTableBlockEntity);
                }
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
                return world.getBlockState(pos.east()).isReplaceable();
            case WEST:
                return world.getBlockState(pos.south()).isReplaceable();
            case NORTH:
                return world.getBlockState(pos.west()).isReplaceable();
            case EAST:
                return world.getBlockState(pos.north()).isReplaceable();
        }
        return false;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(InfusionTableBlock::new);
    }
}
