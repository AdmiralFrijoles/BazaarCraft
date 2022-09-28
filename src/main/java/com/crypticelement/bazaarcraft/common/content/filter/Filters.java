package com.crypticelement.bazaarcraft.common.content.filter;

import com.crypticelement.bazaarcraft.BazaarCraft;
import com.crypticelement.bazaarcraft.common.content.trade.TradeItemTagFilterType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Filters {
    public static final DeferredRegister<FilterType> FILTER_TYPES = DeferredRegister.create(new ResourceLocation(BazaarCraft.MODID, "filter_types"), BazaarCraft.MODID);

    private static final Supplier<IForgeRegistry<FilterType>> REGISTRY = FILTER_TYPES.makeRegistry(FilterType.class, RegistryBuilder::new);


    public static final RegistryObject<FilterType> TRADE_ITEM_TAG_FILTER = FILTER_TYPES.register("TradeItemTagFilterType", TradeItemTagFilterType::new);


    public static FilterType get(ResourceLocation resourceLocation) {
        return REGISTRY.get().getValue(resourceLocation);
    }

    public static void init() {
        FILTER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
