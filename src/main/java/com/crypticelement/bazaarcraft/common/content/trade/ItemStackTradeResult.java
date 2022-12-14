package com.crypticelement.bazaarcraft.common.content.trade;

import com.crypticelement.bazaarcraft.BazaarCraft;
import com.crypticelement.bazaarcraft.common.content.trade.filter.TradeItemFilter;

public class ItemStackTradeResult extends ItemStackFilterTradeBase implements ITradeResult {
    public ItemStackTradeResult(TradeItemFilter<?> filter, int quantity) {
        super(filter, quantity);
    }

    protected TradeCheckResult tryProcess(ITradeBroker broker, boolean simulate) {
        if (!(broker.getSeller() instanceof IItemStackPaymentSource paymentSource))
            return TradeCheckResult.ERROR;
        if (!(broker.getBuyer() instanceof IItemStackPaymentDestination paymentDestination))
            return TradeCheckResult.ERROR;

        return tryProcess(paymentSource, paymentDestination, simulate);
    }

    @Override
    public TradeCheckResult canDispense(ITradeBroker broker) {
        return tryProcess(broker, true);
    }

    @Override
    public void dispense(ITradeBroker broker) {
        if (!canDispense(broker).isSuccess()) return;

        var result = tryProcess(broker, false);
        if (!result.isSuccess()) {
            BazaarCraft.LOGGER.error("Trade collection failed: " + result.getErrorId());
        }
    }
}