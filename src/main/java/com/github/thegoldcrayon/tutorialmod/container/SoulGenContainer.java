package com.github.thegoldcrayon.tutorialmod.container;

import com.github.thegoldcrayon.tutorialmod.init.ModBlocks;
import com.github.thegoldcrayon.tutorialmod.init.ModContainerTypes;
import com.github.thegoldcrayon.tutorialmod.tileentity.SoulGenTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;

import javax.annotation.Nonnull;

public class SoulGenContainer extends Container
{

    public final SoulGenTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public SoulGenContainer(final int windowID, final SoulGenTileEntity tileEntity, final PlayerInventory playerInventory, final PlayerEntity player)
    {

        super(ModContainerTypes.SOUL_GEN, windowID);

        canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
        this.tileEntity = tileEntity;

        //addSlots(tileEntity, playerInventory);

    }

    @Override
    public boolean canInteractWith(@Nonnull final PlayerEntity player)
    {

        return isWithinUsableDistance(canInteractWithCallable, player, ModBlocks.SOUL_GEN);

    }

}
