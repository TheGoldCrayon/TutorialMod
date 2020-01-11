package com.github.thegoldcrayon.tutorialmod.init;

import com.github.thegoldcrayon.tutorialmod.TutorialMod;
import com.github.thegoldcrayon.tutorialmod.entity.*;
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

    public static final EntityType<AppleArrowEntity> APPLE_ARROW = register("apple_arrow", EntityType.Builder
            .<AppleArrowEntity>create(AppleArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5f)
            .setCustomClientFactory(AppleArrowEntity::new));

    public static final EntityType<DamageArrowEntity> DAMAGE_ARROW = register("damage_arrow", EntityType.Builder
            .<DamageArrowEntity>create(DamageArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5f)
            .setCustomClientFactory(DamageArrowEntity::new));

    public static final EntityType<ExplosionArrowEntity> EXPLOSION_ARROW = register("explosion_arrow", EntityType.Builder
            .<ExplosionArrowEntity>create(ExplosionArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5f)
            .setCustomClientFactory(ExplosionArrowEntity::new));

    public static final EntityType<KnockbackArrowEntity> KNOCKBACK_ARROW = register("knockback_arrow", EntityType.Builder
            .<KnockbackArrowEntity>create(KnockbackArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5f)
            .setCustomClientFactory(KnockbackArrowEntity::new));

    public static final EntityType<SpeedArrowEntity> SPEED_ARROW = register("speed_arrow", EntityType.Builder
            .<SpeedArrowEntity>create(SpeedArrowEntity::new, EntityClassification.MISC)
            .size(0.5f, 0.5f)
            .setCustomClientFactory(SpeedArrowEntity::new));




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
