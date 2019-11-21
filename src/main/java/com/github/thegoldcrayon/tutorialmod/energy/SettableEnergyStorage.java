package com.github.thegoldcrayon.tutorialmod.energy;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class SettableEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT>
{

    public SettableEnergyStorage(final int capacity)
    {

        super(capacity);

    }

    public SettableEnergyStorage(final int capacity, final int maxTransfer)
    {

        super(capacity, maxTransfer);

    }

    public SettableEnergyStorage(final int capacity, final int maxReceive, final int maxExtract)
    {

        super(capacity, maxReceive, maxExtract);

    }

    public SettableEnergyStorage(final int capacity, final int maxReceive, final int maxExtract, final int energy)
    {

        super(capacity, maxReceive, maxExtract, energy);

    }

    //The amount of energy that was put into the storage
    public void setEnergy(final int energy)
    {

        this.energy = energy;

    }

    public void addEnergy(int energy)
    {

        this.energy += energy;
        if(this.energy > getMaxEnergyStored())
            this.energy = getEnergyStored();

    }

    @Override
    public CompoundNBT serializeNBT()
    {

        CompoundNBT tag = new CompoundNBT();
        tag.putInt("energy", getEnergyStored());
        return tag;

    }

    @Override
    public void deserializeNBT(CompoundNBT nbt)
    {

        setEnergy(nbt.getInt("energy"));

    }

}
