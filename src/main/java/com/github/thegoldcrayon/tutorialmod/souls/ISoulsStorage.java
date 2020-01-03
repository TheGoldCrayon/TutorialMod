package com.github.thegoldcrayon.tutorialmod.souls;

public interface ISoulsStorage
{

    int receiveSouls(int maxReceive, boolean simulate);
    int extractSouls(int maxExtract, boolean simulate);
    int getSoulsStored();
    int getMaxSoulsStored();
    boolean canExtract();
    boolean canReceive();

}
