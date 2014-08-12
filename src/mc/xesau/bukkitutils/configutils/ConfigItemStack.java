package mc.xesau.bukkitutils.configutils;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ConfigItemStack {

	private ItemStack stack;
	
	public ConfigItemStack( ConfigurationSection ref )
	{
		ItemStack temp = new ItemStack(
			Material.getMaterial( ref.getString( "type" ) ),
			Short.parseShort( ref.getString( "amount" ) ),
			Byte.parseByte( ref.getString( "damage" ) )
		);
		
		ItemMeta tempMeta = temp.getItemMeta();
		
		tempMeta.setDisplayName( ref.getString( "displayname" ) );
		tempMeta.setLore( ref.getStringList( "lore" ) );
		
		ConfigurationSection enchantments = ref.getConfigurationSection( "enchantments" );
		
		for( String enchantment : enchantments.getKeys( false ) )
		{
			tempMeta.addEnchant( Enchantment.getByName( enchantment ), enchantments.getInt( enchantment ), true );
		}
		
		temp.setItemMeta( tempMeta );
		stack = temp;
	}

	public ConfigItemStack( ItemStack stack )
	{
		this.stack = stack;
	}
	
	public ItemStack getItemStack()
	{
		return stack;
	}
	
	public ConfigItemStack setItemStack( ItemStack stack )
	{
		this.stack = stack;
		return this;
	}
	
	public HashMap< String, Object > getConfigurationMap()
	{
		HashMap< String, Object > itemMap = new HashMap< String, Object >();
		itemMap.put( "type",  stack.getType().toString() );
		itemMap.put( "damage", stack.getDurability() );
		itemMap.put( "amount", stack.getAmount() );

		ItemMeta meta = stack.getItemMeta();
		
		HashMap< String, Object > metaMap = new HashMap< String, Object >();
		metaMap.put( "displayname", meta.getDisplayName() );
		metaMap.put( "lore", meta.getLore() );
		metaMap.put( "enchantments", meta.getEnchants() );
		
		itemMap.put( "metadata", metaMap );
		
		return itemMap;
	}
	
}
