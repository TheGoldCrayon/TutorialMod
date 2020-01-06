package com.github.thegoldcrayon.tutorialmod.init;

import com.github.thegoldcrayon.tutorialmod.TutorialMod;
import com.github.thegoldcrayon.tutorialmod.container.SoulGenContainer;
import com.github.thegoldcrayon.tutorialmod.container.TutorialGeneratorContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TutorialMod.MODID)
public class ModContainerTypes
{

    public static final ContainerType<TutorialGeneratorContainer> TUTORIAL_GENERATOR = null;
    public static final ContainerType<SoulGenContainer> SOUL_GEN = null;

}
