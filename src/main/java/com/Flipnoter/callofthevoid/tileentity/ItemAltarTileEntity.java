package com.Flipnoter.callofthevoid.tileentity;

import com.Flipnoter.callofthevoid.blocks.ModBlocks;
import com.Flipnoter.callofthevoid.crafting.InfusionRecipe;
import com.Flipnoter.callofthevoid.crafting.InfusionRecipes;
import com.Flipnoter.callofthevoid.items.ModItems;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import org.lwjgl.Sys;
import scala.tools.nsc.Global;

/**
 * Created by Connor on 2/25/2016.
 */
public class ItemAltarTileEntity extends TileEntity implements IInventory, ITickable {

    private boolean Running;

    private ItemStack[] Inventory;
    private String InvName = "Item Altar";

    private BlockPos[] pedPos;

    InfusionRecipes recipes = InfusionRecipes.instance();

    public ItemAltarTileEntity() {

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

        return hasCustomName() ? InvName : "container.Item_Altar_Tile_Entity.name";

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

        nbt.setBoolean("Running", Running);

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

        Running = nbt.getBoolean("Running");

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

    private void setPedPos() {

        pedPos = new BlockPos[] {new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 4),
                                 new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() + 3),
                                 new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() + 1),
                                 new BlockPos(pos.getX() + 4, pos.getY(), pos.getZ() - 1),
                                 new BlockPos(pos.getX() + 3, pos.getY(), pos.getZ() - 3),
                                 new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 4),
                                 new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 4),
                                 new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() - 3),
                                 new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() - 1),
                                 new BlockPos(pos.getX() - 4, pos.getY(), pos.getZ() + 1),
                                 new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ() + 3),
                                 new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 4)};

    }

    public void setRunning(boolean val) {

        Running = val;

        worldObj.markBlockForUpdate(pos);
        markDirty();

    }

    public boolean checkForPillars() {

        for(int i = 0; i < pedPos.length; i++) {

            if(worldObj.getBlockState(pedPos[i]) == ModBlocks.ItemPedestal.getDefaultState()) {

                if(i == pedPos.length - 1)
                    return true;

            }
            else
            {

                break;

            }
        }

        return false;

    }

    public boolean checkForItems() {

        return true;

    }

    @Override
    public void update() {

        if(pedPos == null)
            setPedPos();

        if(!worldObj.isRemote && Running) {

            if(getStackInSlot(0) != null && checkForPillars() && checkForItems()) {

                setInventorySlotContents(0, new ItemStack(ModItems.ImpureVoidIngot));

                worldObj.addWeatherEffect(new EntityLightningBolt(worldObj, pos.getX(), pos.getY(), pos.getZ()));

            }

            setRunning(false);

        }
    }
}
