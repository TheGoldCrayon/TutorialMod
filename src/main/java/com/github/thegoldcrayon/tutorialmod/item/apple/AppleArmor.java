package com.github.thegoldcrayon.tutorialmod.item.apple;

import com.github.thegoldcrayon.tutorialmod.init.ModItemGroups;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class AppleArmor extends ArmorItem
{

    private String texture;

    public AppleArmor(IArmorMaterial materialIn, EquipmentSlotType slot)
    {

        super(materialIn, slot, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP));

    }

    public Item setArmorTexture(String string)
    {

        this.texture = string;
        return this;

    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, Entity entity, EquipmentSlotType slot, String layer)
    {

        return "tutorialmod:textures/armor/" + this.texture + ".png";

    }

}
