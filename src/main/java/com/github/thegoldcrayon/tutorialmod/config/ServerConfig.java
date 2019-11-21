package com.github.thegoldcrayon.tutorialmod.config;

import com.github.thegoldcrayon.tutorialmod.TutorialMod;
import net.minecraft.item.DyeColor;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

//For config settings that change behavior of code on LOGICAL SERVER
final class ServerConfig
{

    final ForgeConfigSpec.BooleanValue serverBoolean;
    final ForgeConfigSpec.ConfigValue<List<String>> serverStringList;
    final ForgeConfigSpec.ConfigValue<DyeColor> serverEnumDyeColor;
    final ForgeConfigSpec.IntValue serverGeneratorGeneration;

    ServerConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("general");

        serverBoolean = builder
                .comment("An example boolean in the server config")
                .translation(TutorialMod.MODID + ".config.serverBoolean")
                .define("serverBoolean", true);

        serverStringList = builder
                .comment("An example list of Strings in the server config")
                .translation(TutorialMod.MODID + ".config.serverStringList")
                .define("serverStringList", new ArrayList<>());

        serverEnumDyeColor = builder
                .comment("An example enum DyeColor in the server config")
                .translation(TutorialMod.MODID + ".config.serverEnumDyeColor")
                .defineEnum("serverEnumDyeColor", DyeColor.WHITE);

        serverGeneratorGeneration = builder
                .comment("How much energy the generator generates per diamond")
                .translation(TutorialMod.MODID + ".config.serverGeneratorGeneration")
                .defineInRange("serverGeneratorGeneration", 100, 10, 1000);

        builder.pop();
    }

}
