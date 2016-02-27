package com.Flipnoter.callofthevoid.blocks;

import com.Flipnoter.callofthevoid.items.ModItems;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Connor on 2/26/2016.
 */
public class CrystalBlock extends BlockBreakable {

    public CrystalBlock() {

        super(Material.glass, false);
        this.setUnlocalizedName("Crystal_Block");
        this.setCreativeTab(ModItems.VoidTab);
        this.setHardness(10f);
        this.setResistance(20.0f);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(0.4f);

    }

    @Override
    public boolean isOpaqueCube() {

        return false;

    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer() {

        return EnumWorldBlockLayer.TRANSLUCENT;

    }

    @Override
    public boolean isFullCube() {

        return false;

    }
}
