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
		if(s.equals("tile.dirt"))
			return 0;
		if(s.equals("tile.cobblestone"))
			return 0;
		if(s.equals("tile.grass"))
			return 0;
		if(s.equals("tile.mycelium"))
			return 0;
		if(s.equals(""))
			return 0;
		
		
		return 0;
	}
}
