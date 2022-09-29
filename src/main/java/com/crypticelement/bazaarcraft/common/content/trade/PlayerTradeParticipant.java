package com.crypticelement.bazaarcraft.common.content.trade;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;

public class PlayerTradeParticipant implements ITradeParticipant, IItemStackPaymentSource, IItemStackPaymentDestination {
    private final Player player;
    private IItemHandlerModifiable paymentSourceItemHandler;
    private IItemHandlerModifiable paymentDestinationItemHandler;

    public PlayerTradeParticipant(Player player) {
        this.player = player;
        paymentSourceItemHandler = new PlayerMainInvWrapper(player.getInventory());
        paymentDestinationItemHandler = new PlayerMainInvWrapper(player.getInventory());
    }

    public void setPaymentSourceItemHandler(IItemHandlerModifiable paymentSourceItemHandler) {
        this.paymentSourceItemHandler = paymentSourceItemHandler;
    }

    public void setPaymentDestinationItemHandler(IItemHandlerModifiable paymentDestinationItemHandler) {
        this.paymentDestinationItemHandler = paymentDestinationItemHandler;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public IItemHandlerModifiable getSourceItemHandler() {
        return paymentSourceItemHandler;
    }

    @Override
    public IItemHandlerModifiable getDestinationItemHandler() {
        return paymentDestinationItemHandler;
    }
}
