package com.crypticelement.bazaarcraft.common.content.trade;

import com.crypticelement.bazaarcraft.common.content.filter.FilterBase;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;

public abstract class TradeItemFilter<FILTER extends TradeItemFilter<FILTER>> extends FilterBase<FILTER> {


    protected TradeItemFilter() {
    }

    protected TradeItemFilter(FILTER filter) {
    }

    public abstract boolean canFilter(ItemStack itemStack);

    @Override
    public void read(CompoundTag nbt) {
    }

    @Override
    public void read(FriendlyByteBuf data) {
    }
}
