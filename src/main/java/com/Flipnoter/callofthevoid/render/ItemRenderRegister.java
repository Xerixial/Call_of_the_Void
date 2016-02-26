package com.Flipnoter.callofthevoid.render;

import com.Flipnoter.callofthevoid.items.ModItems;
import com.Flipnoter.callofthevoid.main.main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Connor on 2/5/2016.
 */
public class ItemRenderRegister {

    public static String modid = main.MODID;

    public static void registerItemRenderer() {

        register(ModItems.VoidEssenceChunk);
        register(ModItems.CompressedVoidEssence);
        register(ModItems.RefinedVoidEssence);
        register(ModItems.CrystallizedEssence);

    }

    public static void register(Item item) {

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(modid + ":" + item.getUnlocalizedName().substring(5), "inventory"));


    }
}
