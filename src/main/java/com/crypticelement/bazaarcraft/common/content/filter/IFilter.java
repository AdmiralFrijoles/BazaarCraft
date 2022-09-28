package com.crypticelement.bazaarcraft.common.content.filter;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public interface IFilter<FILTER extends IFilter<FILTER>> {
    FilterType getFilterType();

    FILTER clone();

    CompoundTag write(CompoundTag nbt);

    void read(CompoundTag nbt);

    void write(FriendlyByteBuf buffer);

    void read(FriendlyByteBuf data);

    boolean isValidFilter();
}
