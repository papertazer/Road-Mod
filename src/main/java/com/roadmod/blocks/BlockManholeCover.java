package com.roadmod.blocks;

import java.util.Iterator;
import java.util.Vector;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockManholeCover extends Block
{

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool OPEN = PropertyBool.create("open");
    public static final PropertyEnum<BlockManholeCover.DoorHalf> HALF = PropertyEnum.<BlockManholeCover.DoorHalf>create("half", BlockManholeCover.DoorHalf.class);
    
   
    protected BlockManholeCover(Material materialIn)
    {
        super(materialIn);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, Boolean.valueOf(false)).withProperty(HALF, BlockManholeCover.DoorHalf.BOTTOM));
        float f = 0.5F;
        float f1 = 1.0F;
        this.useNeighborBrightness = true;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
    public boolean renderAsNormalBlock() {
        return false;
}
    public boolean isFullCube()
    {
        return false;
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return !((Boolean)worldIn.getBlockState(pos).getValue(OPEN)).booleanValue();
    }

    

    

    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        this.setBounds(worldIn.getBlockState(pos));
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        float f = 0.1875F;
       // this.setBlockBounds(0.0F, 0.40625F, 0.0F, 1.0F, 0.59375F, 1.0F);
    }

    public void setBounds(IBlockState state)
    {
        if (state.getBlock() == this)
        {
            boolean flag = state.getValue(HALF) == BlockManholeCover.DoorHalf.TOP;
            Boolean obool = (Boolean)state.getValue(OPEN);
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
            float f = 0.1875F;

            if (flag)
            {
               // this.setBlockBounds(0.0F, 0.62F, 0.0F, 1.0F, 0.812F, 1.0F);
            }
            else
            {
            	//this.setBlockBounds(0.0F, 0.62F, 0.0F, 1.0F, 0.812F, 1.0F);
            }

            if (obool.booleanValue())
            {
                if (enumfacing == EnumFacing.SOUTH)
                {
                   // this.setBlockBounds(0.0F, 0.0F, 0.8125F, 1.0F, 1.0F, 1.0F);
                }

                if (enumfacing == EnumFacing.NORTH)
                {
                   // this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.1875F);
                }

                if (enumfacing == EnumFacing.EAST)
                {
                  //  this.setBlockBounds(0.8125F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                }

                if (enumfacing == EnumFacing.WEST)
                {
                  //  this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.1875F, 1.0F, 1.0F);
                }
            }
        }
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
       
            state = state.cycleProperty(OPEN);
            worldIn.setBlockState(pos, state, 2);
            worldIn.playAuxSFXAtEntity(playerIn, ((Boolean)state.getValue(OPEN)).booleanValue() ? 1003 : 1006, pos, 0);
            return true;
        
    }

    

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState iblockstate = this.getDefaultState();

        if (facing.getAxis().isHorizontal())
        {
            iblockstate = iblockstate.withProperty(FACING, facing).withProperty(OPEN, Boolean.valueOf(false));
            iblockstate = iblockstate.withProperty(HALF, hitY > 0.5F ? BlockManholeCover.DoorHalf.TOP : BlockManholeCover.DoorHalf.BOTTOM);
        }

        return iblockstate;
    }
    public int getRenderType()
    {
        return 3;
    }
    protected static EnumFacing getFacing(int meta)
    {
        switch (meta & 3)
        {
            case 0:
                return EnumFacing.NORTH;
            case 1:
                return EnumFacing.SOUTH;
            case 2:
                return EnumFacing.WEST;
            case 3:
            default:
                return EnumFacing.EAST;
        }
    }

    protected static int getMetaForFacing(EnumFacing facing)
    {
        switch (facing)
        {
            case NORTH:
                return 0;
            case SOUTH:
                return 1;
            case WEST:
                return 2;
            case EAST:
            default:
                return 3;
        }
    }


    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(OPEN, Boolean.valueOf((meta & 4) != 0)).withProperty(HALF, (meta & 8) == 0 ? BlockManholeCover.DoorHalf.BOTTOM : BlockManholeCover.DoorHalf.TOP);
    }

    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | getMetaForFacing((EnumFacing)state.getValue(FACING));

        if (((Boolean)state.getValue(OPEN)).booleanValue())
        {
            i |= 4;
        }

        if (state.getValue(HALF) == BlockManholeCover.DoorHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

   	protected BlockStateContainer createBlockState() {
   		return new BlockStateContainer(this, new IProperty[] { FACING, OPEN, HALF});
    }

    public static enum DoorHalf implements IStringSerializable
    {
        TOP("top"),
        BOTTOM("bottom");

        private final String name;

        private DoorHalf(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}