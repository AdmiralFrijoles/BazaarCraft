package com.crypticelement.bazaarcraft.common.content.trade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public interface ITradeRequirement {
    TradeCheckResult isSatisfied(ITradeBroker broker);

    void collect(ITradeBroker broker);

    CompoundTag write(CompoundTag nbt);

    void read(CompoundTag nbt);

    void write(FriendlyByteBuf buffer);

    void read(FriendlyByteBuf data);
}
