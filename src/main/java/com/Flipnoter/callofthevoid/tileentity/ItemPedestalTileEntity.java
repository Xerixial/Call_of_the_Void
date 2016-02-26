package com.Flipnoter.callofthevoid.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

/**
 * Created by Connor on 2/25/2016.
 */
public class ItemPedestalTileEntity extends TileEntity implements IInventory {

    private ItemStack[] Inventory;
    private String InvName = "Essence Crystallizer";

    public ItemPedestalTileEntity() {

        Inventory = new ItemStack[getSizeInventory()];

    }

    public String getCustomName() {

        return InvName;

    }

    public void setCustomName(String name) {

        InvName = name;

    }

    @Override
    public String getName() {

        return hasCustomName() ? InvName : "container.Item_Pedestal_Tile_Entity.name";

    }

    @Override
    public boolean hasCustomName() {

        return InvName != null && !InvName.equals("");

    }

    @Override
    public IChatComponent getDisplayName() {

        return hasCustomName() ? new ChatComponentText(getName()) : new ChatComponentTranslation(getName());

    }

    @Override
    public int getSizeInventory() {

        return 1;

    }

    @Override
    public int getInventoryStackLimit() {

        return 1;

    }

    @Override
    public ItemStack getStackInSlot(int index) {

        if(index < 0 || index >= getSizeInventory())
            return null;

        return Inventory[index];

    }

    @Override
    public ItemStack decrStackSize(int index, int count) {

        if(getStackInSlot(index) != null) {
            ItemStack itemstack;

            if(getStackInSlot(index).stackSize <= count) {

                itemstack = getStackInSlot(index);
                setInventorySlotContents(index, null);
                markDirty();
                return itemstack;

            }
            else
            {

                itemstack = getStackInSlot(index).splitStack(count);

                if(getStackInSlot(index).stackSize <= 0) {

                    setInventorySlotContents(index, null);

                }
                else
                {

                    //Just to show that changes happened
                    setInventorySlotContents(index, getStackInSlot(index));

                }

                markDirty();
                return itemstack;

            }
        }
        else
        {

            return null;

        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {

        ItemStack stack = getStackInSlot(index);
        setInventorySlotContents(index, null);
        return stack;

    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

        if(index < 0 || index >= getSizeInventory())
            return;

        if(stack != null && stack.stackSize > getInventoryStackLimit())
            stack.stackSize = getInventoryStackLimit();

        if(stack != null && stack.stackSize == 0)
            stack = null;

        Inventory[index] = stack;
        markDirty();

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {

        return worldObj.getTileEntity(getPos()) == this && player.getDistanceSq(pos.add(0.5, 0.5, 0.5)) <= 64;

    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {

        return true;

    }

    @Override
    public int getField(int id) {

        return 0;

    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount() {

        return 0;

    }

    @Override
    public void clear() {

        for(int i = 0; i < getSizeInventory(); i++)
            setInventorySlotContents(i, null);

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {

        super.writeToNBT(nbt);

        NBTTagList list = new NBTTagList();

        for(int i = 0; i < getSizeInventory(); ++i) {

            if(getStackInSlot(i) != null) {

                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte)i);
                getStackInSlot(i).writeToNBT(stackTag);
                list.appendTag(stackTag);

            }
        }

        nbt.setTag("Items", list);

        if(hasCustomName())
            nbt.setString("CustomName", InvName);

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);

        for(int i = 0; i < list.tagCount(); ++i) {

            NBTTagCompound stackTag = list.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot") & 255;
            setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));

        }

        if(nbt.hasKey("CustomName", 8)) {

            setCustomName(nbt.getString("CustomName"));

        }
    }

    @Override
    public Packet getDescriptionPacket() {

        NBTTagCompound tagCompound = new NBTTagCompound();
        writeToNBT(tagCompound);
        return new S35PacketUpdateTileEntity(pos, 1, tagCompound);

    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {

        readFromNBT(pkt.getNbtCompound());

    }
}
