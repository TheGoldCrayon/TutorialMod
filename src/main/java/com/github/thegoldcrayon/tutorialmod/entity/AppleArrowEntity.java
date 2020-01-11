package com.github.thegoldcrayon.tutorialmod.entity;

import com.github.thegoldcrayon.tutorialmod.init.ModEntities;
import com.github.thegoldcrayon.tutorialmod.init.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class AppleArrowEntity extends ArrowEntity
{

    public AppleArrowEntity(FMLPlayMessages.SpawnEntity spawnPacket, World world)
    {

        super(world, 0, 0, 0);

    }

    public AppleArrowEntity(EntityType<? extends ArrowEntity> arrow, World world)
    {

        super(arrow, world);

    }

    public AppleArrowEntity(World world, LivingEntity livingEntity)
    {

        super(world, livingEntity);

    }

    @Override
    protected ItemStack getArrowStack()
    {

        return new ItemStack(ModItems.APPLE_ARROW);

    }

    @Override
    protected float getWaterDrag()
    {

        return 1.0f;

    }

    @Override
    @Nonnull
    public EntityType<?> getType()
    {

        return ModEntities.APPLE_ARROW;

    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket()
    {

        return NetworkHooks.getEntitySpawningPacket(this);

    }

}
