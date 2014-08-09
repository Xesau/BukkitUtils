package mc.xesau.bukkitutils;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class BukkitUtils extends org.bukkit.plugin.java.JavaPlugin {

	private static ArrayList< Plugin > hookedPlugins = new ArrayList< Plugin >();
	
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
	
	public static void hook( Plugin pl )
	{
		if( !hookedPlugins.contains( pl ) )
			hookedPlugins.add( pl );
	}
	
}
