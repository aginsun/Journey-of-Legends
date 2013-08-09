package aginsun.journey.universal.utils;

import java.awt.Color;
import java.util.List;

public class Party 
{
	public List playerList;
	public Color color;
	public String partyName, partyLeaderUsername;
	
	public Party(String partyName, String partyLeaderUsername, List playerList, Color color)
	{
		this.partyName = partyName;
		this.partyLeaderUsername = partyLeaderUsername;
		this.playerList = playerList;
		this.color = color;
	}
}
