package aginsun.taleofkingdoms.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWand extends Item
{
	public ItemWand(int par1) 
	{
		super(par1);
		this.setMaxDamage(450);
		this.setTextureFile("/aginsun/textures/items.png");
		this.bFull3D = true;
		this.setIconCoord(4, 0);
	}
	
	@Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		
		return true;
	}
}
