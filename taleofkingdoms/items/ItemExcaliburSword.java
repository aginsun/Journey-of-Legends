package aginsun.taleofkingdoms.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.EnumHelper;
import aginsun.taleofkingdoms.core.goldSystem.RaceKeeper;
import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemExcaliburSword extends ItemSword
{
	private EntityPlayer player;
	private StatKeeper stats;
	private static int weaponDamage;
	public static EnumToolMaterial Ex = EnumHelper.addToolMaterial("Ex", 0, -1, 0, weaponDamage, 10);
	
	public ItemExcaliburSword(int par1) 
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
    
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
    	if(stats.getLevel(player) >= 15)
    		return false;
    	else
    		return true;
    }
    
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) 
    {
    	list.add(1, "Level Required: 15");
    	list.add(2, "Class Required: Warrior");
    	if(stats.getLevel(player) >= 15 && RaceKeeper.getClass(player).equals("Warrior"))
    		list.add(3, "Damage Dealt: " + ((StatKeeper.getStrengthPoints(player) / 4) + weaponDamage));
    	else
    		list.add(3, "Damage Dealt: 0");
    }
}