package mc.xesau.bukkitutils;

import java.util.ArrayList;
import java.util.List;

import mc.xesau.bukkitutils.location.LocationUtils;

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
	
	/**
	 * Create a CommandSign instance with multiple commands
	 * 
	 * @param block The Sign block in the world
	 * @param commands The List of commands to execute when the player clicks the sign
	 * @param plugin Your plugin, to register the listener
	 */
	public CommandSign( Sign block, List< String > commands, Plugin plugin ) 
	{
		this.block = block;
		this.commands = commands;
		
		Bukkit.getPluginManager().registerEvents( this, plugin );
	}
	
	/**
	 * Create a CommandSign instance with multiple commands
	 * 
	 * @param block The Sign block in the world
	 * @param command The command to execute when the player clicks the sign
	 * @param plugin Your plugin, to register the listener
	 */
	public CommandSign( Sign block, String command, Plugin plugin )
	{
		this.block = block;
		this.commands = new ArrayList< String >();
		commands.add( command );

		Bukkit.getPluginManager().registerEvents( this, plugin );
	}
	
	/**
	 * Get the Sign
	 * 
	 * @return The sign block
	 */
	public Sign getBlock()
	{
		return block;
	}
	
	/**
	 * 
	 * @param line
	 * @param text
	 */
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
	
	/**
	 * This void will be runned when Bukkit calls a PlayerInteractEvent
	 * It will check if this block is clicked and then execute the commands.
	 * 
	 * @param e The event
	 */
	@EventHandler
	public void onClick( PlayerInteractEvent e )
	{
		if( e.getAction() == Action.RIGHT_CLICK_BLOCK )
		{
			if( e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN )
			{
				Sign b = ( Sign ) e.getClickedBlock();
				if( LocationUtils.isSame( b.getLocation(), e.getClickedBlock().getLocation() ) )
				{
					for( String cmd : commands )
					{
						Bukkit.dispatchCommand( e.getPlayer(), cmd );
					}
				}
			}
		}
	}
	
	public List< String > getCommands()
	{
		return commands;
	}
	
	public void addCommand( String command )
	{
		commands.add( command );
	}
	
	public void addCommands( List< String > commands )
	{
		for( String command : commands )
		{
			this.commands.add( command );
		}
	}
}
