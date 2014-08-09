package mc.xesau.bukkitutils.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ECommand implements CommandExecutor {

	private HashMap< String, ECommand > subCommands;
	private CommandExecutor mainExecutor;
	private String helpMessage;
	
	/**
	 * Construct an ExtendedCommand
	 * @param executor The CommandExecutor if there's no subcommand used
	 */
	public ECommand( CommandExecutor executor, String helpMessage )
	{
		this.mainExecutor = executor;
		this.helpMessage = helpMessage;
	}
	
	/**
	 * Construct an ExtendedCommand
	 * @param executor The CommandExecutor if there's no subcommand used
	 * @parma subCommands A HashMap that includes all the sub commands
	 */	
	public ECommand( CommandExecutor executor, String helpMessage, HashMap< String, ECommand > subCommands )
	{
		this( executor, helpMessage );
		this.subCommands = subCommands;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if( args.length > 0 )
		{
			if( subCommands.containsKey( args[0] ) )
			{
				// Create a List< String > with all the arguments for the sub command
				ArrayList< String > subArguments = new ArrayList<String>( Arrays.asList( args ) );
				
				// Remove the sub command itself
				subArguments.remove( 0 );
				
				// Run the command with the subArguments list converted back to an String[] array
				return subCommands.get( args[0] ).onCommand(sender, command, label, (String[]) subArguments.toArray( new String[0]) );
			}
			
			else
			{
				return mainExecutor.onCommand(sender, command, label, args);
			}
		}
		return false;
	}
	
	/**
	 * Get the command help message
	 * @return the command help message
	 */
	public String getHelpMessage()
	{
		return helpMessage;
	}
	
	/**
	 * Get all the subcommands
	 * @return a HashMap of subcommands
	 */
	public HashMap< String, ECommand > getSubCommands()
	{
		return subCommands;
	}

}
