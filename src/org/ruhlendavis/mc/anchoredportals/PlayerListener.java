/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ruhlendavis.mc.anchoredportals;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 *
 * @author Iain E. Davis <iain@ruhlendavis.org>
 */
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
	
	@EventHandler
	public void onPlayerPortalEvent(PlayerPortalEvent event)
	{
	}
}
