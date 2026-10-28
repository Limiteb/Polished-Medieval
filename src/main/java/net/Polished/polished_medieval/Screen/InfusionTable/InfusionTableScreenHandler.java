package net.Polished.polished_medieval.Screen.InfusionTable;

import net.Polished.polished_medieval.Screen.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

import static net.minecraft.item.Items.BUCKET;

public class InfusionTableScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public InfusionTableScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(pos));
    }

    public InfusionTableScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreenHandlers.INFUSION_TABLE_SCREEN_HANDLER, syncId);
        this.inventory = (Inventory) blockEntity;

        this.addSlot(new Slot(inventory, 0, 80, 35));
        this.addSlot(new Slot(inventory, 1, 56, 11) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(BUCKET);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });
        this.addSlot(new Slot(inventory, 2, 104, 59){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(BUCKET);
            }

            @Override
            public int getMaxItemCount() {
                return 1;
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        Slot sourceSlot = this.getSlot(invSlot);

        if (!sourceSlot.hasStack()) { return ItemStack.EMPTY; }

        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copyStack = sourceStack.copy();

        if (invSlot >= 3) {
            if (!this.insertItem(sourceStack, 1, 2, false)
                    && !this.insertItem(sourceStack, 2, 3, false)
                    && !this.insertItem(sourceStack, 0, 1, false)) {
                return ItemStack.EMPTY;
            }
        } else if (!this.insertItem(sourceStack, 3, this.slots.size(), true)) {
            return ItemStack.EMPTY;
        }

        if (sourceStack.isEmpty()) {
            sourceSlot.setStack(ItemStack.EMPTY);
        } else {
            sourceSlot.markDirty();
        }

        return copyStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
