package com.crypticelement.bazaarcraft.common.content.trade;

import com.crypticelement.bazaarcraft.BazaarCraft;
import com.crypticelement.bazaarcraft.common.content.filter.FilterBase;
import com.crypticelement.bazaarcraft.common.content.trade.filter.TradeItemFilter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemStackTradeRequirement implements ITradeRequirement {
    private static final TradeRequirementResult ERROR_INVALID_FILTER = new TradeRequirementResult("trade.bazaarcraft.error.invalid_filter");
    private static final TradeRequirementResult ERROR_MISSING_ITEMS = new TradeRequirementResult("trade.bazaarcraft.error.missing_required_items");


    private TradeItemFilter<?> filter;
    private int quantity;

    public ItemStackTradeRequirement(TradeItemFilter<?> filter, int quantity) {
        this.filter = filter;
        this.quantity = quantity;
    }

    private TradeRequirementResult tryCollect(ITradeBroker broker, boolean simulate) {
        if (!(broker instanceof IItemStackInput itemStackInput))
            return TradeRequirementResult.ERROR;
        if (!(broker instanceof IItemStackOutput itemStackOutput))
            return TradeRequirementResult.ERROR;

        var inputItemHandler = itemStackInput.getInputItemHandler();
        if (inputItemHandler == null) return TradeRequirementResult.ERROR;
        var outputItemHandler = itemStackOutput.getOutputItemHandler();
        if (outputItemHandler == null) return TradeRequirementResult.ERROR;

        if (!filter.isValidFilter()) return ERROR_INVALID_FILTER;

        if (quantity <= 0) return TradeRequirementResult.SUCCESS;

        int quantityToCollect = quantity;

        var numSlots = inputItemHandler.getSlots();
        for (int slot = 0; slot < numSlots; slot++) {
            var itemStack = inputItemHandler.getStackInSlot(slot);
            if (filter.canFilter(itemStack)) {
                var quantityToTake = Math.min(quantityToCollect, itemStack.getCount());
                var collectedItemStack = inputItemHandler.extractItem(slot, quantityToTake, simulate);
                quantityToTake = collectedItemStack.getCount();

                collectedItemStack = ItemHandlerHelper.insertItemStacked(outputItemHandler, collectedItemStack, simulate);
                if (!collectedItemStack.isEmpty()) // unable to insert all that was taken
                    quantityToCollect -= quantityToTake - collectedItemStack.getCount();
                else
                    quantityToCollect -= quantityToTake;

                if (quantityToCollect <= 0) {
                    return TradeRequirementResult.SUCCESS;
                }
            }
        }

        return ERROR_MISSING_ITEMS;
    }

    //IItemHandlerModifiable
    @Override
    public TradeRequirementResult isSatisfied(ITradeBroker broker) {
        return tryCollect(broker, true);
    }

    @Override
    public void collect(ITradeBroker broker) {
        if (!isSatisfied(broker).isSuccess()) return;

        tryCollect(broker, false);
    }

    @Override
    public CompoundTag write(CompoundTag nbt) {
        nbt.put("filter", filter.write(new CompoundTag()));
        nbt.putInt("quantity", quantity);
        return nbt;
    }

    @Override
    public void read(CompoundTag nbt) {
        try {
            this.filter = (TradeItemFilter<?>) FilterBase.readFromNBT(nbt.getCompound("filter"));
        }
        catch (Exception e) {
            BazaarCraft.LOGGER.error("Unable to load filter", e);
        }

        this.quantity = nbt.getInt("quantity");
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        filter.write(buffer);
        buffer.writeInt(quantity);
    }

    @Override
    public void read(FriendlyByteBuf data) {
        this.filter = (TradeItemFilter<?>) FilterBase.readFromPacket(data);
        this.quantity = data.readInt();
    }
}
