package org.anchoredportals.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;
import org.anchoredportals.utility.Log;

public final class AnchoredPortals extends JavaPlugin
{
	public static Log log;
	private static Metrics metrics;
	private static AnchoredPortals instance;
	public static List<Anchor> anchors;

	@Override
	public void onEnable()
	{
		instance = this;
		log = new Log(this.getLogger(), Level.ALL);

		try
		{
			metrics = new Metrics(this);
			metrics.start();
			log.fine("Plugin Metrics activated.");
		}
		catch (IOException e)
		{
			log.warning("Plugin Metrics submission failed.");
		}

		this.saveDefaultConfig();

		AnchoredPortals.anchors = new ArrayList<Anchor>();
		ConfigurationSection anchorSection = this.getConfig().getConfigurationSection("anchors");

		if (anchorSection != null)
		{
			Set<String> anchorKeys = anchorSection.getKeys(false);

			for (String key : anchorKeys)
			{
				Anchor anchor = Anchor.fromFileConfig(this.getConfig(), "anchors." + key);
				if (anchor != null)
				{
					anchors.add(anchor);
				}
			}
		}

		Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	}

	@Override
	public void onDisable()
	{
		int i = 0;

		// Clear the current set of anchors.
		this.getConfig().set("anchors", null);

		for (Anchor anchor : anchors)
		{
			i++;
			anchor.toFileConfig(this.getConfig(), "anchors." + i);
		}

		this.saveConfig();

		anchors = null;
		if (metrics != null)
		{
			try
			{
				metrics.cancelTask();
			}
			catch (NoSuchMethodError exception)
			{
				log.warning("Metrics cancelTask() method unavailable: " + exception.getMessage());
			}
			metrics = null;
		}

		// The last thing we will do
		instance = null;
	}
}
