package aginsun.journey.core.handlers;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import aginsun.journey.api.QuestHandler;

public class LivingDeathEventHandler
{
	@ForgeSubscribe
	public void onLivingDeath(LivingDeathEvent event)
	{
		if(event.source.getDamageType().equals("player")){
			EntityPlayer player = (EntityPlayer)event.source.getSourceOfDamage();
			if(event.entityLiving instanceof EntityZombie){
				if(QuestHandler.instance().isQuestActive(player, "Hunting"))
				{
					NBTTagCompound nbt = QuestHandler.instance().getQuestPlayer(player);
					int x = nbt.getInteger("QuestHunting");
					x++;
					nbt.setInteger("QuestHunting", x);
					System.out.println("Current Zombies killed for quest Hunting: " + x);
					if(x == 10)
						QuestHandler.instance().setQuestFinished(player, "Hunting");
					QuestHandler.instance().setQuestPlayer(player, nbt);
				}
			}
			
			if(event.entityLiving.getEntityName().equals("Some mo creature name for instance :D"))
			{
				
			}
		}
	}
}