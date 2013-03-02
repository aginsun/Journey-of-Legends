package aginsun.taleofkingdoms.items;

import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
	private static int weaponDamage;
	public static EnumToolMaterial Ex = EnumHelper.addToolMaterial("Ex", 0, -1, 0, weaponDamage, 10);
	
	public ItemExcalibur(int par1) 
	{
		super(par1, Ex);
		this.maxStackSize = 1;
		this.setMaxDamage(-1);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	
    @SideOnly(Side.CLIENT)
    public String getTextureFile()
    {
    	return "/aginsun/textures/items.png";
    }
    
    public int getDamageVsEntity(Entity par1Entity)
    {
    	System.out.println(weaponDamage);
        return this.weaponDamage;
    }
    
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
    	this.weaponDamage = Math.round(stats.getStrengthPoints(player) / 2);
        return false;
    }
}