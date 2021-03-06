package com.Flipnoter.callofthevoid.main;

import com.Flipnoter.callofthevoid.render.BlockRenderRegister;
import com.Flipnoter.callofthevoid.render.ItemRenderRegister;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Connor on 2/4/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        super.preInit(event);

    }

    @Override
    public void init(FMLInitializationEvent event) {

        super.init(event);

        ItemRenderRegister.registerItemRenderer();
        BlockRenderRegister.registerBlockRenderer();

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

        super.postInit(event);

    }
}
