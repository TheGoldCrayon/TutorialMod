package com.github.thegoldcrayon.tutorialmod.item.arrows;

import com.github.thegoldcrayon.tutorialmod.entity.KnockbackArrowEntity;
import com.github.thegoldcrayon.tutorialmod.init.ModItemGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class KnockbackArrow extends ArrowItem
{

    public static double damage = 0.0d;
    public static int knockback = 10;
    public static float velocityModifier = 1.0f;

    public KnockbackArrow()
    {

        super(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));

    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter)
    {

        KnockbackArrowEntity arrow = new KnockbackArrowEntity(worldIn, shooter);
        arrow.setPotionEffect(stack);
        arrow.setDamage(damage);
        arrow.setKnockbackStrength(knockback);
        return arrow;

    }

}
