package org.anchoredportals.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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
	 * Create an anchor from a file configuration path.
	 * 
	 * @param config FileConfiguration to fetch the data from
	 * @param path Path to the anchor's data.
	 * @return The newly created anchor.
	 * 
	 */
	public static Anchor fromFileConfig(FileConfiguration config, String path)
	{
		World world = Bukkit.getWorld(config.getString(path + ".Overworld.Name"));
		Double x = config.getDouble(path + ".Overworld.X");
		Double y = config.getDouble(path + ".Overworld.Y");
		Double z = config.getDouble(path + ".Overworld.Z");
		
		Location overworldTerminus = new Location(world, x, y, z);
		
		world = Bukkit.getWorld(config.getString(path + ".Nether.Name"));
		x = config.getDouble(path + ".Nether.X");
		y = config.getDouble(path + ".Nether.Y");
		z = config.getDouble(path + ".Nether.Z");
		
		Location netherTerminus = new Location(world, x, y, z);

		Anchor anchor = new Anchor();
		anchor.setPlayerName(config.getString(path + ".player"));
		anchor.setOverworldTerminus(overworldTerminus);
		anchor.setNetherTerminus(netherTerminus);
		
		return anchor;
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
		config.set(path + ".Overworld.Name", this.getOverworldTerminus().getWorld().getName());
		config.set(path + ".Overworld.X", this.getOverworldTerminus().getBlockX());
		config.set(path + ".Overworld.Y", this.getOverworldTerminus().getBlockY());
		config.set(path + ".Overworld.Z", this.getOverworldTerminus().getBlockZ());
		config.set(path + ".Nether.Name", this.getNetherTerminus().getWorld().getName());
		config.set(path + ".Nether.X", this.getNetherTerminus().getBlockX());
		config.set(path + ".Nether.Y", this.getNetherTerminus().getBlockY());
		config.set(path + ".Nether.Z", this.getNetherTerminus().getBlockZ());
	}
}
