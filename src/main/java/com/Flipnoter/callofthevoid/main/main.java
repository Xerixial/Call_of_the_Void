package com.Flipnoter.callofthevoid.main;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Connor on 2/4/2016.
 */
@Mod(modid = main.MODID, version = "1.0", name = main.Name, useMetadata = true)
public class main {

    public static final String MODID = "call_of_the_void";
    public static final String Name = "Call of the Void";

    @Mod.Instance
    public static main instance = new main();

    @SidedProxy(clientSide="com.Flipnoter.callofthevoid.main.ClientProxy", serverSide="com.Flipnoter.callofthevoid.main.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {

        proxy.preInit(event);

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.init(event);

    }

    @EventHandler
    public void postinit(FMLPostInitializationEvent event) {

        proxy.postInit(event);

    }
}
