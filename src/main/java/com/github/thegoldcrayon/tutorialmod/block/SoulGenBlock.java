package com.github.thegoldcrayon.tutorialmod.block;

import com.github.thegoldcrayon.tutorialmod.init.ModTileEntityTypes;
import com.github.thegoldcrayon.tutorialmod.tileentity.SoulGenTileEntity;
import com.github.thegoldcrayon.tutorialmod.tileentity.TutorialGeneratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class SoulGenBlock extends Block
{
    public SoulGenBlock()
    {

        super(Properties
            .create(Material.WOOD)
            .sound(SoundType.WOOD)
            .hardnessAndResistance(1.0f)
            .lightValue(2)
        );

    }

    @Override
    public boolean hasTileEntity(final BlockState state)
    {

        return true;

    }

    @Nullable
    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world)
    {

        return ModTileEntityTypes.SOUL_GEN.create();

    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack)
    {

        if(entity != null)
            world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);

    }

    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity)
    {

        return Direction.getFacingFromVector((float) (entity.serverPosX - clickedBlock.getX()), (float) (entity.serverPosY - clickedBlock.getY()), (float) (entity.serverPosZ - clickedBlock.getZ()));

    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {

        builder.add(BlockStateProperties.FACING);

    }

    //@Override
    public boolean onBlockActivated(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit)
    {

        if(!worldIn.isRemote)
        {

            final TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof SoulGenTileEntity)
            {

                NetworkHooks.openGui(((ServerPlayerEntity) player), ((SoulGenTileEntity) tileEntity), pos);

            }

        }

        return true;

    }

}
