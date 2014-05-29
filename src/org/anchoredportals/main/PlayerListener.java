package org.anchoredportals.main;

import java.util.Iterator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class PlayerListener implements Listener
{
	@EventHandler
	public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event)
	{
	}

  @EventHandler
  public void onPlayerRespawn(PlayerRespawnEvent event)
	{
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPortalEvent(PlayerPortalEvent event)
	{
		if (event.getCause() == TeleportCause.NETHER_PORTAL)
		{
			Environment fromEnvironment = event.getFrom().getWorld().getEnvironment();
			Environment toEnvironment = event.getTo().getWorld().getEnvironment();

			if (fromEnvironment == Environment.NETHER && toEnvironment == Environment.NORMAL)
			{
				toOverWorld(event);
			}
			else if (fromEnvironment == Environment.NORMAL && toEnvironment == Environment.NETHER)
			{
				toNether(event);
			}
		}
	}

	private void toOverWorld(PlayerPortalEvent event)
	{
		Location fromPortal = portalIdentifyingLocation(event.getPortalTravelAgent().findPortal(event.getFrom()));
		Location toPortal = portalIdentifyingLocation(event.getPortalTravelAgent().findOrCreate(event.getTo()));

		for (Iterator<Anchor> iterator = AnchoredPortals.anchors.iterator(); iterator.hasNext();)
		{
			Anchor anchor = iterator.next();

			if (event.getPlayer().getName().equals(anchor.getPlayerName()) && fromPortal.getBlockX() == anchor.getNetherTerminus().getBlockX() && fromPortal.getBlockY() == anchor.getNetherTerminus().getBlockY() && fromPortal.getBlockZ() == anchor.getNetherTerminus().getBlockZ())
			{
				// This way, we preserve the yaw/pitch/facing/etc.
				toPortal.setX(anchor.getOverworldTerminus().getBlockX());
				toPortal.setY(anchor.getOverworldTerminus().getBlockY());
				toPortal.setZ(anchor.getOverworldTerminus().getBlockZ());
				event.setTo(toPortal);
				iterator.remove();
				break;
			}
		}
	}

	private void toNether(PlayerPortalEvent event)
	{
		Anchor anchor = new Anchor();
		anchor.setOverworldTerminus(portalIdentifyingLocation(event.getPortalTravelAgent().findPortal(event.getFrom())));
		anchor.setNetherTerminus(portalIdentifyingLocation(event.getPortalTravelAgent().findPortal(event.getPortalTravelAgent().findOrCreate(event.getTo()))));
		anchor.setPlayerName(event.getPlayer().getName());

		AnchoredPortals.anchors.add(anchor);
	}

	/*
	 * Returns the northwest most location of the two possible portal locations.
	 */
	private Location portalIdentifyingLocation(Location location) throws InvalidPortalLocationException
	{
		location.add(0,1,0);
		if (location.getBlock().getRelative(BlockFace.NORTH).getType() == Material.PORTAL)
		{
			return location.getBlock().getRelative(BlockFace.NORTH).getLocation();
		}
		else if (location.getBlock().getRelative(BlockFace.WEST).getType() == Material.PORTAL)
		{
			return location.getBlock().getRelative(BlockFace.WEST).getLocation();
		}
		else if (location.getBlock().getRelative(BlockFace.EAST).getType() == Material.PORTAL)
		{
			return location;
		}
		else if (location.getBlock().getRelative(BlockFace.SOUTH).getType() == Material.PORTAL)
		{
			return location;
		}
		else
		{
			throw new InvalidPortalLocationException();
		}
	}
}
