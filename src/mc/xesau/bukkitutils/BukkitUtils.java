package mc.xesau.bukkitutils;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class BukkitUtils extends org.bukkit.plugin.java.JavaPlugin {
	
	/**
	 * An ArrayList of hooked plugins.
	 */
	private static ArrayList< Plugin > hookedPlugins = new ArrayList< Plugin >();
	
	/**
	 * The command executor for the command /bukkitutils
	 * 
	 * @see {@link org.bukkit.command.CommandExecutor#onCommand(CommandSender, Command, String, String[])}
	 */
	public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args )
	{
		if( cmd.getName().equalsIgnoreCase( "bukkitutils" ) )
		{
			if( sender.hasPermission( "bukkitutils.pluginlist" ) )
			{
				if( hookedPlugins.size() > 0 )
				{
					sender.sendMessage( "§lPlugins that use §a§lBukkitUtils:" );
					for( Plugin p : hookedPlugins )
					{
						sender.sendMessage( "- " + p.getName() + " v" + p.getDescription().getVersion() );
					}
				}
				else
				{
					sender.sendMessage( "§lThere are no plugins hooked into §a§lBukkitUtils" );
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Disable all hooked plugins to prevent a memory leak
	 */
	public void onDisable()
	{
		for( Plugin pl : hookedPlugins )
			unhook( pl );
		
		hookedPlugins = null;
	}
	
	/**
	 * Hook a plugin
	 * 
	 * @param pl The plugin to hook
	 */
	public static void hook( Plugin pl )
	{
		if( !hookedPlugins.contains( pl ) )
			hookedPlugins.add( pl );
	}
	
	/**
	 * Unhook plugin <code>pl</code>
	 * 
	 * @param pl The plugin to unhook
	 */
	public static void unhook( Plugin pl )
	{
		if( hookedPlugins.contains( pl ) )
			hookedPlugins.remove( pl );
	}
	
}
