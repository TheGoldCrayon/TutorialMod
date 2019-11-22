package com.github.thegoldcrayon.tutorialmod.config;

import net.minecraftforge.fml.config.ModConfig;

//This bakes config values to normal fields
public final class ConfigHelper
{

    //Store reference to ModConfigs to be able to change values in them from code
    //Such as a config GUI
    private static ModConfig clientConfig;
    private static ModConfig serverConfig;

    public static void bakeClient(final ModConfig config)
    {

        clientConfig = config;

        TutorialModConfig.clientBoolean = ConfigHolder.CLIENT.clientBoolean.get();
        TutorialModConfig.clientStringList = ConfigHolder.CLIENT.clientStringList.get();
        TutorialModConfig.clientEnumDyeColor = ConfigHolder.CLIENT.clientEnumDyeColor.get();

    }

    public static void bakeServer(final ModConfig config)
    {

        serverConfig = config;

        TutorialModConfig.serverBoolean = ConfigHolder.SERVER.serverBoolean.get();
        TutorialModConfig.serverStringList = ConfigHolder.SERVER.serverStringList.get();
        TutorialModConfig.serverEnumDyeColor = ConfigHolder.SERVER.serverEnumDyeColor.get();

        TutorialModConfig.serverGeneratorGeneration = ConfigHolder.SERVER.serverGeneratorGeneration.get();

    }

    //Helper method to set value on a config then save the config
    public static void setValueAndSave(final ModConfig modConfig, final String path, final Object newValue)
    {

        modConfig.getConfigData().set(path, newValue);
        modConfig.save();

    }

}
