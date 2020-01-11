package com.github.thegoldcrayon.tutorialmod.item.apple;

import com.github.thegoldcrayon.tutorialmod.TutorialMod;
import com.github.thegoldcrayon.tutorialmod.entity.AppleArrowEntity;
import com.github.thegoldcrayon.tutorialmod.init.ModEntities;
import com.github.thegoldcrayon.tutorialmod.init.ModItemGroups;
import com.github.thegoldcrayon.tutorialmod.init.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public class AppleBow extends BowItem
{

    private static final Logger LOGGER = LogManager.getLogger(TutorialMod.MODID + "Apple Bow Testing");

    public static final Predicate<ItemStack> APPLE_ARROW = (itemStack) -> {
        return itemStack.getItem() == ModItems.APPLE_ARROW;
    };

    public AppleBow(int durability)
    {

        super(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(1).maxDamage(durability));

    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate()
    {

        return APPLE_ARROW;

    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {

        return repair.getItem() == Items.APPLE || super.getIsRepairable(toRepair, repair);

    }

    @Override
    @Nonnull
    public AbstractArrowEntity customeArrow(@Nonnull AbstractArrowEntity arrowEntity)
    {

        if(arrowEntity.getType() == EntityType.ARROW)
        {

            arrowEntity.setDamage(arrowEntity.getDamage() * 2);
            arrowEntity.setKnockbackStrength(2);
            LOGGER.debug("Case 1.");
            return arrowEntity;

        }
        else if(arrowEntity.getType() == ModEntities.APPLE_ARROW)
        {

            arrowEntity.setDamage(arrowEntity.getDamage() * 20);
            arrowEntity.setKnockbackStrength(4);
            LOGGER.debug("Case 2.");
            return arrowEntity;

        }
        else
        {

            LOGGER.debug("Case 3.");
            return super.customeArrow(arrowEntity);

        }

    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = playerentity.findAmmo(stack);

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getArrowVelocity(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
                    if (!worldIn.isRemote) {
                        ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        abstractarrowentity = customeArrow(abstractarrowentity);
                        abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 0f);
                        if (f == 1.0F) {
                            abstractarrowentity.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            abstractarrowentity.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                            abstractarrowentity.setFire(100);
                        }

                        stack.damageItem(1, playerentity, (p_220009_1_) -> {
                            p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
                        });
                        if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                            abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        worldIn.addEntity(abstractarrowentity);
                    }

                    worldIn.playSound((PlayerEntity)null, playerentity.posX, playerentity.posY, playerentity.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !playerentity.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerentity.inventory.deleteStack(itemstack);
                        }
                    }

                    playerentity.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }
}
