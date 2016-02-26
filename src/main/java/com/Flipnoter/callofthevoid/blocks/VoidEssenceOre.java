package com.Flipnoter.callofthevoid.blocks;

import com.Flipnoter.callofthevoid.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Connor on 2/4/2016.
 */
public class VoidEssenceOre extends Block {

    public VoidEssenceOre() {

        super(Material.rock);
        this.setUnlocalizedName("Void_Essence_Ore");
        this.setCreativeTab(ModItems.VoidTab);
        this.setHardness(10f);
        this.setResistance(20.0f);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(0.4f);

    }

    public int quantityDropped(Random random) {

        return 1 + random.nextInt(2);

    }

    public int quantityDroppedWithBonus(int fortune, Random random) {

        return fortune == 0 ? this.quantityDropped(random) : this.quantityDropped(random) + fortune + random.nextInt(fortune * 2);

    }

    public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {

        return ModItems.VoidEssenceChunk;

    }

    public int quantityDropped(IBlockState blockstate, int fortune, Random random) {

        return 2 + random.nextInt(1 + fortune + 1);

    }
}
