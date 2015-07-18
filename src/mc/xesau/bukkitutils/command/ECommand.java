package mc.xesau.bukkitutils.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ECommand implements CommandExecutor {

	private HashMap< String, ECommand > subCommands = new HashMap< String, ECommand >();
	private CommandExecutor mainExecutor;
	private String helpMessage = "";
	
	private boolean useArguments = false;
	
	/**
	 * Construct an ExtendedCommand
	 * @param executor The CommandExecutor if there's no subcommand used
	 * @param helpMessage The help message, for if there's a problem with the sub command structure
	 * @param useArguments Whether the command should use arguments or sub commands
	 */
	public ECommand( CommandExecutor executor, String helpMessage, boolean useArguments )
	{
		this.mainExecutor = executor;
		this.helpMessage = helpMessage;
		this.useArguments = useArguments;
	}
	
	/**
	 * Construct an ExtendedCommand
	 * @param executor The CommandExecutor if there's no subcommand used
	 * @parma subCommands A HashMap that includes all the sub commands
	 */	
	public ECommand( CommandExecutor executor, String helpMessage, HashMap< String, ECommand > subCommands )
	{
		this( executor, helpMessage, true );
		
		for( String subCommand : subCommands.keySet() )
		{
			this.subCommands.put( subCommand.toLowerCase(), subCommands.get( subCommand ) );
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if( useArguments )
		{
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
					sender.sendMessage( helpMessage );
					return false;
				}
			}
		}
		
		return mainExecutor.onCommand(sender, command, label, args);
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

	/**
	 * Add a sub command
	 * 
	 * @param name The name of the sub command. for example /test [name]
	 * @param subcommand The ECommand instance that will be run when the subcommand is run
	 * @throws IllegalStateException When useArguments (in the constructor without the subCommands HashMap) is true
	 */
	public ECommand addSubCommand( String name, ECommand subcommand ) throws IllegalStateException
	{
		if( !useArguments )
		{
			this.subCommands.put( name.toLowerCase(), subcommand );
		}
		else
		{
			throw new IllegalStateException( "Can't use subcommands when arguments are used" );
		}
		return this;
	}
	
}
