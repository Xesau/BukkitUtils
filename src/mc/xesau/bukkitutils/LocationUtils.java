package mc.xesau.bukkitutils;

import org.bukkit.Location;

public class LocationUtils {

	public static boolean isSame( Location loc1, Location loc2 )
	{
		return (
			loc1.getWorld().getName() == loc2.getWorld().getName() &&
		  	loc1.getX() == loc2.getX() &&
			loc1.getY() == loc2.getY() &&
			loc1.getZ() == loc2.getZ()
		);
	}
	
}
