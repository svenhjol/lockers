package io.github.tehstoneman.betterstorage.common.item.cardboard;

import io.github.tehstoneman.betterstorage.api.ICardboardItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.nbt.CompoundNBT;

public class ItemCardboardHoe extends HoeItem implements ICardboardItem
{
	public ItemCardboardHoe()
	{
		super( ItemTier.WOOD, -3.0F, new Item.Properties().group( ItemGroup.TOOLS ) );
	}

	/*
	 * public ItemCardboardHoe()
	 * {
	 * super( ItemCardboardSheet.toolMaterial );
	 * this.name = "cardboard_hoe";
	 * }
	 */

	/*
	 * public void register()
	 * {
	 * setUnlocalizedName( ModInfo.modId + "." + name );
	 * setRegistryName( name );
	 * //GameRegistry.register( this );
	 * }
	 */

	/*
	 * @SideOnly( Side.CLIENT )
	 * public void registerItemModels()
	 * {
	 * ModelLoader.setCustomModelResourceLocation( this, 0, new ModelResourceLocation( getRegistryName(), "inventory" ) );
	 * }
	 */

	// Makes sure cardboard tools don't get destroyed,
	// and are ineffective when durability is at 0.
	/*
	 * @Override
	 * public boolean canHarvestBlock( IBlockState block, ItemStack stack )
	 * {
	 * return ItemCardboardSheet.canHarvestBlock( stack, super.canHarvestBlock( block, stack ) );
	 * }
	 */

	/*
	 * @Override
	 * public boolean onLeftClickEntity( ItemStack stack, EntityPlayer player, Entity entity )
	 * {
	 * return !ItemCardboardSheet.isEffective( stack );
	 * }
	 */

	/*
	 * @Override
	 * public boolean onBlockDestroyed( ItemStack stack, World world, IBlockState block, BlockPos pos, EntityLivingBase entity )
	 * {
	 * return block.getBlockHardness( world, pos ) > 0 ? ItemCardboardSheet.damageItem( stack, 1, entity ) : true;
	 * }
	 */

	/*
	 * @Override
	 * public boolean hitEntity( ItemStack stack, EntityLivingBase target, EntityLivingBase player )
	 * {
	 * return ItemCardboardSheet.damageItem( stack, 1, player );
	 * }
	 */

	// Cardboard items
	@Override
	public boolean canDye( ItemStack stack )
	{
		return true;
	}

	@Override
	public int getColor( ItemStack itemstack )
	{
		if( hasColor( itemstack ) )
		{
			final CompoundNBT compound = itemstack.getTag();
			return compound.getInt( "color" );
		}
		return 0x705030;
	}

	@Override
	public boolean hasColor( ItemStack itemstack )
	{
		if( itemstack.hasTag() )
		{
			final CompoundNBT compound = itemstack.getTag();
			return compound.contains( "color" );
		}
		return false;
	}

	@Override
	public void setColor( ItemStack itemstack, int colorRGB )
	{
		final CompoundNBT compound = itemstack.getOrCreateTag();
		compound.putInt( "color", colorRGB );
		itemstack.setTag( compound );
	}
}
