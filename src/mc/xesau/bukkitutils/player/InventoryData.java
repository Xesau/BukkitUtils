package mc.xesau.bukkitutils.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryData {

		private Location loc;
		private ItemStack[] armor;
		private ItemStack[] inv;
		private int xp = 0;
		
		private UUID playerUUID;
		
		/**
		 * Create a instance that holds the information of the player
		 * 
		 * @param p The player to copy the data from
		 */
		public InventoryData( Player p )
		{
			xp = p.getTotalExperience();
			loc = p.getLocation();
			armor = p.getInventory().getArmorContents();
			inv = p.getInventory().getContents();
			playerUUID = p.getUniqueId();
			
			p.getInventory().clear();
			p.setTotalExperience( 0 );
		}
		
		/**
		 * Apply the InventoryData to the original owner of the inventory
		 */
		public void restoreInventory()
		{
			Player p = Bukkit.getServer().getPlayer( playerUUID );
			applyTo( p );
		}
		
		/**
		 * Apply the InventoryData to the given player
		 * 
		 * @param p The player
		 */
		public void applyTo( Player p )
		{
			p.getInventory().clear();
			
			p.setTotalExperience( xp );
			p.teleport( loc );
			p.getInventory().setContents( inv );
			p.getInventory().setArmorContents( armor );
		}
		
		/**
		 * Apply the InventoryData to the given player
		 * 
		 * @param playerUUID The UUID of the player
		 */
		public void applyTo( UUID playerUUID )
		{
			applyTo( Bukkit.getPlayer( playerUUID ) );
		}
		
		/**
		 * Apply the InventoryData to the given player
		 * 
		 * @deprecated Player Names are inconsistent because of the new username change feature
		 * @param playerName The name of the player
		 */
		
		@Deprecated
		public void applyTo( String playerName )
		{
			applyTo( Bukkit.getPlayer( playerName ) );
		}
	
}
