package aginsun.journey.universal.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.EnumHelper;
import aginsun.journey.server.api.LevelKeeper;
import aginsun.journey.server.api.RaceKeeper;

public class ItemJourneySword extends ItemSword
{
	private static String name;
	private static int harvestLevel = 0;
	private static int maxUses;
	private static int efficiency = 0;
	private static int damage;
	private static int enchantability = 22;
	private String[] classes;
	private int level;
	
	public ItemJourneySword(int par1, String name, int maxUses, int damage, String[] classes, int level) 
	{
		super(par1, EnumHelper.addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability));
		this.damage = damage;
		this.classes = classes;
		this.level = level;
	}
	
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
    	for(String string : classes)
    		if(string.equals(RaceKeeper.getClass(player)))
    			if(level <= LevelKeeper.getLevel(player))
    				return false;
    	
        return true;
    }
}
