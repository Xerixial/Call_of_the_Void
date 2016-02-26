package com.Flipnoter.callofthevoid.tileentity;

import com.Flipnoter.callofthevoid.blocks.ModBlocks;
import com.Flipnoter.callofthevoid.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;

import java.util.Random;

/**
 * Created by Connor on 2/13/2016.
 */
public class EssenceCrystallizerTileEntity extends TileEntity implements IInventory, ITickable {

    private int CurEssence, MaxEssence = 25000;

    private int EssencePerTick = 1, buffer;
    private int TimeToComplete = 600, CurTime;

    private static boolean converting;

    private ItemStack[] Inventory;
    private String InvName = "Essence Crystallizer";

    public EssenceCrystallizerTileEntity() {

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

        return hasCustomName() ? InvName : "container.Essence_Crystallizer_Tile_Entity.name";

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

        return 4;

    }

    @Override
    public int getInventoryStackLimit() {

        return 64;

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

    public static boolean isFuel(ItemStack item) {

        return getFuelAmount(item) > 0;

    }

    public static int getFuelAmount(ItemStack stack) {

        if(stack != null) {

            Item item = stack.getItem();

            if(item == null)
                return 0;

            if(item == ModItems.VoidEssenceChunk)
                return 100;

            if(item == ModItems.CompressedVoidEssence)
                return 900;

        }

        return 0;

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

        nbt.setInteger("CurEssence", CurEssence);

        nbt.setInteger("buffer", buffer);

        nbt.setInteger("CurTime", CurTime);

        nbt.setBoolean("converting", converting);

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

        CurEssence = nbt.getInteger("CurEssence");

        buffer = nbt.getInteger("buffer");

        CurTime = nbt.getInteger("CurTime");

        converting = nbt.getBoolean("converting");

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

    public int getCurEssence() {

        return CurEssence;

    }

    public int getMaxEssence() {

        return MaxEssence;

    }

    public int getCurProcessingTime() {

        return CurTime;

    }

    public int getProcessingTime() {

        return TimeToComplete;

    }

    @Override
    public void update() {

        boolean upd = false;

        if(!worldObj.isRemote) {

            if(CurEssence > 0) {

                CurEssence -= EssencePerTick;

                upd = true;

                if(getStackInSlot(0) != null && getStackInSlot(1) != null) {

                    if(getStackInSlot(0).getItem() == ModItems.RefinedVoidEssence && getStackInSlot(1).getItem() == Items.diamond) {

                        if(CurTime < TimeToComplete) {

                            CurTime++;

                            CurEssence -= 4;

                        }
                        else
                        {

                            if(getStackInSlot(2) != null)
                                getStackInSlot(2).stackSize++;

                            if(getStackInSlot(2) == null)
                                setInventorySlotContents(2, new ItemStack(ModItems.CrystallizedEssence));

                            decrStackSize(0, 1);
                            decrStackSize(1, 1);

                            CurTime = 0;

                            upd = true;

                        }
                    }
                    else
                    {

                        CurTime = 0;

                    }
                }
                else
                {

                    CurTime = 0;

                }
            }

            if(buffer > 0 && CurEssence < MaxEssence) {

                CurEssence += 10;
                buffer -= 10;

                upd = true;

            }

            if(CurEssence > MaxEssence) {

                CurEssence = MaxEssence;

                upd = true;

            }

            if(CurEssence < 0) {

                CurEssence = 0;

                upd = true;

            }

            if(getStackInSlot(3) != null) {

                if(CurEssence < MaxEssence - 10) {

                    converting = true;

                }
                else
                {

                    converting = false;

                }

                if(converting && buffer == 0) {

                    buffer = getFuelAmount(getStackInSlot(3));

                    decrStackSize(3, 1);

                    upd = true;

                }
            }
        }

        if(upd) {

            worldObj.markBlockForUpdate(pos);
            markDirty();

        }
    }
}
