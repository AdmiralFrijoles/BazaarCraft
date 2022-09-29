package com.crypticelement.bazaarcraft.common.content.trade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

import java.util.ArrayList;
import java.util.List;

public abstract class TradeOfferBase implements ITradeOffer {
    protected final List<ITradeRequirement> requirements = new ArrayList<>();
    protected final List<ITradeResult> results = new ArrayList<>();

    @Override
    public TradeCheckResult isSatisfied(ITradeBroker broker) {
        for (var requirement : requirements) {
            var result = requirement.isSatisfied(broker);
            if (!result.isSuccess())
                return result;
        }

        return TradeCheckResult.SUCCESS;
    }

    @Override
    public void accept(ITradeBroker broker) {
        if (!isSatisfied(broker).isSuccess()) return;
        collect(broker);
        dispense(broker);
    }

    protected void collect(ITradeBroker broker) {
        for (var requirement : requirements) {
            requirement.collect(broker);
        }
    }

    protected void dispense(ITradeBroker broker) {
        for (var result : results) {
            result.dispense(broker);
        }
    }

    @Override
    public CompoundTag write(CompoundTag nbt) {
        // TODO
        return null;
    }

    @Override
    public void read(CompoundTag nbt) {
        // TODO
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        // TODO
    }

    @Override
    public void read(FriendlyByteBuf data) {
        // TODO
    }
}
