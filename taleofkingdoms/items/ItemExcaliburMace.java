package aginsun.taleofkingdoms.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.EnumHelper;
import aginsun.taleofkingdoms.api.StatKeeper;
import aginsun.taleofkingdoms.core.handlers.RaceKeeper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemExcaliburMace extends ItemSword
{
	private EntityPlayer player;
	private StatKeeper stats;
	private static int weaponDamage;
	public static EnumToolMaterial Ex = EnumHelper.addToolMaterial("Ex", 0, -1, 0, weaponDamage, 10);
	
	public ItemExcaliburMace(int par1) 
	{
		super(par1, EnumToolMaterial.EMERALD);
		this.maxStackSize = 1;
		this.weaponDamage = 5;
		this.setMaxDamage(-1);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
    
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
    	if(stats.getLevel(player) >= 30 && (RaceKeeper.getClass(player).equals("Warrior") || RaceKeeper.getClass(player).equals("Mage")))
    		return false;
    	else
    		return true;
    }
    
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) 
    {
    	list.add(1, "Level Required: 30");
    	list.add(2, "Class Required: Warrior/Mage");
    	if(RaceKeeper.getClass(player).equals("Warrior") && stats.getLevel(player) >= 30)
    		list.add(3, "Damage Dealt: " + ((StatKeeper.getStrengthPoints(player) / 4) + weaponDamage));
    	else if(RaceKeeper.getClass(player).equals("Mage") && stats.getLevel(player) >= 30)
    		list.add(3, "Damage Dealt: " + ((StatKeeper.getIntelligencePoints(player) / 4) + weaponDamage));
    	else
    		list.add(3, "Damage Dealt: 0");
    }
}
