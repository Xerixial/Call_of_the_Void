package com.Flipnoter.callofthevoid.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Connor on 2/13/2016.
 */
public class SlotCrystallizerOutput extends Slot {

    public SlotCrystallizerOutput(IInventory inventoryIn, int index, int xPosition, int yPosition) {

        super(inventoryIn, index, xPosition, yPosition);

    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return false;

    }
}
