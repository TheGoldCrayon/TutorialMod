package com.github.thegoldcrayon.tutorialmod.block;

import com.github.thegoldcrayon.tutorialmod.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.state.BooleanProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class StatueFurnaceBlock extends HorizontalBlock
{

    public static final BooleanProperty BURNING = BooleanProperty.create("burning");

    public StatueFurnaceBlock(final Properties properties)
    {

        super(properties);
        this.setDefaultState(this.getDefaultState()
            .with(HORIZONTAL_FACING, Direction.WEST)
            .with(BURNING, false)
        );

    }
/*
    @Override
    public boolean hasTileEntity(final BlockState state)
    {

        return true;

    }

    @Nullable
    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world)
    {

        return ModTileEntityTypes.STATUE_FURNACE.create();

    }

    @Override
    public void onReplaced(BlockState oldState, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {

        if(oldState.getBlock() != newState.getBlock())
        {

           TileEntity tileEntity = worldIn.getTileEntity(pos);
           if(tileEntity instanceof StatueFurnaceTileEntity)
           {

               final ItemStackHandler inventory - ((StatueFurnaceTileEntity) tileEntity).inventory;
               for(int slot = 0; slot < inventory.getSlots(); slot++)
                   InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));

           }

        }

        super.onReplaced(oldState, worldIn, pos, newState, isMoving);

    }
*/
}
