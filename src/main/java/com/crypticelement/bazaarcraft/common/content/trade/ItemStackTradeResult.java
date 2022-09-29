package com.crypticelement.bazaarcraft.common.content.trade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;

public class ItemStackTradeResult implements ITradeResult {
    private final ItemStack result;

    public ItemStackTradeResult(ItemStack result) {
        this.result = result;
    }

    @Override
    public void dispense(ITradeBroker broker) {
        if (!(broker.getSeller() instanceof IItemStackPaymentSource sellerPaymentSource))
            return;
        if (!(broker.getBuyer() instanceof IItemStackPaymentDestination buyerPaymentDestination))
            return;

        var destinationItemHandler = buyerPaymentDestination.getDestinationItemHandler();
        if (destinationItemHandler == null) return;
        var sourceItemHandler = sellerPaymentSource.getSourceItemHandler();
        if (sourceItemHandler == null) return;

        var stack = result.copy();

        // TODO: Check that the output is not full
        // TODO: Take from source
        ItemHandlerHelper.insertItemStacked(destinationItemHandler, stack, false);
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
