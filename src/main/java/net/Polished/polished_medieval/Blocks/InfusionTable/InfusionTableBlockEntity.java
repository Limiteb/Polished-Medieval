package net.Polished.polished_medieval.Blocks.InfusionTable;

import net.Polished.polished_medieval.Screen.InfusionTable.InfusionTableScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import net.Polished.polished_medieval.Tools.Materials.BloodstoneMaterial;
import static net.Polished.polished_medieval.Tools.ToolGroups.BloodstoneTools.*;
import static net.Polished.polished_medieval.Tools.ToolGroups.IchorTools.*;
import static net.minecraft.item.Items.BUCKET;
import static net.Polished.polished_medieval.Registry.blockEntityTypeRegistry.INFUSION_TABLE_BLOCK_ENTITY;

public class InfusionTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, Inventory {

    private int bloodstoneQuantity = 0;
    private int ichorQuantity = 0;

    private boolean craft(InfusionTableBlockEntity infusionTableBlockEntity, Item item) {
        if (item instanceof ToolItem toolItem && toolItem.getMaterial() == BloodstoneMaterial.INSTANCE) {

            if (infusionTableBlockEntity.getBloodstoneQuantity() == 9) {
                infusionTableBlockEntity.setBloodstoneQuantity(0);

                if (infusionTableBlockEntity.getWorld() != null) {
                    infusionTableBlockEntity.getWorld().playSound(
                            null,
                            infusionTableBlockEntity.getPos(),
                            SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE,
                            SoundCategory.BLOCKS,
                            1.0F,
                            1.0F
                    );
                }

                infusionTableBlockEntity.setStack(0, item.getDefaultStack());
                return true;
            } else {
                return false;
            }

        } else if (item instanceof ToolItem) {
            if (infusionTableBlockEntity.getIchorQuantity() == 9) {
                infusionTableBlockEntity.setIchorQuantity(0);

                if (infusionTableBlockEntity.getWorld() != null) {
                    infusionTableBlockEntity.getWorld().playSound(
                            null,
                            infusionTableBlockEntity.getPos(),
                            SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE,
                            SoundCategory.BLOCKS,
                            1.0F,
                            1.0F
                    );
                }

                infusionTableBlockEntity.setStack(0, item.getDefaultStack());
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    // This method is called when the player right-clicks the infusion table with a brush.
    public boolean craftItem(InfusionTableBlockEntity infusionTableBlockEntity) {

        if (infusionTableBlockEntity.getStack(0).getItem() instanceof ToolItem toolItem && toolItem.getMaterial() == ToolMaterials.NETHERITE) {
            // switch case sword axe pickaxe shovel hoe

            switch (toolItem.getTranslationKey()) {
                case "item.minecraft.netherite_sword":
                    return craft(infusionTableBlockEntity, bloodstoneSword);
                case "item.minecraft.netherite_axe":
                    return craft(infusionTableBlockEntity, bloodstoneAxe);
                case "item.minecraft.netherite_pickaxe":
                    return craft(infusionTableBlockEntity, bloodstonePickaxe);
                case "item.minecraft.netherite_shovel":
                    return craft(infusionTableBlockEntity, bloodstoneShovel);
                case "item.minecraft.netherite_hoe":
                    return craft(infusionTableBlockEntity, bloodstoneHoe);
            }
        } else if (infusionTableBlockEntity.getStack(0).getItem() instanceof ToolItem toolItem){
            switch (toolItem.getTranslationKey()) {
                case "item.polished_medieval.bloodstone_sword":
                    return craft(infusionTableBlockEntity, ichorSword);
                case "item.polished_medieval.bloodstone_axe":
                    return craft(infusionTableBlockEntity, ichorAxe);
                case "item.polished_medieval.bloodstone_pickaxe":
                    return craft(infusionTableBlockEntity, ichorPickaxe);
                case "item.polished_medieval.bloodstone_shovel":
                    return craft(infusionTableBlockEntity, ichorShovel);
                case "item.polished_medieval.bloodstone_hoe":
                    return craft(infusionTableBlockEntity, ichorHoe);
            }
        }
        return false;
    }

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
        // Make sure that it is a bucket in either slot 1 or 2. No check for slot 0.
        if (!stack.isOf(BUCKET) && (slot == 1 || slot == 2)) {
            return;
        }

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

    public int getBloodstoneQuantity() {
        return bloodstoneQuantity;
    }

    public int getIchorQuantity() {
        return ichorQuantity;
    }

    public void setBloodstoneQuantity(int bloodstoneQuantity) {
        this.bloodstoneQuantity = bloodstoneQuantity;
        markDirty();
    }

    public void setIchorQuantity(int ichorQuantity) {
        this.ichorQuantity = ichorQuantity;
        markDirty();
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

        nbt.putInt("BloodstoneQuantity", getBloodstoneQuantity());
        nbt.putInt("IchorQuantity", getIchorQuantity());
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);

        Inventories.readNbt(nbt, items, registries);

        setBloodstoneQuantity(nbt.getInt("BloodstoneQuantity"));
        setIchorQuantity(nbt.getInt("IchorQuantity"));
    }
}
