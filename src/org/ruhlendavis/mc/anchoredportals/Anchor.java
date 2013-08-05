package org.ruhlendavis.mc.anchoredportals;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

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
	/**
	 * Given a config and a path, adds properties for this anchor to that config at the specified path.
	 * 
	 * @param config FileConfiguration to add the properties to.
	 * @param path Path to this anchor in the config. Do NOT include a trailing period (.).
	 */
	public void toFileConfig(FileConfiguration config, String path)
	{
		config.set(path + ".player", this.playerName);
		config.set(path + ".Overworld.X", this.getOverworldTerminus().getBlockX());
		config.set(path + ".Overworld.Y", this.getOverworldTerminus().getBlockY());
		config.set(path + ".Overworld.Z", this.getOverworldTerminus().getBlockZ());
		config.set(path + ".Nether.X", this.getNetherTerminus().getBlockX());
		config.set(path + ".Nether.Y", this.getNetherTerminus().getBlockY());
		config.set(path + ".Nether.Z", this.getNetherTerminus().getBlockZ());
	}
}
