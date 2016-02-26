package com.Flipnoter.callofthevoid.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Connor on 2/4/2016.
 */
public final class ModBlocks {

    public static Block VoidEssenceOre;

    public static Block EssenceCrystallizer;

    public static void Init() {

        Register(VoidEssenceOre = new VoidEssenceOre());
        Register(EssenceCrystallizer = new EssenceCrystallizer());

    }

    static void Register(Block block) {

        GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));

    }
}
