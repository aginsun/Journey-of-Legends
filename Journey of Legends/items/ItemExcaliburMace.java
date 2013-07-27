package aginsun.journey.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import aginsun.journey.api.LevelKeeper;
import aginsun.journey.api.StatKeeper;
import aginsun.journey.core.handlers.RaceKeeper;

public class ItemExcaliburMace extends ItemJourneySword
{
	private EntityPlayer player;
	private StatKeeper stats;
	private static int weaponDamage;
	public static EnumToolMaterial Ex = EnumHelper.addToolMaterial("Ex", 0, -1, 0, weaponDamage, 10);
	
	public ItemExcaliburMace(int par1) 
	{
		super(par1, "Ex1", 2000, 5, new String[]{"Warrior", "Mage"}, 30);
		this.maxStackSize = 1;
		this.weaponDamage = 5;
		this.setMaxDamage(-1);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
    
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) 
    {
    	list.add(1, "Level Required: 30");
    	list.add(2, "Class Required: Warrior/Mage");
    	if(RaceKeeper.getClass(player).equals("Warrior") && LevelKeeper.getLevel(player) >= 30)
    		list.add(3, "Damage Dealt: " + ((StatKeeper.getStrengthPoints(player) / 4) + weaponDamage));
    	else if(RaceKeeper.getClass(player).equals("Mage") && LevelKeeper.getLevel(player) >= 30)
    		list.add(3, "Damage Dealt: " + ((StatKeeper.getIntelligencePoints(player) / 4) + weaponDamage));
    	else
    		list.add(3, "Damage Dealt: 0");
    }
}
