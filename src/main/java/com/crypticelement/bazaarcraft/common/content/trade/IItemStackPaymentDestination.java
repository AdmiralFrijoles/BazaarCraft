package com.crypticelement.bazaarcraft.common.content.trade;

import net.minecraftforge.items.IItemHandlerModifiable;

public interface IItemStackPaymentDestination {
    IItemHandlerModifiable getDestinationItemHandler();
}