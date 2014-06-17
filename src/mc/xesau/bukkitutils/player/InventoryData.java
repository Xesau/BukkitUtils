package mc.xesau.bukkitutils.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryData {

		private Location loc;
		private ItemStack[] armor;
		private ItemStack[] inv;
		private int xp = 0;
		
		String playerName;
		
		public InventoryData( Player p )
		{
			xp = p.getTotalExperience();
			loc = p.getLocation();
			armor = p.getInventory().getArmorContents();
			inv = p.getInventory().getContents();
			playerName = p.getName();
			
			p.getInventory().clear();
			p.setTotalExperience( 0 );
		}
		
		@SuppressWarnings("deprecation")
		public void restoreInventory()
		{
			Player p = Bukkit.getServer().getPlayer( playerName );
			p.getInventory().clear();
			
			p.setTotalExperience( xp );
			p.teleport( loc );
			p.getInventory().setContents( inv );
			p.getInventory().setArmorContents( armor );
		}
	
}
