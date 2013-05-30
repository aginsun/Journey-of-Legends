package aginsun.journey.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import aginsun.journey.api.GoldKeeper;
import aginsun.journey.api.StatKeeper;
import aginsun.journey.core.handlers.RaceKeeper;

public class ItemDebug extends Item
{
	public ItemDebug(int par1) 
	{
		super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(-1);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
    	//TODO: print it on the screen
    	
        return false;
    }

	
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par1)
	{
		list.add(1, "Current Class: " + RaceKeeper.getClass(player));
		list.add(2, "Current Level: " + StatKeeper.getLevel(player));
		list.add(3, "Current STR: " + StatKeeper.getStrengthPoints(player));
		list.add(4, "Current DEX: " + StatKeeper.getDexerityPoints(player));
		list.add(5, "Current INT: " + StatKeeper.getIntelligencePoints(player));
		list.add(6, "Current LUK: " + StatKeeper.getLuckPoints(player));
		list.add(7, "Current Gold: " + GoldKeeper.getGoldTotal(player));
	}
}
