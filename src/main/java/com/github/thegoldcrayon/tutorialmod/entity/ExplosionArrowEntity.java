package com.github.thegoldcrayon.tutorialmod.entity;

import com.github.thegoldcrayon.tutorialmod.init.ModEntities;
import com.github.thegoldcrayon.tutorialmod.init.ModItems;
import com.github.thegoldcrayon.tutorialmod.item.arrows.ExplosionArrow;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class ExplosionArrowEntity extends ArrowEntity
{
    private BlockState inBlockState;

    public ExplosionArrowEntity(FMLPlayMessages.SpawnEntity spawnPacket, World world)
    {

        super(world, 0, 0, 0);

    }

    public ExplosionArrowEntity(EntityType<? extends ArrowEntity> arrow, World world)
    {

        super(arrow, world);

    }

    public ExplosionArrowEntity(World world, LivingEntity livingEntity)
    {

        super(world, livingEntity);

    }

    @Override
    protected void arrowHit(LivingEntity living)
    {

        super.arrowHit(living);
        this.world.createExplosion(new ExplosionArrowEntity(world, living), living.serverPosX, living.serverPosY, living.serverPosZ, ExplosionArrow.explosionRadius, true, Explosion.Mode.NONE);

    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
        RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY)
        {
            this.world.createExplosion(this, this.serverPosX, this.serverPosY, this.serverPosZ, ExplosionArrow.explosionRadius, true, Explosion.Mode.NONE);
        }
        else if (raytraceresult$type == RayTraceResult.Type.BLOCK)
        {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceResultIn;
            BlockState blockstate = this.world.getBlockState(blockraytraceresult.getPos());
            this.inBlockState = blockstate;
            Vec3d vec3d = blockraytraceresult.getHitVec().subtract(this.serverPosX, this.serverPosY, this.serverPosZ);
            this.setMotion(vec3d);
            Vec3d vec3d1 = vec3d.normalize().scale((double)0.05F);
            this.serverPosX -= vec3d1.x;
            this.serverPosY -= vec3d1.y;
            this.serverPosZ -= vec3d1.z;
            this.playSound(this.getHitGroundSound(), 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
            this.inGround = false;
            blockstate.onProjectileCollision(this.world, blockstate, blockraytraceresult, this);
            this.world.createExplosion(this, this.serverPosX, this.serverPosY, this.serverPosZ, ExplosionArrow.explosionRadius, true, Explosion.Mode.NONE);
        }

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

        return ModEntities.EXPLOSION_ARROW;

    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket()
    {

        return NetworkHooks.getEntitySpawningPacket(this);

    }

}
