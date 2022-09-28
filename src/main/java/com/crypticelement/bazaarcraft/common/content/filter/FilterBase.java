package com.crypticelement.bazaarcraft.common.content.filter;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public abstract class FilterBase<FILTER extends FilterBase<FILTER>> implements IFilter<FILTER> {
    @Nullable
    public static IFilter<?> readFromNBT(CompoundTag nbt) {
        if (!nbt.contains("type", CompoundTag.TAG_STRING))
            return null;

        var filterTypeId = nbt.getString("type");
        var filterType = Filters.get(new ResourceLocation(filterTypeId));
        if (filterType == null)
            return null;

        var filter = filterType.create();
        filter.read(nbt);

        return filter;
    }

    @Nullable
    public static IFilter<?> readFromPacket(FriendlyByteBuf data) {
        var filterTypeId = data.readUtf();
        var filterType = Filters.get(new ResourceLocation(filterTypeId));
        if (filterType == null)
            return null;

        var filter = filterType.create();
        filter.read(data);
        return filter;
    }

    @Override
    public abstract FILTER clone();

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public CompoundTag write(CompoundTag nbt) {
        var rl = getFilterType().getRegistryName();
        if (rl != null) throw new RuntimeException();

        nbt.putString("type", getFilterType().getRegistryName().toString());
        return nbt;
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        var rl = getFilterType().getRegistryName();
        if (rl != null) throw new RuntimeException();

        buffer.writeUtf(getFilterType().getRegistryName().toString());
    }
}
