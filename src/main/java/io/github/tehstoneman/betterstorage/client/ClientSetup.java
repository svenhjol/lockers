package io.github.tehstoneman.betterstorage.client;

import io.github.tehstoneman.betterstorage.ModInfo;
import io.github.tehstoneman.betterstorage.client.gui.GuiCardboardBox;
import io.github.tehstoneman.betterstorage.client.gui.GuiCrate;
import io.github.tehstoneman.betterstorage.client.gui.GuiKeyring;
import io.github.tehstoneman.betterstorage.client.gui.GuiLocker;
import io.github.tehstoneman.betterstorage.client.gui.GuiReinforcedChest;
import io.github.tehstoneman.betterstorage.client.gui.GuiReinforcedLocker;
import io.github.tehstoneman.betterstorage.client.renderer.Resources;
import io.github.tehstoneman.betterstorage.client.renderer.TileEntityLockableDoorRenderer;
import io.github.tehstoneman.betterstorage.client.renderer.tileentity.TileEntityLockerRenderer;
import io.github.tehstoneman.betterstorage.client.renderer.tileentity.TileEntityReinforcedChestRenderer;
import io.github.tehstoneman.betterstorage.client.renderer.tileentity.TileEntityTankRenderer;
import io.github.tehstoneman.betterstorage.common.block.BetterStorageBlocks;
import io.github.tehstoneman.betterstorage.common.inventory.BetterStorageContainerTypes;
import io.github.tehstoneman.betterstorage.common.item.BetterStorageItems;
import io.github.tehstoneman.betterstorage.common.item.cardboard.CardboardColor;
import io.github.tehstoneman.betterstorage.common.item.locking.KeyColor;
import io.github.tehstoneman.betterstorage.common.tileentity.BetterStorageTileEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber( modid = ModInfo.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD )
public class ClientSetup
{
	@SubscribeEvent
	public static void init( final FMLClientSetupEvent event )
	{
		// Register GUIs
		ScreenManager.registerFactory( BetterStorageContainerTypes.CARDBOARD_BOX.get(), GuiCardboardBox::new );
		ScreenManager.registerFactory( BetterStorageContainerTypes.CRATE.get(), GuiCrate::new );
		ScreenManager.registerFactory( BetterStorageContainerTypes.KEYRING.get(), GuiKeyring::new );
		ScreenManager.registerFactory( BetterStorageContainerTypes.LOCKER.get(), GuiLocker::new );
		ScreenManager.registerFactory( BetterStorageContainerTypes.REINFORCED_CHEST.get(), GuiReinforcedChest::new );
		ScreenManager.registerFactory( BetterStorageContainerTypes.REINFORCED_LOCKER.get(), GuiReinforcedLocker::new );

		// Register Tile Entity Renderers
		ClientRegistry.bindTileEntityRenderer( BetterStorageTileEntityTypes.GLASS_TANK.get(), TileEntityTankRenderer::new );
		ClientRegistry.bindTileEntityRenderer( BetterStorageTileEntityTypes.LOCKABLE_DOOR.get(), TileEntityLockableDoorRenderer::new );
		ClientRegistry.bindTileEntityRenderer( BetterStorageTileEntityTypes.LOCKER.get(), TileEntityLockerRenderer::new );
		ClientRegistry.bindTileEntityRenderer( BetterStorageTileEntityTypes.REINFORCED_CHEST.get(), TileEntityReinforcedChestRenderer::new );
		ClientRegistry.bindTileEntityRenderer( BetterStorageTileEntityTypes.REINFORCED_LOCKER.get(), TileEntityLockerRenderer::new );

		Minecraft.getInstance().getItemColors().register( new KeyColor(), BetterStorageItems.KEY.get(), BetterStorageItems.LOCK.get() );
		Minecraft.getInstance().getItemColors().register( new CardboardColor(), BetterStorageItems.CARDBOARD_AXE.get(),
				BetterStorageItems.CARDBOARD_BOOTS.get(), BetterStorageItems.CARDBOARD_CHESTPLATE.get(), BetterStorageItems.CARDBOARD_HELMET.get(),
				BetterStorageItems.CARDBOARD_HOE.get(), BetterStorageItems.CARDBOARD_LEGGINGS.get(), BetterStorageItems.CARDBOARD_PICKAXE.get(),
				BetterStorageItems.CARDBOARD_SHOVEL.get(), BetterStorageItems.CARDBOARD_SWORD.get(), BetterStorageBlocks.CARDBOARD_BOX.get() );
		Minecraft.getInstance().getBlockColors().register( new CardboardColor(), BetterStorageBlocks.CARDBOARD_BOX.get() );

		RenderTypeLookup.setRenderLayer( BetterStorageBlocks.GLASS_TANK.get(), RenderType.cutout() );
	}

	@SubscribeEvent
	public static void onTextureStitch( TextureStitchEvent.Pre event )
	{
		if( !event.getMap().getBasePath().equals( PlayerContainer.LOCATION_BLOCKS_TEXTURE ) )
			return;

		event.addSprite( Resources.TEXTURE_CHEST_REINFORCED );
		event.addSprite( Resources.TEXTURE_CHEST_REINFORCED_DOUBLE );
		event.addSprite( Resources.TEXTURE_LOCKER_NORMAL );
		event.addSprite( Resources.TEXTURE_LOCKER_NORMAL_DOUBLE );
		event.addSprite( Resources.TEXTURE_LOCKER_REINFORCED );
		event.addSprite( Resources.TEXTURE_LOCKER_REINFORCED_DOUBLE );
		event.addSprite( Resources.TEXTURE_WHITE );
	}
}
