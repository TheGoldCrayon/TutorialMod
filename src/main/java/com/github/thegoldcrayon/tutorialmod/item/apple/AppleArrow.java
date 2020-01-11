package com.github.thegoldcrayon.tutorialmod.item.apple;

import com.github.thegoldcrayon.tutorialmod.entity.AppleArrowEntity;
import com.github.thegoldcrayon.tutorialmod.init.ModItemGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AppleArrow extends ArrowItem
{

    public AppleArrow()
    {

        super(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));

    }



    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter)
    {

        AppleArrowEntity appleArrowEntity = new AppleArrowEntity(worldIn, shooter);
        appleArrowEntity.setPotionEffect(stack);
        appleArrowEntity.setDamage(100.0d);
        appleArrowEntity.setKnockbackStrength(2);
        return appleArrowEntity;

    }

}
