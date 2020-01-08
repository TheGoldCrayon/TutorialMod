package com.github.thegoldcrayon.tutorialmod.item.apple;

import com.github.thegoldcrayon.tutorialmod.entity.AppleArrowEntity;
import com.github.thegoldcrayon.tutorialmod.init.ModItemGroups;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;

public class AppleBow extends BowItem
{

    public AppleBow()
    {

        super(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(1).maxDamage(3000));

    }

    @Override
    @Nonnull
    public AbstractArrowEntity customeArrow(@Nonnull AbstractArrowEntity arrowEntity)
    {

        if(arrowEntity.getType() == EntityType.ARROW)
            return new AppleArrowEntity(arrowEntity.world, arrowEntity.world.getPlayerByUuid(arrowEntity.shootingEntity));
        else
            return super.customeArrow(arrowEntity);
    }

}
