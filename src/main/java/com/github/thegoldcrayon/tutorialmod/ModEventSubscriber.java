package com.github.thegoldcrayon.tutorialmod;

import com.github.thegoldcrayon.tutorialmod.init.ModItemGroups;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = TutorialMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber
{

    @SubscribeEvent
    public static void OnRegisterItems(RegistryEvent.Register<Item> event)
    {

        event.getRegistry().registerAll(

                setup(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "tutorial_item")

        );

    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name)
    {

        return setup(entry, new ResourceLocation(TutorialMod.MODID, name));

    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName)
    {

        entry.setRegistryName(registryName);
        return entry;

    }

}
