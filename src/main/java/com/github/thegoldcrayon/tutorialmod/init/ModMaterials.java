package com.github.thegoldcrayon.tutorialmod.init;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class ModMaterials
{

    public IItemTier APPLE = new IItemTier()
    {

        @Override
        public int getMaxUses()
        {

            return 1000;

        }

        @Override
        public float getEfficiency()
        {

            return 20.0f;

        }

        @Override
        public float getAttackDamage()
        {

            return 2.0f;

        }

        @Override
        public int getHarvestLevel()
        {

            return 3;

        }

        @Override
        public int getEnchantability()
        {

            return 30;

        }

        @Override
        public Ingredient getRepairMaterial()
        {

            return Ingredient.fromItems(Items.APPLE);

        }

    };

    public IArmorMaterial APPLE_ARMOR = new IArmorMaterial() {

        private final int DAMAGE_FACTOR = 500;

        @Override
        public int getDurability(EquipmentSlotType slotIn)
        {

            int[] maxDamageArray = new int[]{4, 7, 8, 5};
            return maxDamageArray[slotIn.getIndex()] * DAMAGE_FACTOR;

        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn)
        {

            int[] damageReduction = new int[]{4, 7, 8, 5};
            return damageReduction[slotIn.getIndex()];

        }

        @Override
        public int getEnchantability()
        {

            return 30;

        }

        @Override
        public SoundEvent getSoundEvent()
        {

            return SoundEvents.ENTITY_HORSE_EAT;

        }

        @Override
        public Ingredient getRepairMaterial()
        {

            return Ingredient.fromItems(Items.APPLE);

        }

        @Override
        public String getName()
        {

            return "apple";

        }

        @Override
        public float getToughness()
        {

            return 2.5f;

        }

    };

}
