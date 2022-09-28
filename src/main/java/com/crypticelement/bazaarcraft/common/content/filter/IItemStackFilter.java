package com.crypticelement.bazaarcraft.common.content.filter;

import net.minecraft.world.item.ItemStack;

public interface IItemStackFilter<FILTER extends IItemStackFilter<FILTER>> extends IFilter<FILTER> {
    ItemStack getItemStack();

    void setItemStack(ItemStack itemStack);

    @Override
    default boolean isValidFilter() {
        var itemStack = getItemStack();
        return itemStack != null && !itemStack.isEmpty();
    }
}
