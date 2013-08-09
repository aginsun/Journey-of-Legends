package aginsun.journey.universal.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityBuilder extends EntityCreature
{
	private World world;

	
	public EntityBuilder(World par1World) 
	{
		super(par1World);
		this.world = par1World;
		this.setEntityHealth(25000.0F);
		isImmuneToFire = false;
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
			player.addChatMessage("Builder: Greetings " + player.username + ", lets get starting.");
		}
		return true;
	}
}
