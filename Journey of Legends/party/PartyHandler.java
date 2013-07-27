package aginsun.journey.party;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

public class PartyHandler 
{
	private HashMap<String, Party> map = new HashMap<String, Party>();
	
	private static PartyHandler instance = new PartyHandler();
	
	public static PartyHandler getInstance()
	{
		return instance;
	}
	
	public boolean addPlayerToParty(String username, String partyName)
	{
		Party party = map.get(partyName);
		if(party != null)
		{
			party.playerList.add(username);
			return true;
		}
		return false;
	}
	
	public void makeParty(String partyName, List usernames, Color color, String leaderUsername)
	{
		Party party = new Party(partyName, leaderUsername, usernames, color);
		map.put(partyName, party);
	}
	
	public boolean removePlayerFromParty(String username, String partyName)
	{
		Party party = map.get(partyName);
		if(party != null)
		{
			party.playerList.remove(username);
			return true;
		}
		return false;
	}
	
	public void disbandParty(String partyName)
	{
		map.remove(partyName);
	}
	
	public boolean isPlayerInParty(String username)
	{
		for(Party party : map.values())
		{
			List<String> list = party.playerList;
			for(String string : list)
			{
				if(string.equals(username))
				{
					return true;
				}
			}
		}
		return false;
	}

	public Party getParty(String username)
	{
		for(Party party : map.values())
		{
			List<String> list = party.playerList;
			for(String string : list)
			{
				if(string.equals(username))
				{
					return party;
				}
			}
		}
		return null;
	}
}
