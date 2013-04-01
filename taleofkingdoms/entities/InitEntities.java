package aginsun.taleofkingdoms.entities;

import aginsun.taleofkingdoms.TaleOfKingdoms;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class InitEntities 
{
	public static void Init()
	{
		GameRegistry.registerTileEntity(TileEntitySell.class, "TeSell");
		GameRegistry.registerTileEntity(TileEntityKingdom.class, "TeKingdom");
		
		EntityRegistry.registerGlobalEntityID(EntityGuildMaster.class, "GuildMaster", 215, 255, 255);
		EntityRegistry.registerModEntity(EntityGuildMaster.class, "GuildMaster", 215, TaleOfKingdoms.instance, 250, 5, false);
		
		EntityRegistry.registerGlobalEntityID(EntityBuilder.class, "Builder", 216, 145, 122);
		EntityRegistry.registerModEntity(EntityBuilder.class, "Builder", 216, TaleOfKingdoms.instance, 250, 5, false);
		
		EntityRegistry.registerGlobalEntityID(EntityFarmerKeeper.class, "Farmer", 217, 145, 122);
		EntityRegistry.registerModEntity(EntityFarmerKeeper.class, "Farmer", 217, TaleOfKingdoms.instance, 250, 5, false);
		
		EntityRegistry.registerGlobalEntityID(EntityGuildMember.class, "GuildMember", 218, 145, 122);
		EntityRegistry.registerModEntity(EntityGuildMember.class, "GuildMember", 218, TaleOfKingdoms.instance, 250, 5, false);
		
		EntityRegistry.registerGlobalEntityID(EntityWeaponKeeper.class, "WeaponKeeper", 219, 145, 122);
		EntityRegistry.registerModEntity(EntityWeaponKeeper.class, "WeaponKeeper", 219, TaleOfKingdoms.instance, 250, 5, false);
	}
}
