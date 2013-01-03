package aginsun.taleofkingdoms.entities;

import aginsun.taleofkingdoms.client.guis.GuiGuildMaster;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityGuildMaster extends EntityCreature
{
	public World world;
	
	public EntityGuildMaster(World par1World) 
	{
		super(par1World);
		world = par1World;
		texture = "aginsun/textures/head.png";
		moveSpeed = 0.0F;
		isImmuneToFire = false;
		health = 25;
	}

	@Override
	public int getMaxHealth() 
	{
		return 25;
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
			player.addChatMessage("Guild Master: Welcome to the order, " + player.username + ".");
			FMLCommonHandler.instance().showGuiScreen(new GuiGuildMaster(player, world));
		}
		return true;
	}
}
