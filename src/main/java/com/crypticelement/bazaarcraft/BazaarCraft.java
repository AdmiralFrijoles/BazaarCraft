package com.crypticelement.bazaarcraft;

import com.crypticelement.bazaarcraft.common.config.BazaarCraftConfig;
import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(BazaarCraft.MODID)
public class BazaarCraft {
    public static final String MODID = "bazaarcraft";

    public static final Logger LOGGER = LogUtils.getLogger();

    public BazaarCraft() {
        BazaarCraftConfig.registerConfigs(ModLoadingContext.get());
    }
}