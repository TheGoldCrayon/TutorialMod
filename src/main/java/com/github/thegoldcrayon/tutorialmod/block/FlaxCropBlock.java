package com.github.thegoldcrayon.tutorialmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class FlaxCropBlock extends CropsBlock
{
    protected FlaxCropBlock()
    {
        super(Block.Properties
                .create(Material.PLANTS)
                .doesNotBlockMovement()
                .tickRandomly()
                .sound(SoundType.CROP)
        );
    }
}
