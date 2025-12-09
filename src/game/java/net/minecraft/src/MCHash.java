package net.minecraft.src;

import java.util.HashSet;
import java.util.Set;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public class MCHash {
	private Int2ObjectOpenHashMap<Object> slots = new Int2ObjectOpenHashMap<>(16, 0.75F);
	private Set field_35861_f = new HashSet();

	public Object lookup(int var1) {
		return slots.get(var1);
	}

	public boolean func_35858_b(int var1) {
		return this.lookup(var1) != null;
	}

	public void addKey(int var1, Object var2) {
		this.field_35861_f.add(Integer.valueOf(var1));
		this.slots.put(var1, var2);
	}

	public Object removeObject(int var1) {
		this.field_35861_f.remove(Integer.valueOf(var1));
		return this.slots.remove(var1);
	}

	public void clearMap() {
		this.slots.clear();
	}

	public Set func_35860_b() {
		return this.field_35861_f;
	}
}
