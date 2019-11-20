package com.github.thegoldcrayon.tutorialmod.tileentity;

import com.github.thegoldcrayon.tutorialmod.container.TutorialGeneratorContainer;
import com.github.thegoldcrayon.tutorialmod.init.ModBlocks;
import com.github.thegoldcrayon.tutorialmod.init.ModTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TutorialGeneratorTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{

    //private static final String INVENTORY_TAG = "inventory";
    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);

    public TutorialGeneratorTileEntity()
    {

        super(ModTileEntityTypes.TUTORIAL_GENERATOR);

    }

    @Override
    public void tick()
    {



    }

    @Override
    public void read(CompoundNBT tag)
    {

        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
        super.read(tag);

    }

    @Override
    public CompoundNBT write(CompoundNBT tag)
    {

        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            tag.put("inv", compound);
        });
        return super.write(tag);

    }

    //Inventory
    public final ItemStackHandler inventory = new ItemStackHandler(1)
    {

        @Override
        public boolean isItemValid(final int slot, @Nonnull ItemStack stack)
        {

            return stack.getItem() == Items.DIAMOND;

        }

        @Override
        protected void onContentsChanged(final int slot)
        {

            super.onContentsChanged(slot);

            //Marking dirty tells vanilla that chink containing tile entity has changed and means the game will save the chunk to disk later.
            TutorialGeneratorTileEntity.this.markDirty();

        }

    };

    private IItemHandler createHandler()
    {
        return new ItemStackHandler(1)
        {

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack)
            {

                return stack.getItem() == Items.DIAMOND;

            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
            {

                if(stack.getItem() != Items.DIAMOND)
                {

                    return stack;

                }

                return super.insertItem(slot, stack, simulate);

            }

        };

    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {

        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {

            return handler.cast();

        }

        return super.getCapability(cap, side);

    }

    @Nonnull
    @Override
    public Container createMenu(final int windowID, final PlayerInventory inventory, final PlayerEntity player)
    {

        return new TutorialGeneratorContainer(windowID, this, inventory, player);

    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName()
    {

        return new TranslationTextComponent(ModBlocks.TUTORIAL_GENERATOR.getTranslationKey());

    }
}
