package com.github.thegoldcrayon.tutorialmod.souls;

public class SoulsStorage implements ISoulsStorage
{

    protected int souls;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public SoulsStorage(int capacity)
    {

        this(capacity, capacity, capacity, 0);

    }

    public SoulsStorage(int capacity, int maxInOut)
    {

        this(capacity, maxInOut, maxInOut, 0);

    }

    public SoulsStorage(int capacity, int maxReceive, int maxExtract)
    {

        this(capacity, maxReceive, maxExtract, 0);

    }

    public SoulsStorage(int capacity, int maxReceive, int maxExtract, int souls)
    {

        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.souls = Math.max(0, Math.min(capacity, souls));

    }

    @Override
    public int receiveSouls(int maxReceive, boolean simulate)
    {

        if(!canReceive())
            return 0;

        int soulsReceived = Math.min(capacity - souls, Math.min(this.maxReceive, maxReceive));
        if(!simulate)
            souls += soulsReceived;
        return soulsReceived;

    }

    @Override
    public int extractSouls(int maxExtract, boolean simulate)
    {

        if(!canExtract())
            return 0;

        int soulsExtracted = Math.min(souls, Math.min(this.maxExtract, maxExtract));
        if(!simulate)
            souls -= soulsExtracted;
        return soulsExtracted;

    }

    @Override
    public int getSoulsStored()
    {

        return souls;

    }

    @Override
    public int getMaxSoulsStored()
    {

        return capacity;

    }

    @Override
    public boolean canReceive()
    {

        return this.maxReceive > 0;

    }

    @Override
    public boolean canExtract()
    {

        return this.maxExtract > 0;

    }

}
