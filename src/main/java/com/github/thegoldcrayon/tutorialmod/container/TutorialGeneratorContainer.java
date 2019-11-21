package com.github.thegoldcrayon.tutorialmod.container;

import com.github.thegoldcrayon.tutorialmod.energy.SettableEnergyStorage;
import com.github.thegoldcrayon.tutorialmod.init.ModBlocks;
import com.github.thegoldcrayon.tutorialmod.init.ModContainerTypes;
import com.github.thegoldcrayon.tutorialmod.tileentity.TutorialGeneratorTileEntity;
import jdk.nashorn.internal.objects.annotations.Setter;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ReportedException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TutorialGeneratorContainer extends Container
{

    public final TutorialGeneratorTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;


    //Logical server-side constructor
    public TutorialGeneratorContainer(final int windowID, final TutorialGeneratorTileEntity tileEntity, final PlayerInventory playerInventory, final PlayerEntity player)
    {

        super(ModContainerTypes.TUTORIAL_GENERATOR, windowID);

        canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
        this.tileEntity = tileEntity;

        addSlots(tileEntity, playerInventory);

        trackInt(new IntReferenceHolder()
        {

            @Override
            public int get()
            {

                return getEnergy();

            }

            @Override
            public void set(int value)
            {

                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((SettableEnergyStorage) h).setEnergy(value));

            }

        });

    }

    //Logical client-side constructor
    public TutorialGeneratorContainer(final int windowID, final PlayerInventory playerInventory, PacketBuffer data)
    {

        super(ModContainerTypes.TUTORIAL_GENERATOR, windowID);

        final TutorialGeneratorTileEntity tileEntity = getTutorialGeneratorTileEntity(playerInventory, data);
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
        this.tileEntity = tileEntity;

        addSlots(tileEntity, playerInventory);

        trackInt(new IntReferenceHolder()
        {

            @Override
            public int get()
            {

                return getEnergy();

            }

            @Override
            public void set(int value)
            {

                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((SettableEnergyStorage) h).setEnergy(value));

            }

        });

    }

    public int getEnergy()
    {

        return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);

    }

    //Gets the TutorialGeneratorTileEntity at position or crashes
    private TutorialGeneratorTileEntity getTutorialGeneratorTileEntity(final PlayerInventory playerInventory, final PacketBuffer data)
    {

        final BlockPos pos = data.readBlockPos();
        final World world = playerInventory.player.world;
        final TileEntity tileAtPos = world.getTileEntity(pos);

        if(!(tileAtPos instanceof TutorialGeneratorTileEntity))
        {

            final Throwable error;
            if(tileAtPos == null)
                error = new NullPointerException("No TutorialGeneratorTileEntity at position.");
            else
                error = new ClassCastException(tileAtPos.getClass() + " is not a TutorialGeneratorTileEntity");

            CrashReport crashReport = CrashReport.makeCrashReport(error, "Creating Container for a TutorialGeneratorTileEntity");
            CrashReportCategory category = crashReport.makeCategory("Block at position");
            CrashReportCategory.addBlockInfo(category, pos, world.getBlockState(pos));
            throw new ReportedException(crashReport);

        }

        return (TutorialGeneratorTileEntity) tileAtPos;

    }

    //Adds all slots for TutorialGeneratorTileEntity's inventory, player inventory, and player hotbar to this container
    private void addSlots(final TutorialGeneratorTileEntity tileEntity, final PlayerInventory playerInventory)
    {

        //TutorialGeneratorTileEntity's Inventory slot
        //this.addSlot(new SlotItemHandler(tileEntity.inventory, 0, 80, 20));
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(new SlotItemHandler(h, 0, 80, 20));
        });

        final int playerInventoryStartX = 8;
        final int playerInventoryStartY = 51;
        final int slotSizePlus2 = 18; //Slots are 16x16, with 1 on each side for border, totalling 18x18

        //Player Inventory slots
        for(int row = 0; row < 3; row++)
        {

            for(int column = 0; column < 9; column++)
            {

                this.addSlot(new Slot(playerInventory,
                        9 + (row * 9) + column,
                        playerInventoryStartX + (column * slotSizePlus2),
                        playerInventoryStartY + (row * slotSizePlus2)
                ));

            }

        }

        //Player Hotbar slots
        final int playerHotbarY = playerInventoryStartY + slotSizePlus2 * 3 + 4;
        for(int column = 0; column < 9; column++)
        {

            this.addSlot(new Slot(playerInventory,
                    column,
                    playerInventoryStartX + (column * slotSizePlus2),
                    playerHotbarY)
            );

        }

    }

    @Override
    public void detectAndSendChanges()
    {

        super.detectAndSendChanges();

    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(final PlayerEntity player, final int index)
    {

        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = this.inventorySlots.get(index);
        if ((slot != null) && slot.getHasStack())
        {

            final ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            final int containerSlots = this.inventorySlots.size() - player.inventory.mainInventory.size();
            if (index < containerSlots)
            {

                if (!mergeItemStack(itemstack1, containerSlots, this.inventorySlots.size(), true))
                    return ItemStack.EMPTY;

            }
            else if (!mergeItemStack(itemstack1, 0, containerSlots, false))
                return ItemStack.EMPTY;

            if (itemstack1.getCount() == 0)
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, itemstack1);

        }

        return itemstack;

    }


    @Override
    public boolean canInteractWith(@Nonnull final PlayerEntity player)
    {

        return isWithinUsableDistance(canInteractWithCallable, player, ModBlocks.TUTORIAL_GENERATOR);

    }

    private static class EnergyReferenceHolder extends IntReferenceHolder
    {

        private final SettableEnergyStorage energy;

        public EnergyReferenceHolder(final SettableEnergyStorage energy)
        {

            this.energy = energy;

        }

        @Override
        public int get()
        {

            return energy.getEnergyStored();

        }

        @Override
        public void set(final int newValue)
        {

            energy.setEnergy(newValue);

        }

    }

}
