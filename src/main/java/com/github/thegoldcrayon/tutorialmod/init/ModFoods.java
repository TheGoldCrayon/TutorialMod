package com.github.thegoldcrayon.tutorialmod.init;

import net.minecraft.item.Food;

public class ModFoods
{

    public static final Food EDIBLE_SOUL = (new Food.Builder()).hunger(10).saturation(10).fastToEat().setAlwaysEdible().meat().build();

}
