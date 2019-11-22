package com.github.thegoldcrayon.tutorialmod.souls;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class SettableSoulsStorage extends SoulsStorage implements INBTSerializable<CompoundNBT>
{

    public SettableSoulsStorage(int capacity)
    {

        super(capacity);

    }

    public SettableSoulsStorage(int capacity, int maxInOut)
    {

        super(capacity, maxInOut);

    }

    public SettableSoulsStorage(int capacity, int maxIn, int maxOut)
    {

        super(capacity, maxIn, maxOut);

    }

    public SettableSoulsStorage(int capacity, int maxIn, int maxOut, int souls)
    {

        super(capacity, maxIn, maxOut, souls);

    }

    public void setSouls(final int souls)
    {

        this.souls = souls;

    }

    public void addSouls(int souls)
    {

        this.souls += souls;
        if(this.souls > getMaxSoulsStored())
            this.souls = getSoulsStored();

    }

    public void consumeSouls(int souls)
    {

        this.souls -= souls;
        if(this.souls < 0)
            this.souls = 0;

    }

    @Override
    public CompoundNBT serializeNBT()
    {

        CompoundNBT tag = new CompoundNBT();
        tag.putInt("endMana", 5);
        return tag;

    }

    @Override
    public void deserializeNBT(CompoundNBT nbt)
    {

        setSouls(nbt.getInt("endMana"));

    }

}


