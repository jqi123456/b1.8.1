package net.minecraft.src;

import java.util.Iterator;
import java.util.List;

import org.magicwerk.brownies.collections.GapList;

public final class J_JsonArrayNodeBuilder implements J_JsonNodeBuilder {
	private final GapList elementBuilders = new GapList<>();

	public J_JsonArrayNodeBuilder withElement(J_JsonNodeBuilder var1) {
		this.elementBuilders.add(var1);
		return this;
	}

	public J_JsonRootNode build() {
		GapList var1 = new GapList<>();
		Iterator var2 = this.elementBuilders.iterator();

		while (var2.hasNext()) {
			J_JsonNodeBuilder var3 = (J_JsonNodeBuilder) var2.next();
			var1.add(var3.buildNode());
		}

		return J_JsonNodeFactories.func_27309_a(var1);
	}

	public J_JsonNode buildNode() {
		return this.build();
	}
}
