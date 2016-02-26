package com.Flipnoter.callofthevoid.gui;

import com.Flipnoter.callofthevoid.guicontainer.ContainerEssenceCrystallizer;
import com.Flipnoter.callofthevoid.tileentity.EssenceCrystallizerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Connor on 2/13/2016.
 */
public class ModGUIHandler implements IGuiHandler {

    public static final int Essence_Crystallizer_GUI = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if(ID == Essence_Crystallizer_GUI)
            return new ContainerEssenceCrystallizer(player.inventory, (EssenceCrystallizerTileEntity)world.getTileEntity(new BlockPos(x, y, z)));

        return null;

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if(ID == Essence_Crystallizer_GUI)
            return new GUIEssenceCrystallizer(player.inventory, (EssenceCrystallizerTileEntity)world.getTileEntity(new BlockPos(x, y, z)));

        return null;

    }
}
