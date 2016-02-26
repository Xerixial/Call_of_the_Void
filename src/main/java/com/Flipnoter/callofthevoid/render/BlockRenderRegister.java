package com.Flipnoter.callofthevoid.render;

import com.Flipnoter.callofthevoid.blocks.ModBlocks;
import com.Flipnoter.callofthevoid.main.main;
import com.Flipnoter.callofthevoid.render.blocks.EssenceCrystallizerRenderer;
import com.Flipnoter.callofthevoid.render.blocks.ItemPedestalRenderer;
import com.Flipnoter.callofthevoid.tileentity.EssenceCrystallizerTileEntity;
import com.Flipnoter.callofthevoid.tileentity.ItemPedestalTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * Created by Connor on 2/13/2016.
 */
public class BlockRenderRegister {

    public static String modid = main.MODID;

    public static void registerBlockRenderer() {

        register(ModBlocks.VoidEssenceOre);
        register(ModBlocks.EssenceCrystallizer);
        register(ModBlocks.ItemPedestal);

        ClientRegistry.bindTileEntitySpecialRenderer(EssenceCrystallizerTileEntity.class, new EssenceCrystallizerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(ItemPedestalTileEntity.class, new ItemPedestalRenderer());

    }

    public static void register(Block block) {

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(modid + ":" + block.getUnlocalizedName().substring(5), "inventory"));

    }
}
