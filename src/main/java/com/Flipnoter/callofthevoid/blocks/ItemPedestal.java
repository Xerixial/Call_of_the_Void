package com.Flipnoter.callofthevoid.blocks;

import com.Flipnoter.callofthevoid.items.ModItems;
import com.Flipnoter.callofthevoid.tileentity.ItemPedestalTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Connor on 2/25/2016.
 */
public class ItemPedestal extends BlockContainer implements ITileEntityProvider {

    public ItemPedestal() {

        super(Material.iron);
        this.setUnlocalizedName("Item_Pedestal");
        this.setCreativeTab(ModItems.VoidTab);
        this.setHardness(6f);
        this.setResistance(10f);
        this.setHarvestLevel("pickaxe", 3);
        this.setBlockBounds(3f / 16f, 0, 3f / 16f, 1f - 3f / 16f, 1, 1f - 3f / 16f);
        this.isBlockContainer = true;

    }

    @Override
    public boolean isOpaqueCube() {

        return false;

    }

    @Override
    public int getRenderType() {

        return 3;

    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {

        ItemPedestalTileEntity tile = (ItemPedestalTileEntity)world.getTileEntity(pos);

        if(tile.getStackInSlot(0) == null && player.getHeldItem() != null) {

            ItemStack input = player.getHeldItem().copy();
            input.stackSize = 1;
            player.getHeldItem().stackSize--;
            tile.setInventorySlotContents(0, input);

        }
        else if (tile.getStackInSlot(0) != null && player.getHeldItem() == null) {

            if(!tile.getWorld().isRemote) {

                EntityItem invItem = new EntityItem(tile.getWorld(), player.posX, player.posY + 0.25, player.posZ, tile.getStackInSlot(0));
                tile.getWorld().spawnEntityInWorld(invItem);

            }

            tile.clear();

        }

        world.markBlockForUpdate(pos);

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {

        return new ItemPedestalTileEntity();

    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {

        ItemPedestalTileEntity te = (ItemPedestalTileEntity)world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world, pos, te);

        super.breakBlock(world, pos, state);

    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        if(stack.hasDisplayName()) {

            ((ItemPedestalTileEntity)worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());

        }
    }

    @Override
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {

        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);

        TileEntity tileentity = worldIn.getTileEntity(pos);

        return tileentity != null && tileentity.receiveClientEvent(eventID, eventParam);

    }
}
