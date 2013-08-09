package aginsun.journey.universal.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemCoins extends Item
{
    public ItemCoins(int i)
    {
        super(i);
        super.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }  
}
