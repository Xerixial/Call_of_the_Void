package com.Flipnoter.callofthevoid.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Connor on 2/13/2016.
 */
public class ModTileEntities {

    public static void Init() {

        GameRegistry.registerTileEntity(EssenceCrystallizerTileEntity.class, "Essence_Crystallizer");
        GameRegistry.registerTileEntity(ItemPedestalTileEntity.class, "Item_Pedestal");
        GameRegistry.registerTileEntity(ItemAltarTileEntity.class, "Item_Altar");

    }
}
