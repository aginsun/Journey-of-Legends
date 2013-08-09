package aginsun.journey.universal.handlers;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import aginsun.journey.universal.utils.ItemDropHelper;

public class EntityLivingHandler 
{
	@ForgeSubscribe
	public void onEntityLivingDeath(LivingDeathEvent event) 
	{
		if (event.source.getDamageType().equals("player"))
		{
			ItemDropHelper.dropCoins(event.entityLiving);
		}
	}
}
