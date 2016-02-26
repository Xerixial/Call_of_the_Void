package com.Flipnoter.callofthevoid.gui;

import com.Flipnoter.callofthevoid.guicontainer.ContainerEssenceCrystallizer;
import com.Flipnoter.callofthevoid.main.main;
import com.Flipnoter.callofthevoid.tileentity.EssenceCrystallizerTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

/**
 * Created by Connor on 2/13/2016.
 */
public class GUIEssenceCrystallizer extends GuiContainer {

    private IInventory playerInv;
    private EssenceCrystallizerTileEntity te;

    public GUIEssenceCrystallizer(IInventory playerInv, EssenceCrystallizerTileEntity te) {

        super(new ContainerEssenceCrystallizer(playerInv, te));

        this.playerInv = playerInv;
        this.te = te;

        xSize = 176;
        ySize = 166;

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(new ResourceLocation(main.MODID + ":textures/gui/container/Essence_Crystallizer.png"));
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        float essence = (float)te.getCurEssence() / (float)te.getMaxEssence();
        drawTexturedModalRect(guiLeft + 8, guiTop + 8 + (42 - (int)(essence * 42)), 177, 42 - (int)(essence * 42), 16, (int)(essence * 42));

        float time = (float)te.getCurProcessingTime() / (float)te.getProcessingTime();
        drawTexturedModalRect(guiLeft + 64, guiTop + 23, 177, 44, (int)(time * 48), 40);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        String s = te.getDisplayName().getUnformattedText();
        fontRendererObj.drawString(s, 88 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        fontRendererObj.drawString(playerInv.getDisplayName().getUnformattedText(), 8, 72, 4210752);

    }

    @Override
    public void drawScreen(int x, int y, float partialTick) {

        super.drawScreen(x, y, partialTick);
        drawHoverText(x - guiLeft, y - guiTop);

    }

    private void drawHoverText(int x, int y) {

        if(x > 7 && x < 25 && y > 7 && y < 50) {

            ArrayList<String> internal = new ArrayList<String>();
            internal.add("Stored Essence");
            internal.add("" + EnumChatFormatting.RED + te.getCurEssence() + "/" + te.getMaxEssence());
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            drawHoveringText(internal, x + guiLeft, y + guiTop, fontRendererObj);
            GL11.glPopAttrib();

        }
    }
}
