package aginsun.taleofkingdoms.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.EnumHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAgBlade extends ItemSword
{
	public static EnumToolMaterial AgBlade = EnumHelper.addToolMaterial("AgBlade", 3, -1, 50.0F, 10000, 22);
	public ItemAgBlade(int par1) 
	{
		super(par1, AgBlade);
		this.setCreativeTab(null);
	}
	
	@SideOnly(Side.CLIENT)
	public String getTextureFile()
	{
    	return "/aginsun/textures/items.png";
	}
	
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
    	if(player.username.equals("aginsun"))
    		return false;
    	else
    		return true;
    }
}
