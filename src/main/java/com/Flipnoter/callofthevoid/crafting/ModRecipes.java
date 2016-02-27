package com.Flipnoter.callofthevoid.crafting;

import com.Flipnoter.callofthevoid.blocks.ModBlocks;
import com.Flipnoter.callofthevoid.items.ModItems;
import net.minecraft.block.BlockStone;
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

        InfusionRecipes.instance();

    }

    static void InitCraftingTable() {

        GameRegistry.addRecipe(new ItemStack(ModBlocks.CrystalBlock), new Object[] {"CCC", "CCC", "CCC", 'C', ModItems.CrystallizedEssence});

        GameRegistry.addRecipe(new ItemStack(ModItems.CompressedVoidEssence), new Object[] {"###", "###", "###", '#', ModItems.VoidEssenceChunk});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.EssenceCrystallizer),
                new Object[] {"R R", "IRI", "BDB", 'R', ModItems.RefinedVoidEssence, 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'D', Blocks.diamond_block});

        GameRegistry.addRecipe(new ItemStack(ModBlocks.ItemPedestal, 2),
                new Object[] {"SRS", " S ", "SCS", 'S', new ItemStack(Blocks.stone, BlockStone.EnumType.STONE.getMetadata()), 'R', Items.redstone, 'C', ModItems.CrystallizedEssence});

    }

    static void InitFurnace() {

        GameRegistry.addSmelting(new ItemStack(ModItems.CompressedVoidEssence), new ItemStack(ModItems.RefinedVoidEssence), 0.3f);

    }
}
