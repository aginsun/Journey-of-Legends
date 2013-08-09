package aginsun.journey.universal.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import aginsun.journey.server.api.RaceKeeper;
import aginsun.journey.server.api.StatKeeper;
import aginsun.journey.universal.entities.EntityShuricken;
import aginsun.journey.universal.items.ItemClaw;
import aginsun.journey.universal.items.ItemExcaliburMace;
import aginsun.journey.universal.items.ItemWand;

public class LivingAttackEventHandler 
{
	private int damage;
	public StatKeeper stats;
	public RaceKeeper race;
	@ForgeSubscribe
	public void onEntityLivingAttack(LivingHurtEvent event) //TODO: Balancing!
	{
		if (event.source.getDamageType().equals("player"))
		{
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			if(player.inventory.getCurrentItem() != null)
			{
				if(race.getClass(player).equals("Warrior"))
				{
					if((player.inventory.getCurrentItem().getItem() instanceof ItemSword))
					{
						event.ammount += Math.round(stats.getStrengthPoints(player) / 4);
					}
				}
				if(race.getClass(player).equals("Hunter"))
				{
					if((player.inventory.getCurrentItem().getItem() instanceof ItemBow))
					{
						event.ammount += Math.round(stats.getDexerityPoints(player) / 4);
					}
				}
				if(race.getClass(player).equals("Mage"))
				{
					if((player.inventory.getCurrentItem().getItem() instanceof ItemWand))
					{
						event.ammount += Math.round(stats.getIntelligencePoints(player) / 4);
					}
					else if((player.inventory.getCurrentItem().getItem() instanceof ItemExcaliburMace))
					{
						event.ammount += Math.round(stats.getIntelligencePoints(player) / 4);
					}
				}
				if(race.getClass(player).equals("Thief"))
				{
					if((player.inventory.getCurrentItem().getItem() instanceof ItemClaw))
					{
						event.ammount += Math.round(stats.getLuckPoints(player) / 4);
					}
				}
			}
		}
		
		if (event.source.getSourceOfDamage() instanceof EntityArrow) 
		{
            if (((EntityArrow) event.source.getSourceOfDamage()).shootingEntity != null) 
            {
                if (((EntityArrow) event.source.getSourceOfDamage()).shootingEntity instanceof EntityPlayer) 
                {
        			EntityPlayer player = (EntityPlayer) ((EntityArrow) event.source.getSourceOfDamage()).shootingEntity;
    				if(race.getClass(player).equals("Hunter"))
    				{
    					event.ammount += Math.round(stats.getDexerityPoints(player) / 4);
    				}
                }
            }
        }
		
		if(event.source.getSourceOfDamage() instanceof EntityShuricken)
		{
			if(((EntityShuricken) event.source.getSourceOfDamage()).getThrower() != null)
			{
				if(((EntityShuricken) event.source.getSourceOfDamage()).getThrower() instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer) ((EntityShuricken) event.source.getSourceOfDamage()).getThrower();
					if(race.getClass(player).equals("Thief"))
						event.ammount += Math.round(StatKeeper.getLuckPoints(player) / 8);
				}
			}
		}
	}
}