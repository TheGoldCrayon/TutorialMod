package com.github.thegoldcrayon.tutorialmod;

import com.github.thegoldcrayon.tutorialmod.block.StatueBaseBlock;
import com.github.thegoldcrayon.tutorialmod.init.ModItemGroups;
import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventBusSubscriber(modid = TutorialMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber
{

    private static final Logger LOGGER = LogManager.getLogger(TutorialMod.MODID + " Mod Event Subscriber");

    //This will be called by Forge when it's time to register Blocks
    //This will always be called before Items
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event)
    {

        event.getRegistry().registerAll(

                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f)), "tutorial_block"),
                setup(new StatueBaseBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(20.0f, 20.0f)), "creator_statue"),
                setup(new StatueBaseBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(20.0f, 20.0f)), "creator_statue_2"),
                setup(new StatueBaseBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(20.0f, 20.0f)), "creator_statue_3")

        );

        LOGGER.debug("Registered Blocks");

    }

    //This will be called by Forge when it's time to register Items
    //This will always be called after Blocks
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event)
    {

        final IForgeRegistry<Item> registry = event.getRegistry();

        registry.registerAll(

                setup(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "tutorial_item")

        );

        //Go through entire registry as to include any potential Registry Overrides
        for(final Block block : ForgeRegistries.BLOCKS.getValues())
        {

            final ResourceLocation blockRegistryName = block.getRegistryName();

            // An extra safe-guard against badly registered blocks
            Preconditions.checkNotNull(blockRegistryName, "Registry Name of Block \"" + block + "\" of class \"" + block.getClass().getName() + "\"is null! This is not allowed!");

            // Check that the blocks is from our mod, if not, continue to the next block
            if (!blockRegistryName.getNamespace().equals(TutorialMod.MODID))
                continue;

            // If you have blocks that don't have a corresponding BlockItem, uncomment this code and create
            // an Interface - or even better an Annotation - called NoAutomaticBlockItem with no methods
            // and implement it on your blocks that shouldn't have BlockItems autmatically made for them
//			if (block instanceof NoAutomaticBlockItem) {
//				continue;
//			}

            // Make the properties, and make it so that the item will be on our ItemGroup (CreativeTab)
            final Item.Properties properties = new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP);

            // Create the new BlockItem with the block and it's properties
            final BlockItem blockItem = new BlockItem(block, properties);

            // Setup the new BlockItem with the block's registry name and register it
            registry.register(setup(blockItem, blockRegistryName));

        }

        LOGGER.debug("Registered Items");

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
