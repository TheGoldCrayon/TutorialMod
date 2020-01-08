package com.github.thegoldcrayon.tutorialmod.client;

import com.github.thegoldcrayon.tutorialmod.TutorialMod;
import com.github.thegoldcrayon.tutorialmod.client.gui.TutorialGeneratorScreen;
import com.github.thegoldcrayon.tutorialmod.entity.AppleArrowEntity;
import com.github.thegoldcrayon.tutorialmod.init.ModContainerTypes;
import com.github.thegoldcrayon.tutorialmod.init.ModEntities;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = TutorialMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber
{

    private static final Logger LOGGER = LogManager.getLogger(TutorialMod.MODID + "Client Mod Event Subscriber");

    @SubscribeEvent
    public static void onFMLClientSetupEvent(final FMLClientSetupEvent event)
    {

        //Register TileEntity Renderers
        LOGGER.debug("Registered TileEntity Renderers");

        //Register Entity Renderers
        RenderingRegistry.registerEntityRenderingHandler(AppleArrowEntity.class, TippedArrowRenderer::new);
        LOGGER.debug("Registered Entity Renderers");

        //Register ContainerType Screens
        ScreenManager.registerFactory(ModContainerTypes.TUTORIAL_GENERATOR, TutorialGeneratorScreen::new);
        LOGGER.debug("Registered ContainerType Screens");

    }

}
