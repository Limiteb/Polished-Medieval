package net.Polished.polished_medieval.Blocks.InfusionTable;

import net.Polished.polished_medieval.Screen.InfusionTable.InfusionTableScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import static net.Polished.polished_medieval.Registry.blockEntityTypeRegistry.INFUSION_TABLE_BLOCK_ENTITY;

public class InfusionTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, Inventory {

    // This sets the number of item slots, and the defaault item stack.
    private final DefaultedList<ItemStack> items =
            DefaultedList.ofSize(3, ItemStack.EMPTY);

    // Block entity creation, this method is called in another class.
    public InfusionTableBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(INFUSION_TABLE_BLOCK_ENTITY, blockPos, blockState);
    }

    // inventory data
    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    // Item display name, what will show up as the name for the actual item, and when you look at the block with Jade for example.
    @Override
    public Text getDisplayName() {
        return Text.literal("Infusion Table");
    }

    // Creates the inventory
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new InfusionTableScreenHandler(syncId, playerInventory, this.pos);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.get(0).isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = Inventories.splitStack(items, slot, amount);

        if (!stack.isEmpty()) {
            markDirty();
        }

        return stack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(items, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        items.set(slot, stack);
        markDirty();
    }

    // Checks to see if the player is within block range to open the inventory
    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.isRemoved()) { return false; } // Checks if the block exists

        return player.squaredDistanceTo(
                this.pos.getX() + 0.5,
                this.pos.getY() + 0.5,
                this.pos.getZ() + 0.5
        ) <= 64.0;
    }

    @Override
    public void clear() {
        items.clear();
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        Inventories.writeNbt(nbt, items, registries);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        Inventories.readNbt(nbt, items, registries);
    }
}
