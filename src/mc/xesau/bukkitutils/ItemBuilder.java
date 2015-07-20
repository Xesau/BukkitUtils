package mc.xesau.bukkitutils;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

	private Material item;
	private byte damageValue;
	private int amount;
	private String displayName;
	private List<String> lore;
	private Map<Enchantment, Integer> enchantments;

	public ItemBuilder(Material item, int amount) {
		this.item = item;
		this.amount = amount;
	}

	public ItemBuilder(Material item, int amount, byte damageValue) {
		this.item = item;
		this.amount = amount;
		this.damageValue = damageValue;
	}

	public ItemStack getItem() {
		ItemStack tmp = new ItemStack(item, amount, damageValue);
		ItemMeta meta = tmp.getItemMeta();

		if (displayName != null)
			meta.setDisplayName(displayName);

		if (lore != null)
			meta.setLore(lore);

		if (enchantments != null)
			for (Enchantment e : enchantments.keySet()) {
				meta.addEnchant(e, enchantments.get(e), true);
			}

		tmp.setItemMeta(meta);

		return tmp;
	}

	public ItemBuilder setMaterial(Material item) {
		this.item = item;
		return this;
	}

	public Material getMaterial() {
		return item;
	}

	public int getAmount() {
		return amount;
	}

	public ItemBuilder setAmount(int amount) {
		this.amount = amount;
		return this;
	}

	public ItemBuilder setDamageValue(byte damageValue) {
		this.damageValue = damageValue;
		return this;
	}

	public byte getDamageValue() {
		return damageValue;
	}

	public ItemBuilder addEnchantment(Enchantment e, int level) {
		enchantments.put(e, level);
		return this;
	}

	public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
		for (Enchantment e : enchantments.keySet()) {
			this.enchantments.put(e, enchantments.get(e));
		}
		return this;
	}

	public Map<Enchantment, Integer> getEnchantments() {
		return enchantments;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public ItemBuilder setDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	public ItemBuilder setLore(List<String> lore) {
		this.lore = lore;
		return this;
	}

	public ItemBuilder addLoreLine(String text) {
		if (this.lore == null)
			this.lore = new ArrayList<String>();

		this.lore.add(text);
		return this;
	}

	public ItemBuilder addLoreLines(List<String> lore) {
		if (this.lore == null)
			this.lore = new ArrayList<String>();

		for (String line : lore) {
			this.lore.add(line);
		}
		return this;
	}

	public List<String> getLore() {
		return this.lore;
	}

}