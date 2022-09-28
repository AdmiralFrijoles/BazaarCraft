package com.crypticelement.bazaarcraft;

import com.crypticelement.bazaarcraft.common.config.BazaarCraftConfig;
import com.crypticelement.bazaarcraft.common.content.filter.Filters;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(BazaarCraft.MODID)
public class BazaarCraft {
    public static final String MODID = "bazaarcraft";

    public static final Logger LOGGER = LogUtils.getLogger();

    public BazaarCraft() {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        var forgeEventBus = MinecraftForge.EVENT_BUS;

        BazaarCraftConfig.registerConfigs(ModLoadingContext.get());

        modEventBus.addListener(BazaarCraft::init);

        Filters.init();
    }

    public static void init(final FMLCommonSetupEvent event) {

    }
}