package aginsun.taleofkingdoms.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
    
    @SideOnly(Side.CLIENT)
    public String getTextureFile()
    {
    return "/aginsun/textures/items.png";
    }
    
    @SideOnly(Side.CLIENT)
    public int getIconFromDamage(int i)
    { 
    	return 0;
    }


}
