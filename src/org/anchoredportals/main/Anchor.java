package org.anchoredportals.main;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class Anchor
{
	private Location overworldTerminus;
	private String overworldWorldName;
	private Location netherTerminus;
	private String netherWorldName;
	private UUID uuid;

	public Location getOverworldTerminus()
	{
		return overworldTerminus;
	}

	public void setOverworldTerminus(Location overworldTerminus)
	{
		overworldWorldName = overworldTerminus.getWorld().getName();
		this.overworldTerminus = overworldTerminus;
	}

	public Location getNetherTerminus()
	{
		return netherTerminus;
	}

	public void setNetherTerminus(Location netherTerminus)
	{
		netherWorldName = netherTerminus.getWorld().getName();
		this.netherTerminus = netherTerminus;
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}

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
		String uuidString = config.getString(path + ".uuid", "");
		UUID uuid;
		// This complexity is only necessary for a few months while
		// we're in transition from name to UUID. 2014-05-30 IED.
		if (uuidString.isEmpty())
		{
			String name = config.getString(path + ".player");
			OfflinePlayer player = Bukkit.getOfflinePlayer(name);
			if (player == null)
			{
				return null;
			}
			else
			{
				uuid = player.getUniqueId();
			}
		}
		else
		{
			uuid = UUID.fromString(uuidString);
		}
		anchor.setUuid(uuid);
		anchor.setOverworldTerminus(overworldTerminus);
		anchor.setNetherTerminus(netherTerminus);

		return anchor;
	}

	public void toFileConfig(FileConfiguration config, String path)
	{
		config.set(path + ".uuid", uuid.toString());
		config.set(path + ".Overworld.Name", overworldWorldName);
		config.set(path + ".Overworld.X", overworldTerminus.getBlockX());
		config.set(path + ".Overworld.Y", overworldTerminus.getBlockY());
		config.set(path + ".Overworld.Z", overworldTerminus.getBlockZ());
		config.set(path + ".Nether.Name", netherWorldName);
		config.set(path + ".Nether.X", netherTerminus.getBlockX());
		config.set(path + ".Nether.Y", netherTerminus.getBlockY());
		config.set(path + ".Nether.Z", netherTerminus.getBlockZ());
	}
}
