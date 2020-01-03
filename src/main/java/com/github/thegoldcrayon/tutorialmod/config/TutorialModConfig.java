package com.github.thegoldcrayon.tutorialmod.config;

import net.minecraft.item.DyeColor;

import java.util.List;

//Holds the baked (runtime) values for our config
//These should never be from outside this package
public final class TutorialModConfig
{

    // Client
    public static boolean clientBoolean;
    public static List<String> clientStringList;
    public static DyeColor clientEnumDyeColor;

    public static int clientGeneratorGeneration;

    // Server
    public static boolean serverBoolean;
    public static List<String> serverStringList;
    public static DyeColor serverEnumDyeColor;

}
