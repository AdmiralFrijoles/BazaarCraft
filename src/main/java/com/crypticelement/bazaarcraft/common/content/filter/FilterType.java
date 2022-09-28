package com.crypticelement.bazaarcraft.common.content.filter;

import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class FilterType extends ForgeRegistryEntry<FilterType> {

    public abstract IFilter<?> create();
}
