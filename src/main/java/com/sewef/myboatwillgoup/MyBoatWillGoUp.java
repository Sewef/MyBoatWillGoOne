package com.sewef.myboatwillgoup;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = MyBoatWillGoUp.MODID, name = MyBoatWillGoUp.NAME, version = MyBoatWillGoUp.VERSION)
public class MyBoatWillGoUp
{
    public static final String MODID = "myboatwillgoup";
    public static final String NAME = "My Boat Will Go Up";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("Near, far, wherever you are, I believe that the boat does go up.");
        MinecraftForge.EVENT_BUS.register(new EntityBoatTicking());
    }
}
