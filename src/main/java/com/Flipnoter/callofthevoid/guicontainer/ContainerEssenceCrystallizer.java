package com.Flipnoter.callofthevoid.guicontainer;

import com.Flipnoter.callofthevoid.inventory.SlotCrystallizerFuel;
import com.Flipnoter.callofthevoid.inventory.SlotCrystallizerOutput;
import com.Flipnoter.callofthevoid.tileentity.EssenceCrystallizerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Connor on 2/13/2016.
 */
public class ContainerEssenceCrystallizer extends Container {

    private EssenceCrystallizerTileEntity te;

    public ContainerEssenceCrystallizer(IInventory playerInv, EssenceCrystallizerTileEntity te) {

        this.te = te;

        this.addSlotToContainer(new Slot(te, 0, 44, 17));
        this.addSlotToContainer(new Slot(te, 1, 44, 53));
        this.addSlotToContainer(new SlotCrystallizerOutput(te, 2, 116, 35));
        this.addSlotToContainer(new SlotCrystallizerFuel(te, 3, 8, 53));

        // Player Inventory, Slot 9-35, Slot IDs 9-35
        for(int y = 0; y < 3; ++y) {

            for(int x = 0; x < 9; ++x) {

                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));

            }
        }

        // Player Inventory, Slot 0-8, Slot IDs 36-44
        for(int x = 0; x < 9; ++x) {

            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));

        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {

        return this.te.isUseableByPlayer(playerIn);

    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {

        ItemStack previous = null;
        Slot slot = this.inventorySlots.get(fromSlot);

        if(slot != null && slot.getHasStack()) {

            ItemStack current = slot.getStack();
            previous = current.copy();

            if(fromSlot < 3) {

                if(!this.mergeItemStack(current, 9, 39, true))
                    return null;

            }
            else
            {

                if(!this.mergeItemStack(current, 0, 2, false))
                    return null;

            }

            if(current.stackSize == 0)
                slot.putStack(null);
            else
                slot.onSlotChanged();

            if(current.stackSize == previous.stackSize)
                return null;

            slot.onPickupFromSlot(playerIn, current);

        }

        return previous;

    }
}
