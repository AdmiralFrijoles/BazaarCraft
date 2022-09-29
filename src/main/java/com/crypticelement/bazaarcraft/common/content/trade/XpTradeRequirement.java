package com.crypticelement.bazaarcraft.common.content.trade;

import com.crypticelement.bazaarcraft.BazaarCraft;

public class XpTradeRequirement extends XpTradeBase implements ITradeRequirement {
    public XpTradeRequirement(int totalExperience) {
        super(totalExperience);
    }

    protected TradeCheckResult tryProcess(ITradeBroker broker, boolean simulate) {
        if (!(broker.getBuyer() instanceof IXpPaymentSource paymentSource))
            return TradeCheckResult.ERROR;
        if (!(broker.getSeller() instanceof IXpPaymentDestination paymentDestination))
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
