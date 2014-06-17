package mc.xesau.bukkitutils.command;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ECommand implements CommandExecutor {

	public HashMap< String, ECommand > subCommands;
	public CommandExecutor mainExecutor;
	
	/**
	 * Construct an ExtendedCommand
	 * @param executor The CommandExecutor if there's no subcommand used
	 */
	public ECommand( CommandExecutor executor )
	{
		this.mainExecutor = executor;
	}
	
	/**
	 * Construct an ExtendedCommand
	 * @param executor The CommandExecutor if there's no subcommand used
	 * @parma subCommands A HashMap that includes all the sub commands
	 */	
	public ECommand( CommandExecutor executor, HashMap< String, ECommand > subCommands )
	{
		this( executor );
		this.subCommands = subCommands;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if( args.length > 0 )
		{
			if( subCommands.containsKey( args[0] ) )
			{
				// Create a List< String > with all the arguments for the sub command
				List< String > subArguments = Arrays.asList( args );
				
				// Remove the sub command itself
				subArguments.remove( 0 );
				
				// Run the command with the subArguments list converted back to an String[] array
				subCommands.get( args[0] ).onCommand(sender, command, label, ( String[] ) subArguments.toArray() );
			}
			
			else
			{
				mainExecutor.onCommand(sender, command, label, args);
			}
		}
		return false;
	}

}
