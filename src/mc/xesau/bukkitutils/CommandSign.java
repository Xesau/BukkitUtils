package mc.xesau.bukkitutils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class CommandSign implements Listener {

	private Sign block;
	private List< String > commands;
	
	public CommandSign( Sign block, List< String > commands, Plugin plugin ) 
	{
		this.block = block;
		this.commands = commands;
		
		Bukkit.getPluginManager().registerEvents( this, plugin );
	}
	
	public CommandSign( Sign block, String command, Plugin plugin )
	{
		this.block = block;
		this.commands = new ArrayList< String >();
		commands.add( command );

		Bukkit.getPluginManager().registerEvents( this, plugin );
	}
	
	public Sign getBlock()
	{
		return block;
	}
	
	public void setLine( int line, String text )
	{
		block.setLine( line, text );
	}
	
	public void setLines( List< String > lines )
	{
		if( lines.size() > 0 )
			block.setLine( 0, lines.get( 0 ) );
		
		if( lines.size() > 1 )
			block.setLine( 1, lines.get( 1 ) );
		
		if( lines.size() > 2 )
			block.setLine( 2, lines.get( 2 ) );
		
		if( lines.size() > 3 )
			block.setLine( 3, lines.get( 3 ) );
	}
	
	@EventHandler
	public void onClick( PlayerInteractEvent e )
	{
		if( e.getAction() == Action.RIGHT_CLICK_BLOCK )
		{
			if( e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN )
			{
				Sign b = ( Sign ) e.getClickedBlock();
				if( b.getLocation().getWorld().getName() == block.getLocation().getWorld().getName() &&
					b.getLocation().getX() == block.getLocation().getX() &&
					b.getLocation().getY() == block.getLocation().getY() &&
					b.getLocation().getZ() == block.getLocation().getZ() )
				{
					for( String cmd : commands )
					{
						Bukkit.dispatchCommand( e.getPlayer(), cmd );
					}
				}
			}
		}
	}
	
}
