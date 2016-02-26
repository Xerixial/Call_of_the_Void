package com.Flipnoter.callofthevoid.main;

import com.Flipnoter.callofthevoid.blocks.ModBlocks;
import com.Flipnoter.callofthevoid.gui.ModGUIHandler;
import com.Flipnoter.callofthevoid.items.ModItems;
import com.Flipnoter.callofthevoid.tileentity.ModTileEntities;
import com.Flipnoter.callofthevoid.world.OreGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Connor on 2/4/2016.
 */
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

        ModItems.Init();

        ModBlocks.Init();

        ModTileEntities.Init();

    }

    public void init(FMLInitializationEvent event) {

        ModRecipes.Init();

        GameRegistry.registerWorldGenerator(new OreGenerator(), 0);

        NetworkRegistry.INSTANCE.registerGuiHandler(main.instance, new ModGUIHandler());

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
