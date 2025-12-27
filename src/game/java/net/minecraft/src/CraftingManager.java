package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

import com.carrotsearch.hppc.CharObjectHashMap;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

public class CraftingManager {
	private static final CraftingManager instance = new CraftingManager();
	private ObjectArrayList<IRecipe> recipes = new ObjectArrayList<>();
	private static CharObjectHashMap<ItemStack> temp;

	public static final CraftingManager getInstance() {
		return instance;
	}

	private CraftingManager() {
		(new RecipesTools()).addRecipes(this);
		(new RecipesWeapons()).addRecipes(this);
		(new RecipesIngots()).addRecipes(this);
		(new RecipesFood()).addRecipes(this);
		(new RecipesCrafting()).addRecipes(this);
		(new RecipesArmor()).addRecipes(this);
		(new RecipesDyes()).addRecipes(this);
		this.addRecipe(new ItemStack(Item.paper, 3), new Object[] { "###", '#', Item.reed });
		this.addRecipe(new ItemStack(Item.book, 1), new Object[] { "#", "#", "#", '#', Item.paper });
		this.addRecipe(new ItemStack(Block.fence, 2), new Object[] { "###", "###", '#', Item.stick });
		this.addRecipe(new ItemStack(Block.field_35277_bw, 1),
				new Object[] { "#W#", "#W#", '#', Item.stick, 'W', Block.planks });
		this.addRecipe(new ItemStack(Block.jukebox, 1),
				new Object[] { "###", "#X#", "###", '#', Block.planks, 'X', Item.diamond });
		this.addRecipe(new ItemStack(Block.music, 1),
				new Object[] { "###", "#X#", "###", '#', Block.planks, 'X', Item.redstone });
		this.addRecipe(new ItemStack(Block.bookShelf, 1),
				new Object[] { "###", "XXX", "###", '#', Block.planks, 'X', Item.book });
		this.addRecipe(new ItemStack(Block.blockSnow, 1), new Object[] { "##", "##", '#', Item.snowball });
		this.addRecipe(new ItemStack(Block.blockClay, 1), new Object[] { "##", "##", '#', Item.clay });
		this.addRecipe(new ItemStack(Block.brick, 1), new Object[] { "##", "##", '#', Item.brick });
		this.addRecipe(new ItemStack(Block.glowStone, 1), new Object[] { "##", "##", '#', Item.lightStoneDust });
		this.addRecipe(new ItemStack(Block.cloth, 1), new Object[] { "##", "##", '#', Item.silk });
		this.addRecipe(new ItemStack(Block.tnt, 1),
				new Object[] { "X#X", "#X#", "X#X", 'X', Item.gunpowder, '#', Block.sand });
		this.addRecipe(new ItemStack(Block.stairSingle, 3, 3), new Object[] { "###", '#', Block.cobblestone });
		this.addRecipe(new ItemStack(Block.stairSingle, 3, 0), new Object[] { "###", '#', Block.stone });
		this.addRecipe(new ItemStack(Block.stairSingle, 3, 1), new Object[] { "###", '#', Block.sandStone });
		this.addRecipe(new ItemStack(Block.stairSingle, 3, 2), new Object[] { "###", '#', Block.planks });
		this.addRecipe(new ItemStack(Block.stairSingle, 3, 4), new Object[] { "###", '#', Block.brick });
		this.addRecipe(new ItemStack(Block.stairSingle, 3, 5), new Object[] { "###", '#', Block.field_35285_bn });
		this.addRecipe(new ItemStack(Block.ladder, 2), new Object[] { "# #", "###", "# #", '#', Item.stick });
		this.addRecipe(new ItemStack(Item.doorWood, 1), new Object[] { "##", "##", "##", '#', Block.planks });
		this.addRecipe(new ItemStack(Block.trapdoor, 2), new Object[] { "###", "###", '#', Block.planks });
		this.addRecipe(new ItemStack(Item.doorSteel, 1), new Object[] { "##", "##", "##", '#', Item.ingotIron });
		this.addRecipe(new ItemStack(Item.sign, 1),
				new Object[] { "###", "###", " X ", '#', Block.planks, 'X', Item.stick });
		this.addRecipe(new ItemStack(Item.cake, 1),
				new Object[] { "AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', Item.egg });
		this.addRecipe(new ItemStack(Item.sugar, 1), new Object[] { "#", '#', Item.reed });
		this.addRecipe(new ItemStack(Block.planks, 4), new Object[] { "#", '#', Block.wood });
		this.addRecipe(new ItemStack(Item.stick, 4), new Object[] { "#", "#", '#', Block.planks });
		this.addRecipe(new ItemStack(Block.torchWood, 4), new Object[] { "X", "#", 'X', Item.coal, '#', Item.stick });
		this.addRecipe(new ItemStack(Block.torchWood, 4),
				new Object[] { "X", "#", 'X', new ItemStack(Item.coal, 1, 1), '#', Item.stick });
		this.addRecipe(new ItemStack(Item.bowlEmpty, 4), new Object[] { "# #", " # ", '#', Block.planks });
		this.addRecipe(new ItemStack(Block.rail, 16),
				new Object[] { "X X", "X#X", "X X", 'X', Item.ingotIron, '#', Item.stick });
		this.addRecipe(new ItemStack(Block.railPowered, 6),
				new Object[] { "X X", "X#X", "XRX", 'X', Item.ingotGold, 'R', Item.redstone, '#', Item.stick });
		this.addRecipe(new ItemStack(Block.railDetector, 6),
				new Object[] { "X X", "X#X", "XRX", 'X', Item.ingotIron, 'R', Item.redstone, '#', Block.pressurePlateStone });
		this.addRecipe(new ItemStack(Item.minecartEmpty, 1), new Object[] { "# #", "###", '#', Item.ingotIron });
		this.addRecipe(new ItemStack(Block.pumpkinLantern, 1),
				new Object[] { "A", "B", 'A', Block.pumpkin, 'B', Block.torchWood });
		this.addRecipe(new ItemStack(Item.minecartCrate, 1),
				new Object[] { "A", "B", 'A', Block.chest, 'B', Item.minecartEmpty });
		this.addRecipe(new ItemStack(Item.minecartPowered, 1),
				new Object[] { "A", "B", 'A', Block.stoneOvenIdle, 'B', Item.minecartEmpty });
		this.addRecipe(new ItemStack(Item.boat, 1), new Object[] { "# #", "###", '#', Block.planks });
		this.addRecipe(new ItemStack(Item.bucketEmpty, 1), new Object[] { "# #", " # ", '#', Item.ingotIron });
		this.addRecipe(new ItemStack(Item.flintAndSteel, 1),
				new Object[] { "A ", " B", 'A', Item.ingotIron, 'B', Item.flint });
		this.addRecipe(new ItemStack(Item.bread, 1), new Object[] { "###", '#', Item.wheat });
		this.addRecipe(new ItemStack(Block.stairCompactPlanks, 4), new Object[] { "#  ", "## ", "###", '#', Block.planks });
		this.addRecipe(new ItemStack(Item.fishingRod, 1),
				new Object[] { "  #", " #X", "# X", '#', Item.stick, 'X', Item.silk });
		this.addRecipe(new ItemStack(Block.stairCompactCobblestone, 4),
				new Object[] { "#  ", "## ", "###", '#', Block.cobblestone });
		this.addRecipe(new ItemStack(Block.field_35280_bx, 4), new Object[] { "#  ", "## ", "###", '#', Block.brick });
		this.addRecipe(new ItemStack(Block.field_35279_by, 4),
				new Object[] { "#  ", "## ", "###", '#', Block.field_35285_bn });
		this.addRecipe(new ItemStack(Item.painting, 1),
				new Object[] { "###", "#X#", "###", '#', Item.stick, 'X', Block.cloth });
		this.addRecipe(new ItemStack(Item.appleGold, 1),
				new Object[] { "###", "#X#", "###", '#', Block.blockGold, 'X', Item.appleRed });
		this.addRecipe(new ItemStack(Block.lever, 1), new Object[] { "X", "#", '#', Block.cobblestone, 'X', Item.stick });
		this.addRecipe(new ItemStack(Block.torchRedstoneActive, 1),
				new Object[] { "X", "#", '#', Item.stick, 'X', Item.redstone });
		this.addRecipe(new ItemStack(Item.redstoneRepeater, 1),
				new Object[] { "#X#", "III", '#', Block.torchRedstoneActive, 'X', Item.redstone, 'I', Block.stone });
		this.addRecipe(new ItemStack(Item.pocketSundial, 1),
				new Object[] { " # ", "#X#", " # ", '#', Item.ingotGold, 'X', Item.redstone });
		this.addRecipe(new ItemStack(Item.compass, 1),
				new Object[] { " # ", "#X#", " # ", '#', Item.ingotIron, 'X', Item.redstone });
		this.addRecipe(new ItemStack(Item.map, 1),
				new Object[] { "###", "#X#", "###", '#', Item.paper, 'X', Item.compass });
		this.addRecipe(new ItemStack(Block.button, 1), new Object[] { "#", "#", '#', Block.stone });
		this.addRecipe(new ItemStack(Block.pressurePlateStone, 1), new Object[] { "##", '#', Block.stone });
		this.addRecipe(new ItemStack(Block.pressurePlatePlanks, 1), new Object[] { "##", '#', Block.planks });
		this.addRecipe(new ItemStack(Block.dispenser, 1),
				new Object[] { "###", "#X#", "#R#", '#', Block.cobblestone, 'X', Item.bow, 'R', Item.redstone });
		this.addRecipe(new ItemStack(Block.pistonBase, 1), new Object[] { "TTT", "#X#", "#R#", '#', Block.cobblestone, 'X',
				Item.ingotIron, 'R', Item.redstone, 'T', Block.planks });
		this.addRecipe(new ItemStack(Block.pistonStickyBase, 1),
				new Object[] { "S", "P", 'S', Item.slimeBall, 'P', Block.pistonBase });
		this.addRecipe(new ItemStack(Item.bed, 1), new Object[] { "###", "XXX", '#', Block.cloth, 'X', Block.planks });
		RecipeSorter.setCraftingManager(this);
		this.recipes.sort(RecipeSorter.instance);
		System.out.println(this.recipes.size() + " recipes");
	}

	void addRecipe(ItemStack var1, Object... var2) {
		String var3 = "";
		int var4 = 0;
		int var5 = 0;
		int var6 = 0;
		if (var2[var4] instanceof String[]) {
			String[] var11 = (String[]) ((String[]) var2[var4++]);

			for (int var8 = 0; var8 < var11.length; ++var8) {
				String var9 = var11[var8];
				++var6;
				var5 = var9.length();
				var3 = var3 + var9;
			}
		} else {
			while (var2[var4] instanceof String) {
				String var7 = (String) var2[var4++];
				++var6;
				var5 = var7.length();
				var3 = var3 + var7;
			}
		}

		if (temp == null)
			temp = new CharObjectHashMap<>();
		if (!temp.isEmpty())
			temp.clear();
		for (; var4 < var2.length; var4 += 2) {
			Character var13 = (Character) var2[var4];
			ItemStack var15 = null;
			if (var2[var4 + 1] instanceof Item) {
				var15 = new ItemStack((Item) var2[var4 + 1]);
			} else if (var2[var4 + 1] instanceof Block) {
				var15 = new ItemStack((Block) var2[var4 + 1], 1, -1);
			} else if (var2[var4 + 1] instanceof ItemStack) {
				var15 = (ItemStack) var2[var4 + 1];
			}

			temp.put(var13, var15);
		}

		ItemStack[] var14 = new ItemStack[var5 * var6];

		for (int var16 = 0; var16 < var5 * var6; ++var16) {
			char var10 = var3.charAt(var16);
			if (temp.containsKey(Character.valueOf(var10))) {
				var14[var16] = ((ItemStack) temp.get(Character.valueOf(var10))).copy();
			} else {
				var14[var16] = null;
			}
		}

		this.recipes.add(new ShapedRecipes(var5, var6, var14, var1));
	}

	void addShapelessRecipe(ItemStack var1, Object... var2) {
		ArrayList var3 = new ArrayList<>();
		Object[] var4 = var2;
		int var5 = var2.length;

		for (int var6 = 0; var6 < var5; ++var6) {
			Object var7 = var4[var6];
			if (var7 instanceof ItemStack) {
				var3.add(((ItemStack) var7).copy());
			} else if (var7 instanceof Item) {
				var3.add(new ItemStack((Item) var7));
			} else {
				if (!(var7 instanceof Block)) {
					throw new RuntimeException("Invalid shapeless recipe!");
				}

				var3.add(new ItemStack((Block) var7));
			}
		}

		this.recipes.add(new ShapelessRecipes(var1, var3));
	}

	public ItemStack findMatchingRecipe(InventoryCrafting var1) {
		for (int var2 = 0; var2 < this.recipes.size(); ++var2) {
			IRecipe var3 = (IRecipe) this.recipes.get(var2);
			if (var3.matches(var1)) {
				return var3.getCraftingResult(var1);
			}
		}

		return null;
	}

	public List getRecipeList() {
		return this.recipes;
	}
}
