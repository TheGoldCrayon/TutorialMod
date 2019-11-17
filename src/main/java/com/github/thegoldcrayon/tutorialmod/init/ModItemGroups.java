package com.github.thegoldcrayon.tutorialmod.init;

import com.github.thegoldcrayon.tutorialmod.TutorialMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class ModItemGroups
{

    public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(TutorialMod.MODID, () -> new ItemStack(ModItems.TUTORIAL_ITEM));

    public static final class ModItemGroup extends ItemGroup
    {

        private final Supplier<ItemStack> iconSupplier;

        public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier)
        {

            super(name);
            this.iconSupplier = iconSupplier;

        }

        @Override
        public ItemStack createIcon()
        {

            return iconSupplier.get();

        }

    }

}
