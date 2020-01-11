package com.github.thegoldcrayon.tutorialmod.item.apple;

import com.github.thegoldcrayon.tutorialmod.init.ModItemGroups;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class AppleSword extends SwordItem
{

    public AppleSword(IItemTier tier, int attackDamageIn, float attackSpeedIn)
    {

        super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));

    }

}