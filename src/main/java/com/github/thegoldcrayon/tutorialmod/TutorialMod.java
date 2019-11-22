package com.github.thegoldcrayon.tutorialmod;

import com.github.thegoldcrayon.tutorialmod.config.ConfigHolder;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TutorialMod.MODID)
public class TutorialMod
{

    public static final String MODID = "tutorialmod";

    public static final Logger LOGGER = LogManager.getLogger();

    public TutorialMod()
    {

        LOGGER.debug("Hello from the Tutorial Mod!");

        final ModLoadingContext modLoadingContext = ModLoadingContext.get();


        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);

    }

}
