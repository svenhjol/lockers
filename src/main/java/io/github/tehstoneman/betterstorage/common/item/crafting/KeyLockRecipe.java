package io.github.tehstoneman.betterstorage.common.item.crafting;

import io.github.tehstoneman.betterstorage.api.lock.IKey;
import io.github.tehstoneman.betterstorage.common.item.BetterStorageItems;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;

public class KeyLockRecipe extends ShapedRecipe
{
	public KeyLockRecipe( ResourceLocation idIn )
	{
		super( idIn, "lock", 3, 3, NonNullList.from( Ingredient.EMPTY, Ingredient.EMPTY, Ingredient.fromItems( Items.GOLD_INGOT ), Ingredient.EMPTY,
				Ingredient.fromItems( Items.GOLD_INGOT ), Ingredient.fromItems( BetterStorageItems.KEY ), Ingredient.fromItems( Items.GOLD_INGOT ),
				Ingredient.fromItems( Items.GOLD_INGOT ), Ingredient.fromItems( Items.IRON_INGOT ), Ingredient.fromItems( Items.GOLD_INGOT ) ),
				new ItemStack( BetterStorageItems.LOCK ) );
	}

	@Override
	public ItemStack getCraftingResult( CraftingInventory inv )
	{
		final ItemStack outputStack = super.getCraftingResult( inv );

		if( !outputStack.isEmpty() )
		{
			final CompoundNBT tagCompound = outputStack.getOrCreateTag();

			for( int i = 0; i < inv.getSizeInventory(); ++i )
			{
				final ItemStack ingredientStack = inv.getStackInSlot( i );

				if( !ingredientStack.isEmpty() && ingredientStack.getItem() instanceof IKey )
					if( ingredientStack.hasTag() )
						tagCompound.merge( ingredientStack.getTag() );
			}

			outputStack.setTag( tagCompound );
		}

		return outputStack;
	}

	@Override
	public NonNullList< ItemStack > getRemainingItems( CraftingInventory inv )
	{
		final NonNullList< ItemStack > ret = NonNullList.withSize( inv.getSizeInventory(), ItemStack.EMPTY );

		for( int i = 0; i < ret.size(); ++i )
		{
			final ItemStack itemStack = inv.getStackInSlot( i );
			if( !itemStack.isEmpty() )
				if( itemStack.getItem() instanceof IKey )
					ret.set( i, itemStack.copy() );
				else
					ret.set( i, ForgeHooks.getContainerItem( itemStack ) );
		}

		return ret;
	}

	public static class Factory// implements IRecipeFactory
	{
		/*
		 * @Override
		 * public IRecipe parse( JsonContext context, JsonObject json )
		 * {
		 * final String group = JsonUtils.getString( json, "group", "" );
		 * final CraftingHelper.ShapedPrimer primer = parseShaped( context, json );
		 * final ItemStack result = CraftingHelper.getItemStack( JsonUtils.getJsonObject( json, "result" ), context );
		 *
		 * return new CopyKeyRecipe( group.isEmpty() ? null : new ResourceLocation( group ), result, primer );
		 * }
		 */

		/*
		 * public static CraftingHelper.ShapedPrimer parseShaped( final JsonContext context, final JsonObject json )
		 * {
		 * final Map< Character, Ingredient > ingredientMap = Maps.newHashMap();
		 * for( final Map.Entry< String, JsonElement > entry : JsonUtils.getJsonObject( json, "key" ).entrySet() )
		 * {
		 * if( entry.getKey().length() != 1 )
		 * throw new JsonSyntaxException( "Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only)." );
		 * if( " ".equals( entry.getKey() ) )
		 * throw new JsonSyntaxException( "Invalid key entry: ' ' is a reserved symbol." );
		 *
		 * ingredientMap.put( entry.getKey().toCharArray()[0], CraftingHelper.getIngredient( entry.getValue(), context ) );
		 * }
		 *
		 * ingredientMap.put( ' ', Ingredient.EMPTY );
		 *
		 * final JsonArray patternJ = JsonUtils.getJsonArray( json, "pattern" );
		 *
		 * if( patternJ.size() == 0 )
		 * throw new JsonSyntaxException( "Invalid pattern: empty pattern not allowed" );
		 *
		 * final String[] pattern = new String[patternJ.size()];
		 * for( int x = 0; x < pattern.length; ++x )
		 * {
		 * final String line = JsonUtils.getString( patternJ.get( x ), "pattern[" + x + "]" );
		 * if( x > 0 && pattern[0].length() != line.length() )
		 * throw new JsonSyntaxException( "Invalid pattern: each row must  be the same width" );
		 * pattern[x] = line;
		 * }
		 *
		 * final CraftingHelper.ShapedPrimer primer = new CraftingHelper.ShapedPrimer();
		 * primer.width = pattern[0].length();
		 * primer.height = pattern.length;
		 * primer.mirrored = JsonUtils.getBoolean( json, "mirrored", true );
		 * primer.input = NonNullList.withSize( primer.width * primer.height, Ingredient.EMPTY );
		 *
		 * final Set< Character > keys = Sets.newHashSet( ingredientMap.keySet() );
		 * keys.remove( ' ' );
		 *
		 * int index = 0;
		 * for( final String line : pattern )
		 * for( final char chr : line.toCharArray() )
		 * {
		 * final Ingredient ing = ingredientMap.get( chr );
		 * if( ing == null )
		 * throw new JsonSyntaxException( "Pattern references symbol '" + chr + "' but it's not defined in the key" );
		 * primer.input.set( index++, ing );
		 * keys.remove( chr );
		 * }
		 *
		 * if( !keys.isEmpty() )
		 * throw new JsonSyntaxException( "Key defines symbols that aren't used in pattern: " + keys );
		 *
		 * return primer;
		 * }
		 */
	}

	/*
	 * @Override
	 * public boolean isDynamic()
	 * {
	 * return true;
	 * }
	 */
}