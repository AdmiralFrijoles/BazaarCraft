package com.crypticelement.bazaarcraft.common.config;

import net.minecraftforge.fml.ModLoadingContext;

public class ModConfig {
    public static void register() {
        CommonConfig.register(ModLoadingContext.get());
    }
}
