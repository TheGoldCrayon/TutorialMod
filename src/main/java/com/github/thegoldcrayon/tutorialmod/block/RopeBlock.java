package com.github.thegoldcrayon.tutorialmod.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class RopeBlock extends LadderBlock
{
    protected static final VoxelShape ROPE = Block.makeCuboidShape(6.0d, 0.0d, 6.0d, 10.0d, 16.0d, 10.0d);

    public RopeBlock()
    {
        super(Block.Properties
                .create(Material.WOOL)
                .sound(SoundType.CLOTH)
                .hardnessAndResistance(0.5f)
                .func_226896_b_()
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return ROPE;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        int xPos = pos.getX();
        int yPos = pos.getY() + 1;
        int zPos = pos.getZ();
        BlockPos blockAbove = new BlockPos(xPos, yPos, zPos);
        Block block = worldIn.getBlockState(blockAbove).getBlock();

        if(block == Blocks.AIR)
            return false;
        else
            return true;
    }
}
