package aginsun.journey.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import aginsun.journey.entities.TileEntityKingdom;

public class ContainerKingdom extends Container
{
	protected TileEntityKingdom kingdom;
	
	private SlotKingdomSell slotSell;
	
	public ContainerKingdom(TileEntityKingdom tileEntity, InventoryPlayer inventory) 
	{
		this.kingdom = tileEntity;
		bindPlayerInventory(inventory);
		addSlotToContainer(this.slotSell = new SlotKingdomSell(this, tileEntity, 0, 202, 120));
		addSlotToContainer(new Slot(tileEntity, 1, 160, 120));		
		addSlotToContainer(new Slot(tileEntity, 2, 121, 120));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return kingdom.isUseableByPlayer(player);
	}
	
	protected void bindPlayerInventory(InventoryPlayer player_inventory)
	{
        for (int var4 = 0; var4 < 9; ++var4) 
        {
            this.addSlotToContainer(new Slot(player_inventory, var4, 11 + var4 * 18, 224));
        }
        for (int var4 = 0; var4 < 3; ++var4)
        {
            for (int var5 = 0; var5 < 9; ++var5)
            {
                this.addSlotToContainer(new Slot(player_inventory, (var5 + (var4 + 1) * 9), 11 + var5 * 18, 166 + var4 * 18));
            }
        }
	}
	
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 0)
            {
                if (!this.mergeItemStack(itemstack1, 1, 37, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (!this.slotSell.getHasStack() && this.slotSell.isItemValid(itemstack1) && itemstack1.stackSize >= 1)
            {
                if (!this.mergeItemStack(itemstack1, 0, 1, false))
                {
                    return null;
                }
            }
            else if (par2 >= 1 && par2 < 28)
            {
                if (!this.mergeItemStack(itemstack1, 28, 37, false))
                {
                    return null;
                }
            }
            else if (par2 >= 28 && par2 < 37)
            {
                if (!this.mergeItemStack(itemstack1, 1, 28, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 1, 37, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}	
