package com.crypticelement.bazaarcraft.common.content.trade;

import com.crypticelement.bazaarcraft.BazaarCraft;

public class XpTradeResult extends XpTradeBase implements ITradeResult {
    public XpTradeResult(int totalExperience) {
        super(totalExperience);
    }

    protected TradeCheckResult tryProcess(ITradeBroker broker, boolean simulate) {
        if (!(broker.getSeller() instanceof IXpPaymentSource paymentSource))
            return TradeCheckResult.ERROR;
        if (!(broker.getBuyer() instanceof IXpPaymentDestination paymentDestination))
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
