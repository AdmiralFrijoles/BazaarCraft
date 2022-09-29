package com.crypticelement.bazaarcraft.common.content.trade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public interface ITradeOffer {
    /**
     * @return The number of times the trade could be performed
     */
    int getStock();

    /**
     * @return True if the trade cannot be performed due to stock limitatons
     */
    default boolean isOutOfStock() {
        return getStock() <= 0;
    }

    /**
     * @return True if the trade can be completed
     */
    TradeCheckResult isSatisfied(ITradeBroker broker);

    /**
     * Accept the trade
     */
    void accept(ITradeBroker broker);

    CompoundTag write(CompoundTag nbt);

    void read(CompoundTag nbt);

    void write(FriendlyByteBuf buffer);

    void read(FriendlyByteBuf data);
}
