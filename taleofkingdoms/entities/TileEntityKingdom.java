package aginsun.taleofkingdoms.entities;


import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.goldSystem.GoldValues;
import cpw.mods.fml.common.FMLCommonHandler;

public class TileEntityKingdom extends TileEntity implements IInventory 
{
	private ItemStack[] inventory;
	private EntityPlayer player;
	private HashMap<Item, Integer> ItemList = new HashMap<Item, Integer>();
	private List<Integer> itemList;
	private NBTTagCompound nbttagcompound = new NBTTagCompound();
	
	public TileEntityKingdom()
	{
		inventory = new ItemStack[3];
	}
	
	public void updateEntity()
	{
		int GoldValue = 0;
		
		/* Sell Slot */
		if(this.getStackInSlot(0) != null)
		{
			String itemName = this.getStackInSlot(0).getItemName(); //Returns Unlocalized name!
			GoldValue = GoldValues.PriceItem(itemName);
			if(FMLCommonHandler.instance().getEffectiveSide().isServer())
				GoldKeeper.addGold(player, GoldValue);
			this.decrStackSize(0, 1);
		}
		
		if(this.getStackInSlot(1) != null)
		{
			int i = nbttagcompound.getInteger(this.getStackInSlot(1).getItem().getUnlocalizedName());
			i++;
			if(FMLCommonHandler.instance().getEffectiveSide().isServer())
			{
				System.out.println("Current items: " + i);
				System.out.println("ItemName: " + this.getStackInSlot(i).getItem().getUnlocalizedName());
			}
			nbttagcompound.setInteger(this.getStackInSlot(1).getItem().getUnlocalizedName(), i);
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
