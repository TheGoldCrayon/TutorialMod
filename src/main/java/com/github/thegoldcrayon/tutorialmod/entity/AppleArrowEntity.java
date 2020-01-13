package com.github.thegoldcrayon.tutorialmod.entity;

import com.github.thegoldcrayon.tutorialmod.TutorialMod;
import com.github.thegoldcrayon.tutorialmod.init.ModEntities;
import com.github.thegoldcrayon.tutorialmod.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

public class AppleArrowEntity extends ArrowEntity
{

    private int timeSinceShot = 0;
    private static final Logger LOGGER = LogManager.getLogger(TutorialMod.MODID + "Apple Arrow Testing");

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
    public void tick()
    {
        if(!world.isRemote)
        {
            if (timeSinceShot < 30)
                timeSinceShot++;
            else if (timeSinceShot == 30 && !this.inGround) {
                double xVector = this.getMotion().getX();
                double yVector = this.getMotion().getY();
                double zVector = this.getMotion().getZ();

                double arrowX = this.getPosition().getX();
                double arrowY = this.getPosition().getY();
                double arrowZ = this.getPosition().getZ();

                Entity shooter = this.getShooter();
                //PlayerEntity
                //LOGGER.debug(shooter);

                double shooterX = shooter.func_226277_ct_(); //.getX()
                double shooterY = shooter.func_226278_cu_(); //.getY()
                double shooterZ = shooter.func_226281_cx_(); //.getZ()
                float pitch = shooter.getPitch(20.0f);
                float yaw = shooter.getYaw(20.0f);
                //LOGGER.debug("Pitch: " + pitch);
                //LOGGER.debug("Yaw: " + yaw);
                double shooterLookX = shooter.getLook(20.0f).getX() * 2;
                double shooterLookY = shooter.getLook(20.0f).getY() * 2;
                double shooterLookZ = shooter.getLook(20.0f).getZ() * 2;
                //LOGGER.debug("X: " + shooterLookX + ", Y: " + shooterLookY + ", Z: " + shooterLookZ);

                //this.shoot(shooterLookX,shooterLookY,shooterLookZ,1.0f, 0.0f);
                this.shoot(shooterX - arrowX, shooterY - arrowY, shooterZ - arrowZ, 1.0f, 0.0f);
                //this.shoot(xVector, yVector * (-1), zVector, 1.0f, 0.0f);
                timeSinceShot++;
            }
            super.tick();
        }
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
