package aginsun.taleofkingdoms.inventory;

import aginsun.taleofkingdoms.core.goldSystem.GoldValues;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotKingdomSell extends Slot
{
    final ContainerKingdom kingdom;

    public SlotKingdomSell(ContainerKingdom container, IInventory inventory, int par3, int par4, int par5)
    {
        super(inventory, par3, par4, par5);
        this.kingdom = container;
    }

    public boolean isItemValid(ItemStack item)
    {
    	if(GoldValues.PriceItem(item.getItem().getUnlocalizedName()) != 0)
    		return true;
    	else
    		return false;
    }

    public int getSlotStackLimit()
    {
        return 64;
    }
}
