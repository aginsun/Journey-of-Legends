package aginsun.taleofkingdoms.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import aginsun.taleofkingdoms.api.ExperienceKeeper;
import aginsun.taleofkingdoms.api.QuestHandler;
import aginsun.taleofkingdoms.client.guis.GuiQuest;
import aginsun.taleofkingdoms.core.quests.Quest;
import aginsun.taleofkingdoms.core.quests.QuestGuildMaster;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityGuildMaster extends EntityCreature
{
	public World world;
	public Quest quest;
	
	public EntityGuildMaster(World par1World) 
	{
		super(par1World);
		world = par1World;
		texture = "/aginsun/textures/head.png";
		moveSpeed = 0.0F;
		isImmuneToFire = false;
		health = 25;
	}

	@Override
	public int getMaxHealth() 
	{
		return 150;
	}
	
	protected boolean canDespawn()
	{
		return false;
	}
	
	public boolean canInteractWith(EntityPlayer player)
	{
		if(isDead)
		{
			return false;
		}
		else
		{
			return player.getDistanceSqToEntity(this) <= 64D;
		}
	}
	
	public boolean canBePushed()
	{
		return false;
	}
	
	public boolean isMovementCeased()
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean interact(EntityPlayer player)
	{
		if(canInteractWith(player))
		{
			heal(25);
			quest = new QuestGuildMaster();
			if(QuestHandler.getQuestStatus(player, "TheStart") == 0 && FMLCommonHandler.instance().getEffectiveSide().isServer())
				FMLCommonHandler.instance().showGuiScreen(new GuiQuest("TheStart", quest, 1));
			
			if(QuestHandler.getQuestStatus(player, "TheStart") == 1 && FMLCommonHandler.instance().getEffectiveSide().isServer())
				player.addChatMessage("Go on, open up the stat gui by pressing O on your keyboard!");
			
			if(QuestHandler.getQuestStatus(player, "TheStart") == 2 && FMLCommonHandler.instance().getEffectiveSide().isServer())
			{
				player.addChatMessage(quest.QuestEndLines(player, 1));
				quest.QuestEndReward(player, 1);
				QuestHandler.questFinishedReward(player, "TheStart");
			}
			
			if(questStatus(player, "TheStart") == 3 && questStatus(player, "Leveling") == 0 && isServer())
				FMLCommonHandler.instance().showGuiScreen(new GuiQuest("Leveling", quest, 2));
			if(questStatus(player, "Leveling") == 1)
				player.addChatMessage(new StringBuilder().append("Go on, go and level up! you need to kill about ").append(Math.round((850 - ExperienceKeeper.getExperience(player)) / 15)).append("more mobs!").toString());
			if(questStatus(player, "Leveling") == 2)
				FMLCommonHandler.instance().showGuiScreen(new GuiQuest("Leveling", quest, 2));
		}
		return true;
	}
	
	
	private int questStatus(EntityPlayer player, String x)
	{
		return QuestHandler.getQuestStatus(player, x);
	}
	
	private boolean isServer()
	{
		return FMLCommonHandler.instance().getEffectiveSide().isServer();
	}
}
