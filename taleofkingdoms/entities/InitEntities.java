package aginsun.taleofkingdoms.entities;

import aginsun.taleofkingdoms.TaleOfKingdoms;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class InitEntities 
{
	public static void Init()
	{
		GameRegistry.registerTileEntity(TileEntitySell.class, "TeSell");
		EntityRegistry.registerGlobalEntityID(EntityGuildMaster.class, "GuildMaster", 215, 255, 255);
		EntityRegistry.registerModEntity(EntityGuildMaster.class, "GuildMaster", 215, TaleOfKingdoms.instance, 250, 5, false);
		EntityRegistry.registerGlobalEntityID(EntityBuilder.class, "Builder", 216, 145, 122);
		EntityRegistry.registerModEntity(EntityBuilder.class, "Builder", 216, TaleOfKingdoms.instance, 250, 5, false);
	}
}
