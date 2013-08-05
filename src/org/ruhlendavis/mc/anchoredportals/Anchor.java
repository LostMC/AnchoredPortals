package org.ruhlendavis.mc.anchoredportals;

import org.bukkit.Location;

/**
 * Representation of an "anchor" (link between a given pair of nether portals) in memory.
 * 
 * @author Feaelin (Iain E. Davis) <iain@ruhlendavis.org>
 */
public class Anchor
{
	private Location overworldTerminus;
	private Location netherTerminus;
	private String playerName;

	public Location getOverworldTerminus()
	{
		return overworldTerminus;
	}

	public void setOverworldTerminus(Location overworldTerminus)
	{
		this.overworldTerminus = overworldTerminus;
	}

	public Location getNetherTerminus()
	{
		return netherTerminus;
	}

	public void setNetherTerminus(Location netherTerminus)
	{
		this.netherTerminus = netherTerminus;
	}

	public String getPlayerName()
	{
		return playerName;
	}

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}
}
