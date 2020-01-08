package com.github.thegoldcrayon.tutorialmod.item.apple;

import com.github.thegoldcrayon.tutorialmod.init.ModItemGroups;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class AppleHoe extends HoeItem
{

    public AppleHoe(IItemTier tier, float attackSpeedIn)
    {

        super(tier, attackSpeedIn, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));

    }

}
