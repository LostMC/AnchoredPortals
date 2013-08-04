/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ruhlendavis.mc.anchoredportals;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

/**
 *
 * @author Iain E. Davis <iain@ruhlendavis.org>
 */
public class PlayerListener implements Listener
{
	private static File playerFolder;

	PlayerListener(File playerFolder)
	{
		PlayerListener.playerFolder = playerFolder;
	}


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

			File dataFile = new File(playerFolder, event.getPlayer().getName() + ".yml");
			FileConfiguration playerData = YamlConfiguration.loadConfiguration(dataFile);
			
			if (fromEnvironment == Environment.NETHER && toEnvironment == Environment.NORMAL)
			{
				// Check for a record and use it.
			}
			else if (fromEnvironment == Environment.NORMAL && toEnvironment == Environment.NETHER)
			{
				// Record the destination.
			}
		}
	}
	
	/**
	 * Handles when a player is going from the nether to the overworld.
	 * 
	 * @param event PlayerPortalEvent object for this 'event'.
	 */
	private void toOverWorld(PlayerPortalEvent event)
	{
		
	}
	
	/**
	 * Handles when a player is going from overworld to the nether.
	 * 
	 * @param event PlayerPortalEvent object for this 'event'.
	 */
	private void toNether(PlayerPortalEvent event)
	{
	}
}
