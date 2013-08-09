package aginsun.journey.universal.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import aginsun.journey.client.guis.GuiQuest;
import aginsun.journey.server.api.QuestHandler;
import aginsun.journey.universal.handlers.QuestRegistry;
import aginsun.journey.universal.packets.PacketQuestData;
import aginsun.journey.universal.packets.PacketType;
import aginsun.journey.universal.utils.Quest;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
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
		isImmuneToFire = false;
		setEntityHealth(25.0F);
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
			if(QuestHandler.instance().isQuestActiveClient(player, QuestRegistry.getQuest(4).setPlayer(player).getQuestName()))
			{
				quest = QuestRegistry.getQuest(4).setPlayer(player);
				PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketQuestData(player.username, quest.getQuestName(), 2)));
				FMLCommonHandler.instance().showGuiScreen(new GuiQuest(quest.getQuestName(), quest, true));
				return true;
			}
			if(QuestHandler.instance().getQuestStatus(player, "The beginning of a great adventure")  != 3)
				quest = QuestRegistry.getQuest(1).setPlayer(player);
			else if(QuestHandler.instance().getQuestStatus(player, "Hunting")  != 3)
				quest = QuestRegistry.getQuest(3).setPlayer(player);
			else if(QuestHandler.instance().getQuestStatus(player, "Leveling") != 3)
				quest = QuestRegistry.getQuest(2).setPlayer(player);
			else if(QuestHandler.instance().getQuestStatus(player, "Progression")  != 3)
				quest = QuestRegistry.getQuest(5).setPlayer(player);
			if(quest != null)
				quest.update();
		}
		return true;
	}
}
