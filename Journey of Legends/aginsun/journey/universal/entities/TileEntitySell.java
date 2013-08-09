package aginsun.journey.universal.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import aginsun.journey.server.api.ExperienceKeeper;
import aginsun.journey.server.api.GoldKeeper;
import aginsun.journey.universal.handlers.GoldValueHandler;
import aginsun.journey.universal.packets.PacketGold;
import aginsun.journey.universal.packets.PacketType;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class TileEntitySell extends TileEntity implements IInventory
{
	private ItemStack[] inventory;
    public GoldKeeper gold;
    public World world;
    public EntityPlayer player;
    public GoldValueHandler goldvalues;
    public ExperienceKeeper experience;
    public Player par1player;

	public TileEntitySell()
	{
		inventory = new ItemStack[1];
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
        if (inventory[i] != null)
        {
            for (int k = 0; k < inventory[i].stackSize; k++)
            {
                Item item = inventory[i].getItem();
                String s = item.getUnlocalizedName();
                j = goldvalues.getGoldValue(s);
                if(FMLCommonHandler.instance().getEffectiveSide().isServer())
                	gold.addGold(setPlayerName(player), j);
        		this.par1player = (Player)player;
        		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(player.username, GoldKeeper.getGoldTotal(player))), (Player)player);
            }
            if (j != 0)
            {
                inventory[i] = null;
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
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public String getInvName()
	{
		return "TeSell";
	}
	
	public EntityPlayer setPlayerName(EntityPlayer player)
	{
		return this.player = player;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) 
	{
		return false;
	}
}