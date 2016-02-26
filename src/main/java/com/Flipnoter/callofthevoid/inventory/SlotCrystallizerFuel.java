package com.Flipnoter.callofthevoid.inventory;

import com.Flipnoter.callofthevoid.tileentity.EssenceCrystallizerTileEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Connor on 2/13/2016.
 */
public class SlotCrystallizerFuel extends Slot {

    public SlotCrystallizerFuel(IInventory inventoryIn, int index, int xPosition, int yPosition) {

        super(inventoryIn, index, xPosition, yPosition);

    }

    @Override
    public boolean isItemValid(ItemStack stack) {

        return EssenceCrystallizerTileEntity.isFuel(stack);

    }
}
