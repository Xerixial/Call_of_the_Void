package com.Flipnoter.callofthevoid.blocks;

import com.Flipnoter.callofthevoid.gui.ModGUIHandler;
import com.Flipnoter.callofthevoid.items.ModItems;
import com.Flipnoter.callofthevoid.main.main;
import com.Flipnoter.callofthevoid.tileentity.EssenceCrystallizerTileEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Created by Connor on 2/13/2016.
 */
public class EssenceCrystallizer extends BlockContainer implements ITileEntityProvider {

    protected EssenceCrystallizer() {

        super(Material.iron);
        this.setUnlocalizedName("Essence_Crystallizer");
        this.setCreativeTab(ModItems.VoidTab);
        this.setHardness(6f);
        this.setResistance(10f);
        this.setHarvestLevel("pickaxe", 3);
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

        if(!world.isRemote) {

            FMLNetworkHandler.openGui(player, main.instance, ModGUIHandler.Essence_Crystallizer_GUI, world, pos.getX(), pos.getY(), pos.getZ());

            //player.openGui(main.instance, ModGUIHandler.Essence_Crystallizer_GUI, world, pos.getX(), pos.getY(), pos.getZ());

        }

        world.markBlockForUpdate(pos);

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {

        return new EssenceCrystallizerTileEntity();

    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {

        EssenceCrystallizerTileEntity te = (EssenceCrystallizerTileEntity)world.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(world, pos, te);

        super.breakBlock(world, pos, state);

    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        if(stack.hasDisplayName()) {

            ((EssenceCrystallizerTileEntity)worldIn.getTileEntity(pos)).setCustomName(stack.getDisplayName());

        }
    }

    @Override
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {

        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);

        TileEntity tileentity = worldIn.getTileEntity(pos);

        return tileentity != null && tileentity.receiveClientEvent(eventID, eventParam);

    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {

        if(((EssenceCrystallizerTileEntity)world.getTileEntity(pos)).getCurEssence() > 0) {

            world.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH, pos.getX() + 0.15d, pos.getY() + 1.1d, pos.getZ() + 0.15d, 0, 0, 0);
            world.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH, pos.getX() + 1d, pos.getY() + 1.1d, pos.getZ() + 1d, 0, 0, 0);
            world.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH, pos.getX() + 1d, pos.getY() + 1.1d, pos.getZ() + 0.15d, 0, 0, 0);
            world.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH, pos.getX() + 0.15d, pos.getY() + 1.1d, pos.getZ() + 1d, 0, 0, 0);

        }
    }
}
