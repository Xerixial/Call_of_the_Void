package com.Flipnoter.callofthevoid.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Connor on 2/4/2016.
 */
public final class ModBlocks {

    public static Block VoidEssenceOre;
    public static Block CrystalBlock;

    public static Block EssenceCrystallizer;
    public static Block ItemPedestal;
    public static Block ItemAltar;

    public static void Init() {

        Register(VoidEssenceOre = new VoidEssenceOre());
        Register(CrystalBlock = new CrystalBlock());

        Register(EssenceCrystallizer = new EssenceCrystallizer());
        Register(ItemPedestal = new ItemPedestal());
        Register(ItemAltar = new ItemAltar());

    }

    static void Register(Block block) {

        GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));

    }
}
