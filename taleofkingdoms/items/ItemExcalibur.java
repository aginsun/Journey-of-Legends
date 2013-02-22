package aginsun.taleofkingdoms.items;

import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumHelper;

public class ItemExcalibur extends ItemSword
{
	private EntityPlayer player;
	private StatKeeper stats;
	private static int weaponDamageEx = 500;
	private static EnumToolMaterial ExcaliburEnum = EnumHelper.addToolMaterial("ExcaliburEnum", 0, -1, 0, weaponDamageEx, 1);
	
	public ItemExcalibur(int par1) 
	{
		super(par1, ExcaliburEnum);
        this.weaponDamageEx = 500;
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	
    @SideOnly(Side.CLIENT)
    public String getTextureFile()
    {
    	return "/aginsun/textures/items.png";
    }
    
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
    	this.setIconIndex(2);
		return true;
    }
}
