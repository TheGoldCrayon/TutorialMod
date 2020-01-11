package com.github.thegoldcrayon.tutorialmod.item.arrows;

import com.github.thegoldcrayon.tutorialmod.entity.AppleArrowEntity;
import com.github.thegoldcrayon.tutorialmod.entity.DamageArrowEntity;
import com.github.thegoldcrayon.tutorialmod.init.ModItemGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DamageArrow extends ArrowItem
{

    public static double damage = 10.0d;
    public static int knockback = 0;
    public static float velocityModifier = 0.5f;

    public DamageArrow()
    {

        super(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));

    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter)
    {

        DamageArrowEntity arrow = new DamageArrowEntity(worldIn, shooter);
        arrow.setPotionEffect(stack);
        arrow.setDamage(damage);
        arrow.setKnockbackStrength(knockback);
        return arrow;

    }

}
