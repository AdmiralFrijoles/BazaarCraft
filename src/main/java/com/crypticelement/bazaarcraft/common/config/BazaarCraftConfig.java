package com.crypticelement.bazaarcraft.common.config;

import net.minecraftforge.fml.ModLoadingContext;

public  class BazaarCraftConfig {
    public static void registerConfigs(ModLoadingContext modLoadingContext) {
        CommonConfig.register(modLoadingContext);
    }
}
