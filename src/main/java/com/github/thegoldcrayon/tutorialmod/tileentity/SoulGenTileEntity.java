package com.github.thegoldcrayon.tutorialmod.tileentity;

import com.github.thegoldcrayon.tutorialmod.container.SoulGenContainer;
import com.github.thegoldcrayon.tutorialmod.container.TutorialGeneratorContainer;
import com.github.thegoldcrayon.tutorialmod.init.ModBlocks;
import com.github.thegoldcrayon.tutorialmod.init.ModItems;
import com.github.thegoldcrayon.tutorialmod.init.ModTileEntityTypes;
import com.github.thegoldcrayon.tutorialmod.souls.ISoulsStorage;
import com.github.thegoldcrayon.tutorialmod.souls.SettableSoulsStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SoulGenTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider
{

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
    private LazyOptional<ISoulsStorage> souls = LazyOptional.of(this::createSouls);

    private int counter;

    public SoulGenTileEntity()
    {

        super(ModTileEntityTypes.SOUL_GEN);

    }

    @Override
    public void tick()
    {

        if(world.isRemote)
            return;

        if(counter > 0)
        {

            counter--;
            if(counter <= 0)
                souls.ifPresent(s -> ((SettableSoulsStorage) s).addSouls(1));
            markDirty();

        }

        if(counter <= 0)
        {

            handler.ifPresent(h -> {
                ItemStack stack = h.getStackInSlot(0);
                if(stack.getItem() == ModItems.SOUL)
                {

                    h.extractItem(0, 1, false);
                    counter = 100;
                    markDirty();

                }

            });

        }

        //sendOutSouls();

    }

    @Override
    public void read(CompoundNBT tag)
    {

        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));

        CompoundNBT soulsTag = tag.getCompound("souls");
        souls.ifPresent(s -> ((INBTSerializable<CompoundNBT>) s).deserializeNBT(soulsTag));

        super.read(tag);

    }

    @Override
    public CompoundNBT write(CompoundNBT tag)
    {

        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            tag.put("inv", compound);
        });

        souls.ifPresent(s -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) s).serializeNBT();
            tag.put("souls", compound);
        });

        return super.write(tag);

    }

    private IItemHandler createHandler()
    {

        return new ItemStackHandler(1)
        {

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack)
            {

                return stack.getItem() == ModItems.SOUL;

            }

            @Override
            protected void onContentsChanged(final int slot)
            {

                super.onContentsChanged(slot);
                SoulGenTileEntity.this.markDirty();

            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
            {

                if(stack.getItem() != ModItems.SOUL)
                    return stack;

                return super.insertItem(slot, stack, simulate);

            }

        };

    }

    private ISoulsStorage createSouls()
    {

        return new SettableSoulsStorage(100, 0);

    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {

        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return handler.cast();

        return super.getCapability(cap, side);

    }

    @Nonnull
    @Override
    public Container createMenu(final int windowID, final PlayerInventory inventory, final PlayerEntity player)
    {

        return new SoulGenContainer(windowID, this, inventory, player);

    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName()
    {

        return new TranslationTextComponent(ModBlocks.SOUL_GEN.getTranslationKey());

    }

}
