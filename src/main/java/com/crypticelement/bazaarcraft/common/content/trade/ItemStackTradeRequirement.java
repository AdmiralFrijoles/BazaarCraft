package com.crypticelement.bazaarcraft.common.content.trade;

import com.crypticelement.bazaarcraft.BazaarCraft;
import com.crypticelement.bazaarcraft.common.content.trade.filter.TradeItemFilter;

public class ItemStackTradeRequirement extends ItemStackFilterTradeBase implements ITradeRequirement {
    public ItemStackTradeRequirement(TradeItemFilter<?> filter, int quantity) {
        super(filter, quantity);
    }

    protected TradeCheckResult tryProcess(ITradeBroker broker, boolean simulate) {
        if (!(broker.getBuyer() instanceof IItemStackPaymentSource paymentSource))
            return TradeCheckResult.ERROR;
        if (!(broker.getSeller() instanceof IItemStackPaymentDestination paymentDestination))
            return TradeCheckResult.ERROR;

        return tryProcess(paymentSource, paymentDestination, simulate);
    }

    @Override
    public TradeCheckResult isSatisfied(ITradeBroker broker) {
        return tryProcess(broker, true);
    }

    @Override
    public void collect(ITradeBroker broker) {
        if (!isSatisfied(broker).isSuccess()) return;

        var result = tryProcess(broker, false);
        if (!result.isSuccess()) {
            BazaarCraft.LOGGER.error("Trade collection failed: " + result.getErrorId());
        }
    }
}