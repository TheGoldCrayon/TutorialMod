package com.github.thegoldcrayon.tutorialmod.init;

import com.github.thegoldcrayon.tutorialmod.TutorialMod;
import com.github.thegoldcrayon.tutorialmod.entity.AppleArrowEntity;
import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;

@Mod.EventBusSubscriber(modid = TutorialMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TutorialMod.MODID)
public class ModEntities
{

    private static List<EntityType> entities = Lists.newArrayList();

    public static final EntityType<AppleArrowEntity> APPLE_ARROW = register("apple_arrow", EntityType.Builder.<AppleArrowEntity>create(AppleArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5f)
            .setCustomClientFactory(AppleArrowEntity::new));

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event)
    {

        for(EntityType entity : entities)
            event.getRegistry().register(entity);

    }

    public static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder)
    {

        ResourceLocation location = new ResourceLocation(TutorialMod.MODID, name);
        EntityType<T> entityType = builder.build(location.toString());
        entityType.setRegistryName(location);
        entities.add(entityType);
        return entityType;

    }

}
