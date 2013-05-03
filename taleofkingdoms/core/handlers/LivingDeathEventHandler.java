package aginsun.taleofkingdoms.core.handlers;

import aginsun.taleofkingdoms.api.QuestHandler;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class LivingDeathEventHandler
{
	@ForgeSubscribe
	public void onLivingDeath(LivingDeathEvent event)
	{
		if(event.source.getDamageType().equals("player")){
			EntityPlayer player = (EntityPlayer)event.source.getSourceOfDamage();
			if(event.entity instanceof EntityZombie){
				if(QuestHandler.getQuestStatus(player, "Hunting") != 3 && QuestHandler.getQuestStatus(player, "Hunting") != 0)
				{
					NBTTagCompound nbt = QuestHandler.getQuestPlayer(player);
					int x = nbt.getInteger("Zombert Kills");
					nbt.setInteger("Zombert kills", x++);
					if(x == 10)
						QuestHandler.setQuestFinished(player, "Hunting");
					QuestHandler.setQuestPlayer(player, nbt);
				}
			}
		}
	}
}
