package aginsun.taleofkingdoms.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import aginsun.taleofkingdoms.entities.TileEntityKingdom;

public class ContainerKingdom extends Container
{
	private TileEntityKingdom kingdom;
	
	public ContainerKingdom(TileEntityKingdom tileEntity, InventoryPlayer inventory) 
	{
		this.kingdom = tileEntity;
		addSlotToContainer(new Slot(inventory, 0, 305, 119));
		addSlotToContainer(new Slot(inventory, 1, 305, 83));		
		addSlotToContainer(new Slot(inventory, 2, 305, 47));
		bindPlayerInventory(inventory);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return kingdom.isUseableByPlayer(player);
	}
	
	protected void bindPlayerInventory(InventoryPlayer player_inventory)
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				addSlotToContainer(new Slot(player_inventory, j + i * 9 + 9, 4 + j * 18, 168 + i * 18));
			}
		}

		for(int i = 0; i < 9; i++)	
		{
			addSlotToContainer(new Slot(player_inventory, i, 4 + i * 18, 226));
		}
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int i)
	{
		ItemStack stack = null;
		Slot slot_object = (Slot) inventorySlots.get(i);

		if(slot_object != null && slot_object.getHasStack())
			{
			ItemStack stack_in_slot = slot_object.getStack();
			stack = stack_in_slot.copy();

			if(i == 0)
			{
				if(!mergeItemStack(stack_in_slot, 1, inventorySlots.size(), true))
				{
					return null;
				}
			} 
			else if(!mergeItemStack(stack_in_slot, 0, 1, false))
			{
				return null;
			}

			if(stack_in_slot.stackSize == 0)
			{
				slot_object.putStack(null);
			} 
			else
			{
				slot_object.onSlotChanged();
			}
		}

	return stack;
	}
}
