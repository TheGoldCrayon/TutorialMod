package com.github.thegoldcrayon.tutorialmod.souls;

public class SoulsStorage
{

    protected int souls;
    protected int capacity;
    protected int maxIn;
    protected int maxOut;

    public SoulsStorage(int capacity)
    {

        this(capacity, capacity, capacity, 0);

    }

    public SoulsStorage(int capacity, int maxInOut)
    {

        this(capacity, maxInOut, maxInOut, 0);

    }

    public SoulsStorage(int capacity, int maxIn, int maxOut)
    {

        this(capacity, maxIn, maxOut, 0);

    }

    public SoulsStorage(int capacity, int maxIn, int maxOut, int souls)
    {

        this.capacity = capacity;
        this.maxIn = maxIn;
        this.maxOut = maxOut;
        this.souls = Math.max(0, Math.min(capacity, souls));

    }

    public int getSoulsStored()
    {

        return souls;

    }

    public int getMaxSoulsStored()
    {

        return capacity;

    }

    public boolean canInput()
    {

        return this.maxIn > 0;

    }

    public boolean canOutput()
    {

        return this.maxOut > 0;

    }

}
