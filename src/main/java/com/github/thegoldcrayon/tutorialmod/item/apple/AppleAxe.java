package com.github.thegoldcrayon.tutorialmod.item.apple;

import com.github.thegoldcrayon.tutorialmod.init.ModItemGroups;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class AppleAxe extends AxeItem
{

    public AppleAxe(IItemTier tier, float attackDamageIn, float attackSpeedIn)
    {

        super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));

    }

}
