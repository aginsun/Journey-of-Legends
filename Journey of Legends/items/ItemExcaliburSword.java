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

public class ItemExcaliburSword extends ItemJourneySword
{
	private EntityPlayer player;
	public static int weaponDamage = 0;
	public static EnumToolMaterial Ex = EnumHelper.addToolMaterial("Ex", 0, 1500, 0, weaponDamage, 10);
	
	public ItemExcaliburSword(int par1) 
	{
		super(par1, "Ex", 1500, 0, new String[]{"Warrior"}, 15);
		this.maxStackSize = 1;
		this.setMaxDamage(-1);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
    
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) 
    {
    	list.add(1, "Level Required: 15");
    	list.add(2, "Class Required: Warrior");
    	if(LevelKeeper.getLevel(player) >= 15 && RaceKeeper.getClass(player).equals("Warrior"))
    		list.add(3, "Damage Dealt: " + ((StatKeeper.getStrengthPoints(player) / 4) + weaponDamage));
    	else
    		list.add(3, "Damage Dealt: 0");
    }
}