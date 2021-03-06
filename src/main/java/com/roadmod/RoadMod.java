package com.roadmod;

import com.roadmod.blocks.RoadBlocks;
import com.roadmod.items.RoadItems;
import com.roadmod.proxy.IRoadProxy;
import com.roadmod.tabs.AsphaultTab;
import com.roadmod.tabs.SignTab;
import com.roadmod.tabs.MiscTab;
import com.roadmod.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION)
public class RoadMod
{
@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	
	public static IRoadProxy proxy;

    public static boolean isCarsAndDrivesLoaded;
    
    public static CreativeTabs AsphaultTab = new AsphaultTab("tabRoadAsphault");
    public static CreativeTabs SignTab = new SignTab("tabRoadSign");
    public static CreativeTabs MiscTab = new MiscTab("tabRoadMisc");
    
    public static DamageSource electricDamage = new DamageSource("electric").setDamageBypassesArmor();
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init();
    }
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.init();
    	RoadBlocks.init();
    	RoadBlocks.register();
    	RoadItems.init();
    	RoadItems.register();
    	isCarsAndDrivesLoaded = Loader.isModLoaded("carsanddrives");
   
    }

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{	
		proxy.init();
	}
}
