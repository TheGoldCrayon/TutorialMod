package com.github.thegoldcrayon.tutorialmod;

import net.minecraftforge.fml.common.Mod;
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

    }

}
