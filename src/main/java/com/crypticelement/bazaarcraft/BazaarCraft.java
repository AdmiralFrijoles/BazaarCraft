package com.crypticelement.bazaarcraft;

import com.crypticelement.bazaarcraft.common.config.ModConfig;
import com.crypticelement.bazaarcraft.common.util.ModRegistries;
import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(BazaarCraft.MODID)
public class BazaarCraft {
    public static final String MODID = "bazaarcraft";

    public static final Logger LOGGER = LogUtils.getLogger();


    public BazaarCraft() {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModConfig.register();
        ModRegistries.register();

        modEventBus.addListener(BazaarCraft::setup);
        modEventBus.addListener(BazaarCraft::clientSetup);
    }

    public static void setup(final FMLCommonSetupEvent event) {
    }

    public static void clientSetup(final FMLClientSetupEvent event) {
    }
}