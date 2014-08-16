package mc.xesau.bukkitutils.location;

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
	
	public static boolean isIn( Location check, Location point1, Location point2 )
	{
		return (
			check.getWorld().getName() == point1.getWorld().getName() &&
			check.getWorld().getName() == point2.getWorld().getName() &&
		
			check.getX() >= Math.min( point1.getX(), point2.getX() ) &&
			Math.max( point1.getX(), point2.getX() ) >= check.getX() &&
			
			check.getY() >= Math.min( point1.getY(), point2.getY() ) &&
			Math.max( point1.getY(), point2.getY() ) >= check.getY() &&
			
			check.getZ() >= Math.min( point1.getZ(), point2.getZ() ) &&
			Math.max( point1.getZ(), point2.getZ() ) >= check.getZ()
		);
	}
	
}
