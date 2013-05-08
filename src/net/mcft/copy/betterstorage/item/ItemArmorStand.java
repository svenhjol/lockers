package net.mcft.copy.betterstorage.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemArmorStand extends ItemBlock {
	
	public ItemArmorStand(int id) {
		super(id);
		setMaxStackSize(1);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player,
	                         World world, int x, int y, int z, int side,
	                         float hitX, float hitY, float hitZ) {
		
		if (stack.stackSize == 0) return false;
		
		Block blockClicked = Block.blocksList[world.getBlockId(x, y, z)];
		
		// If the block clicked is air or snow,
		// don't change the target coordinates, but set the side to 1 (top).
		if ((blockClicked == null) ||
		    (blockClicked == Block.snow)) side = 1;
		// If the block clicked is not replaceable,
		// adjust the coordinates depending on the side clicked.
		else if ((blockClicked != Block.vine) &&
		         (blockClicked != Block.tallGrass) &&
		         (blockClicked != Block.deadBush) &&
		         !blockClicked.isBlockReplaceable(world, x, y, z)) {
			switch (side) {
				case 0: y--; break;
				case 1: y++; break;
				case 2: z--; break;
				case 3: z++; break;
				case 4: x--; break;
				case 5: x++; break;
			}
		}
		
		// Return false if there's not enough world height left.
		if (y >= world.getHeight() - 2) return false;
		
		Block blockTop = Block.blocksList[world.getBlockId(x, y + 1, z)];
		
		// Return false if the block above isn't replaceable.
		if ((blockTop != null) && (blockTop != Block.vine) &&
		    !blockTop.isBlockReplaceable(world, x, y + 1, z)) return false;
		
		// Return false if the player can't edit any of the
		// two blocks the armor stand would occupy.
		if (!player.canPlayerEdit(x, y, z, side, stack) ||
			!player.canPlayerEdit(x, y + 1, z, side, stack)) return false;
		
		// Return false if there's an entity blocking the placement.
		if (!world.canPlaceEntityOnSide(getBlockID(), x, y, z, false, side, player, stack)) return false;
		
		Block block = Block.blocksList[getBlockID()];
		
		// Actually place the block in the world,
		// play place sound and decrease stack size if successful.
		if (placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, 0)) {
			String sound = block.stepSound.getPlaceSound();
			float volume = (block.stepSound.getVolume() + 1.0F) / 2.0F;
			float pitch = block.stepSound.getPitch() * 0.8F;
			world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5F, sound, volume, pitch);
			stack.stackSize--;
		}
		
		return true;
		
	}
	
}
