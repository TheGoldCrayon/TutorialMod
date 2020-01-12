package com.github.thegoldcrayon.tutorialmod.client.render.entity;

import com.github.thegoldcrayon.tutorialmod.entity.AppleArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class AppleArrowRenderer extends ArrowRenderer<AppleArrowEntity>
{
    public AppleArrowRenderer(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(AppleArrowEntity entity)
    {
        return new ResourceLocation("tutorialmod:textures/entity/apple_arrow.png");
    }
}
