package com.github.thegoldcrayon.tutorialmod;

import com.github.thegoldcrayon.tutorialmod.block.StatueBaseBlock;
import com.github.thegoldcrayon.tutorialmod.block.TutorialGeneratorBlock;
import com.github.thegoldcrayon.tutorialmod.config.ConfigHelper;
import com.github.thegoldcrayon.tutorialmod.config.ConfigHolder;
import com.github.thegoldcrayon.tutorialmod.container.TutorialGeneratorContainer;
import com.github.thegoldcrayon.tutorialmod.init.*;
import com.github.thegoldcrayon.tutorialmod.item.apple.*;
import com.github.thegoldcrayon.tutorialmod.tileentity.TutorialGeneratorTileEntity;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;

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
                setup(new StatueBaseBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(20.0f, 20.0f)), "creator_statue_3"),
                setup(new TutorialGeneratorBlock(), "tutorial_generator")
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
                setup(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "tutorial_item"),
                        setup(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)), "soul"),
                        setup(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).food(ModFoods.EDIBLE_SOUL)), "edible_soul"),
                        setup(new ApplePickaxe(new ModMaterials().APPLE, 2, 2.0f), "apple_pickaxe"),
                        setup(new AppleAxe(new ModMaterials().APPLE, 2, 2.0f), "apple_axe"),
                        setup(new AppleHoe(new ModMaterials().APPLE, 1.0f), "apple_hoe"),
                        setup(new AppleShovel(new ModMaterials().APPLE, 2, 2.0f), "apple_shovel"),
                        setup(new AppleSword(new ModMaterials().APPLE, 10, 3.0f), "apple_sword"),
                        setup(new AppleArmor(new ModMaterials().APPLE_ARMOR, EquipmentSlotType.HEAD).setArmorTexture("apple_layer_1"), "apple_helmet"),
                        setup(new AppleArmor(new ModMaterials().APPLE_ARMOR, EquipmentSlotType.CHEST).setArmorTexture("apple_layer_1"), "apple_chestplate"),
                        setup(new AppleArmor(new ModMaterials().APPLE_ARMOR, EquipmentSlotType.LEGS).setArmorTexture("apple_layer_2"), "apple_leggings"),
                        setup(new AppleArmor(new ModMaterials().APPLE_ARMOR, EquipmentSlotType.FEET).setArmorTexture("apple_layer_1"), "apple_boots"),
                        setup(new AppleBow(2500), "apple_bow"),
                        setup(new AppleArrow(), "apple_arrow")

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
            // and implement it on your blocks that shouldn't have BlockItems automatically made for them
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

    //This will be called by Forge when it's time to register TileEntityTypes.
    //This will always be called after Blocks and Items.
    @SubscribeEvent
    public static void onRegisterTileEntityTypes(@Nonnull final RegistryEvent.Register<TileEntityType<?>> event)
    {

        event.getRegistry().registerAll(
            setup(TileEntityType.Builder.create(TutorialGeneratorTileEntity::new, ModBlocks.TUTORIAL_GENERATOR).build(null), "tutorial_generator")
        );

        LOGGER.debug("Registered TileEntityTypes");

    }

    //This will be called by Forge when it's time to register ContainerTypes.
    //This will always be called after Blocks and Items.
    @SubscribeEvent
    public static void onRegisterContainerTypes(@Nonnull final RegistryEvent.Register<ContainerType<?>> event)
    {

        event.getRegistry().registerAll(
            setup(IForgeContainerType.create(TutorialGeneratorContainer::new), "tutorial_generator")
        );

        LOGGER.debug("Registered ContainerTypes");

    }

    //This will be called by forge when a config changes.
    @SubscribeEvent
    public static void onModConfigEvent(final ModConfig.ModConfigEvent event)
    {

        final ModConfig config = event.getConfig();

        //Rebake configs when they change
        if(config.getSpec() == ConfigHolder.CLIENT_SPEC)
        {

            ConfigHelper.bakeClient(config);
            LOGGER.debug("Baked Client config");

        }

        else if(config.getSpec() == ConfigHolder.SERVER_SPEC)
        {

            ConfigHelper.bakeServer(config);
            LOGGER.debug("Baked Server config");

        }

        LOGGER.debug("Baked configs");

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
