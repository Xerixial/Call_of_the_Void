package com.Flipnoter.callofthevoid.crafting;

import net.minecraft.item.ItemStack;

/**
 * Created by Connor on 2/26/2016.
 */
public class InfusionRecipe {

    private ItemStack Output;

    private ItemStack AltarItem;

    private ItemStack[] Components;

    public InfusionRecipe(ItemStack output, ItemStack altarItem, ItemStack[] components) {

        Output = output;
        AltarItem = altarItem;
        Components = components;

    }

    public ItemStack getOutput() {

        return Output;

    }

    public ItemStack getAltarItem() {

        return AltarItem;

    }

    public ItemStack[] getComponents() {

        return Components;

    }
}
