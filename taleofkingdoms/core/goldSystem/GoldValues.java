package aginsun.taleofkingdoms.core.goldSystem;

/**sets all the gold values for the vanilla items
 * and also any mod items that I want to be added.
 * might change int to a Float.
 *  
 * @author Aginsun
 */

public class GoldValues 
{
	public static int PriceItem(String s)
	{
		if(s.equals("tile.stone"))
			return 0;
		if(s.equals("tile.grass"))
			return 0;
		if(s.equals("tile.dirt"))
			return 0;
		if(s.equals("tile.cobblestone"))
			return 0;
		if(s.equals("tile.wood"))
			return 1;
		if(s.equals("tile.sapling"))
			return 0;
		if(s.equals("tile.log"))
			return 4;
		if(s.equals("tile.bedrock"))
			return 0;
		if(s.equals("tile.sand"))
			return 0;
		if(s.equals("tile.gravel"))
			return 0;
		
		
		return 0;
	}
}
