package com.crypticelement.bazaarcraft.common.content.trade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;

public abstract class XpTradeBase {
    private static final TradeCheckResult ERROR_SOURCE_NOT_ENOUGH_XP = new TradeCheckResult("trade.bazaarcraft.error.source_not_enough_xp");
    private static final TradeCheckResult ERROR_DESTINATION_XP_FULL = new TradeCheckResult("trade.bazaarcraft.error.destination_xp_full");


    private int totalExperience;

    protected XpTradeBase(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    protected TradeCheckResult tryProcess(IXpPaymentSource paymentSource,
                                          IXpPaymentDestination paymentDestination,
                                          boolean simulate) {

        if (totalExperience <= 0) return TradeCheckResult.SUCCESS;

        var buyerSourceXpHandler = paymentSource.getSourceXpHandler();
        if (buyerSourceXpHandler == null) return ERROR_SOURCE_NOT_ENOUGH_XP;
        var sellerDestinationXpHandler = paymentDestination.getDestinationXpHandler();
        if (sellerDestinationXpHandler == null) return ERROR_DESTINATION_XP_FULL;

        if (buyerSourceXpHandler.canTakePoints(totalExperience))
            return ERROR_SOURCE_NOT_ENOUGH_XP;
        if (sellerDestinationXpHandler.canGivePoints(totalExperience))
            return ERROR_DESTINATION_XP_FULL;

        if (!simulate) {
            buyerSourceXpHandler.takePoints(totalExperience);
            sellerDestinationXpHandler.givePoints(totalExperience);
        }

        return TradeCheckResult.SUCCESS;
    }

    public CompoundTag write(CompoundTag nbt) {
        nbt.putInt("totalExperience", totalExperience);
        return nbt;
    }

    public void read(CompoundTag nbt) {
        if (!nbt.contains("totalExperience", Tag.TAG_INT))
            totalExperience = 0;
        else
            totalExperience = nbt.getInt("totalExperience");
    }

    public void write(FriendlyByteBuf buffer) {
        buffer.writeInt(totalExperience);
    }

    public void read(FriendlyByteBuf data) {
        totalExperience = data.readInt();
    }
}
