package com.Flipnoter.callofthevoid.main;

import com.Flipnoter.callofthevoid.blocks.ModBlocks;
import com.Flipnoter.callofthevoid.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Connor on 2/5/2016.
 */
public class ModRecipes {

    public static void Init() {

        InitCraftingTable();

        InitFurnace();

    }

    static void InitCraftingTable() {

        GameRegistry.addRecipe(new ItemStack(ModItems.CompressedVoidEssence), new Object[] {"###", "###", "###", '#', ModItems.VoidEssenceChunk});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.EssenceCrystallizer),
                new Object[] {"R R", "IRI", "BDB", 'R', ModItems.RefinedVoidEssence, 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'D', Blocks.diamond_block});

    }

    static void InitFurnace() {

        GameRegistry.addSmelting(new ItemStack(ModItems.CompressedVoidEssence), new ItemStack(ModItems.RefinedVoidEssence), 0.3f);

    }
}
