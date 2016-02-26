package com.Flipnoter.callofthevoid.items;

import com.Flipnoter.callofthevoid.blocks.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Connor on 2/5/2016.
 */
public class ModItems {

    public static Item VoidEssenceChunk;
    public static Item CompressedVoidEssence;
    public static Item RefinedVoidEssence;
    public static Item CrystallizedEssence;

    public static final CreativeTabs VoidTab = new CreativeTabs("Call_of_the_Void") {

        @Override
        public Item getTabIconItem() {

            return Item.getItemFromBlock(ModBlocks.VoidEssenceOre);

        }
    };

    public static void Init() {

        Register(VoidEssenceChunk = new VoidEssenceChunk());
        Register(CompressedVoidEssence = new CompressedVoidEssence());
        Register(RefinedVoidEssence = new RefinedVoidEssence());
        Register(CrystallizedEssence = new CrystallizedEssence());

    }

    static void Register(Item item) {

        GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));

    }
}
