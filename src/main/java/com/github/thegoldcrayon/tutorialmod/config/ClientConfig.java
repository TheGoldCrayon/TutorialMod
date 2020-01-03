package com.github.thegoldcrayon.tutorialmod.config;

import com.github.thegoldcrayon.tutorialmod.TutorialMod;
import net.minecraft.item.DyeColor;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

//For config settings that change behavior of code on LOGICAL CLIENT
final class ClientConfig
{

    final ForgeConfigSpec.BooleanValue clientBoolean;
    final ForgeConfigSpec.ConfigValue<List<String>> clientStringList;
    final ForgeConfigSpec.ConfigValue<DyeColor> clientEnumDyeColor;
    final ForgeConfigSpec.IntValue clientGeneratorGeneration;

    ClientConfig(final ForgeConfigSpec.Builder builder)
    {

        builder.push("general");

        clientBoolean = builder
                .comment("An example boolean in the client config")
                .translation(TutorialMod.MODID + ".config.clientBoolean")
                .define("clientBoolean", true);

        clientStringList = builder
                .comment("An example list of Strings in the client config")
                .translation(TutorialMod.MODID + ".config.clientStringList")
                .define("clientStringList", new ArrayList<>());

        clientEnumDyeColor = builder
                .comment("An example enum DyeColor in the client config")
                .translation(TutorialMod.MODID + ".config.clientEnumDyeColor")
                .defineEnum("clientEnumDyeColor", DyeColor.WHITE);

        clientGeneratorGeneration = builder
                .comment("How much energy the generator generates per diamond")
                .translation(TutorialMod.MODID + ".config.clientGeneratorGeneration")
                .defineInRange("clientGeneratorGeneration", 100, 10, 1000);

        builder.pop();

    }
}
