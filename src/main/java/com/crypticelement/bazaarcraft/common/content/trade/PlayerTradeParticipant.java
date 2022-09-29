package com.crypticelement.bazaarcraft.common.content.trade;

import com.crypticelement.bazaarcraft.common.util.IXpHandler;
import com.crypticelement.bazaarcraft.common.util.PlayerXpWrapper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;
import org.jetbrains.annotations.NotNull;

public class PlayerTradeParticipant implements ITradeParticipant,
        IItemStackPaymentSource,
        IItemStackPaymentDestination,
        IXpPaymentSource,
        IXpPaymentDestination
{
    private final Player player;
    private IItemHandler paymentSourceItemHandler;
    private IItemHandler paymentDestinationItemHandler;
    private IXpHandler paymentSourceXpHandler;
    private IXpHandler paymentDestinationXpHandler;

    public PlayerTradeParticipant(@NotNull Player player) {
        this.player = player;
        paymentSourceItemHandler = paymentDestinationItemHandler = new PlayerMainInvWrapper(player.getInventory());
        paymentSourceXpHandler = paymentDestinationXpHandler = new PlayerXpWrapper(player);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPaymentSourceItemHandler(IItemHandler paymentSourceItemHandler) {
        this.paymentSourceItemHandler = paymentSourceItemHandler;
    }

    public void setPaymentDestinationItemHandler(IItemHandler paymentDestinationItemHandler) {
        this.paymentDestinationItemHandler = paymentDestinationItemHandler;
    }

    public void setPaymentSourceXpHandler(IXpHandler paymentSourceXpHandler) {
        this.paymentSourceXpHandler = paymentSourceXpHandler;
    }

    public void setPaymentDestinationXpHandler(IXpHandler paymentDestinationXpHandler) {
        this.paymentDestinationXpHandler = paymentDestinationXpHandler;
    }

    @Override
    public IItemHandler getSourceItemHandler() {
        return paymentSourceItemHandler;
    }

    @Override
    public IItemHandler getDestinationItemHandler() {
        return paymentDestinationItemHandler;
    }

    @Override
    public IXpHandler getSourceXpHandler() {
        return paymentSourceXpHandler;
    }

    @Override
    public IXpHandler getDestinationXpHandler() {
        return paymentDestinationXpHandler;
    }
}
