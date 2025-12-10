package net.minecraft.src;

public class RecipesCrafting {
	public void addRecipes(CraftingManager var1) {
		var1.addRecipe(new ItemStack(Block.chest), new Object[]{"###", "# #", "###", '#', Block.planks});
		var1.addRecipe(new ItemStack(Block.stoneOvenIdle), new Object[]{"###", "# #", "###", '#', Block.cobblestone});
		var1.addRecipe(new ItemStack(Block.workbench), new Object[]{"##", "##", '#', Block.planks});
		var1.addRecipe(new ItemStack(Block.sandStone), new Object[]{"##", "##", '#', Block.sand});
		var1.addRecipe(new ItemStack(Block.field_35285_bn, 4), new Object[]{"##", "##", '#', Block.stone});
		var1.addRecipe(new ItemStack(Block.field_35288_bq, 16), new Object[]{"###", "###", '#', Item.ingotIron});
		var1.addRecipe(new ItemStack(Block.field_35282_br, 16), new Object[]{"###", "###", '#', Block.glass});
	}
}
