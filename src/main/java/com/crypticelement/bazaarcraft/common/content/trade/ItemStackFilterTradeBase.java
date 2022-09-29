package com.crypticelement.bazaarcraft.common.content.trade;

import com.crypticelement.bazaarcraft.BazaarCraft;
import com.crypticelement.bazaarcraft.common.content.filter.FilterBase;
import com.crypticelement.bazaarcraft.common.content.trade.filter.TradeItemFilter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.items.ItemHandlerHelper;

public abstract class ItemStackFilterTradeBase {
    private static final TradeCheckResult ERROR_MISSING_ITEMS = new TradeCheckResult("trade.bazaarcraft.error.missing_required_items");


    protected TradeItemFilter<?> filter;
    protected int quantity;

    protected ItemStackFilterTradeBase(TradeItemFilter<?> filter, int quantity) {
        this.filter = filter;
        this.quantity = quantity;
    }

    protected TradeCheckResult tryProcess(IItemStackPaymentSource paymentSource,
                                        IItemStackPaymentDestination paymentDestination,
                                        boolean simulate) {

        var sourceItemHandler = paymentSource.getSourceItemHandler();
        if (sourceItemHandler == null) return TradeCheckResult.ERROR;
        var destinationItemHandler = paymentDestination.getDestinationItemHandler();
        if (destinationItemHandler == null) return TradeCheckResult.ERROR;

        if (filter == null || !filter.isValidFilter()) return TradeCheckResult.ERROR;

        if (quantity <= 0) return TradeCheckResult.SUCCESS;

        int quantityToCollect = quantity;

        var numSlots = sourceItemHandler.getSlots();
        for (int slot = 0; slot < numSlots; slot++) {
            var itemStack = sourceItemHandler.getStackInSlot(slot);
            if (filter.canFilter(itemStack)) {
                var quantityToTake = Math.min(quantityToCollect, itemStack.getCount());
                var collectedItemStack = sourceItemHandler.extractItem(slot, quantityToTake, simulate);
                quantityToTake = collectedItemStack.getCount();

                collectedItemStack = ItemHandlerHelper.insertItemStacked(destinationItemHandler, collectedItemStack, simulate);
                if (!collectedItemStack.isEmpty()) // unable to insert all that was taken
                    quantityToCollect -= quantityToTake - collectedItemStack.getCount();
                else
                    quantityToCollect -= quantityToTake;

                if (quantityToCollect <= 0) {
                    return TradeCheckResult.SUCCESS;
                }
            }
        }

        return ERROR_MISSING_ITEMS;
    }

    public CompoundTag write(CompoundTag nbt) {
        nbt.put("filter", filter.write(new CompoundTag()));
        nbt.putInt("quantity", quantity);
        return nbt;
    }

    public void read(CompoundTag nbt) {
        try {
            this.filter = (TradeItemFilter<?>) FilterBase.readFromNBT(nbt.getCompound("filter"));
        }
        catch (Exception e) {
            BazaarCraft.LOGGER.error("Unable to load filter", e);
        }

        this.quantity = nbt.getInt("quantity");
    }

    public void write(FriendlyByteBuf buffer) {
        filter.write(buffer);
        buffer.writeInt(quantity);
    }

    public void read(FriendlyByteBuf data) {
        this.filter = (TradeItemFilter<?>) FilterBase.readFromPacket(data);
        this.quantity = data.readInt();
    }
}
