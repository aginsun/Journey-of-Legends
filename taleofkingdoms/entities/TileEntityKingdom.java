package aginsun.taleofkingdoms.entities;


import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import aginsun.taleofkingdoms.api.GoldKeeper;
import aginsun.taleofkingdoms.core.handlers.GoldValues;
import aginsun.taleofkingdoms.core.handlers.packets.PacketGold;
import aginsun.taleofkingdoms.core.handlers.packets.PacketType;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class TileEntityKingdom extends TileEntity implements IInventory 
{
	private ItemStack[] inventory;
	private EntityPlayer player;
	private Player par1player;
	public NBTTagCompound nbttagcompound = new NBTTagCompound();
	private GoldKeeper gold;
	
	public TileEntityKingdom()
	{
		inventory = new ItemStack[3];
	}
	
	public void updateEntity()
	{
		if(this.getStackInSlot(1) != null)
		{
			int i = nbttagcompound.getInteger(this.getStackInSlot(1).getItem().getUnlocalizedName());
			i++;
			nbttagcompound.setInteger(this.getStackInSlot(1).getItem().getUnlocalizedName(), i);
			decrStackSize(1, 1);
		}
		if(this.getStackInSlot(2) != null)
		{
			String s = getStackInSlot(2).getDisplayName();
			
		}
	}
	
	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
        int j = 0;
        if (inventory[0] != null)
        {
            for (int k = 0; k < inventory[0].stackSize; k++)
            {
                Item item = inventory[0].getItem();
                String s = item.getUnlocalizedName();
                j = GoldValues.getGoldValue(s);
                if(FMLCommonHandler.instance().getEffectiveSide().isServer())
                	gold.addGold(setPlayerName(player), j);
        		this.par1player = (Player)player;
                PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(player.username, gold.getGoldTotal(player))), par1player);
            }
            if (j != 0)
            {
                inventory[0] = null;
            }
        }
		return inventory[i];
	}
	
	@Override
    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        inventory[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }
	@Override
    public ItemStack decrStackSize(int i, int j)
    {
        if (inventory[i] != null)
        {
            if (inventory[i].stackSize <= j)
            {
                ItemStack itemstack = inventory[i];
                inventory[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = inventory[i].splitStack(j);
            if (inventory[i].stackSize == 0)
            {
                inventory[i] = null;
            }
            return itemstack1;
        }
        else
        {
            return null;
        }
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex)
	{
		return null;
	}

	@Override
	public String getInvName()
	{
		return "TeKingdom";
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}
	
	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) 
	{
		return false;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setTag("ItemList", nbttagcompound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		nbttagcompound = (NBTTagCompound) nbt.getTag("ItemList");
	}
	
	public EntityPlayer setPlayerName(EntityPlayer player)
	{
		return this.player = player;
	}
}
