package com.Flipnoter.callofthevoid.render.blocks;

import com.Flipnoter.callofthevoid.tileentity.ItemAltarTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * Created by Connor on 2/25/2016.
 */
public class ItemAltarRenderer extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick, int destroyStage){

        renderItem((ItemAltarTileEntity)te, x, y, z);

    }

    public void renderItem(ItemAltarTileEntity tile, double x, double y, double z) {

        if(tile.getStackInSlot(0) != null) {

            EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0, 0, 0, new ItemStack(tile.getStackInSlot(0).getItem()));

            entityItem.hoverStart = 0;

            GlStateManager.pushMatrix();

            GlStateManager.translate(x, y, z);
            GL11.glTranslatef(0.5f, 0.85f, 0.5f);
            GL11.glScalef(1.25f, 1.25f, 1.25f);
            GL11.glRotatef(tile.getWorld().getWorldTime(), 0F, -1F, 0F);
            Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(entityItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);

            GlStateManager.popMatrix();

        }
    }
}
