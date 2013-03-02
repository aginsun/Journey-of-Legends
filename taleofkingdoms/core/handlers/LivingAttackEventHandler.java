package aginsun.taleofkingdoms.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;
import aginsun.taleofkingdoms.core.helpers.ItemDropHelper;
import aginsun.taleofkingdoms.items.InitItems;

public class LivingAttackEventHandler 
{
	private int damage;
	public StatKeeper stats;
	@ForgeSubscribe
	public void onEntityLivingAttack(LivingHurtEvent event) 
	{
		if (event.source.getDamageType().equals("player"))
		{
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			if(player.inventory.getCurrentItem() != null)
			{
				if((player.inventory.getCurrentItem().getItem() instanceof ItemSword))
				{
					event.ammount += stats.getStrengthPoints(player);
				}
			}
		}
	}
}
