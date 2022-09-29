package com.crypticelement.bazaarcraft.common.content.trade.filter;

import com.crypticelement.bazaarcraft.common.content.filter.FilterType;
import com.crypticelement.bazaarcraft.common.content.filter.Filters;
import com.crypticelement.bazaarcraft.common.content.filter.ITagFilter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;


public class TradeItemTagFilter extends TradeItemFilter<TradeItemTagFilter> implements ITagFilter<TradeItemTagFilter> {
    private String tagName;

    public TradeItemTagFilter(String tagName) {
        this.tagName = tagName;
    }

    public TradeItemTagFilter() {
    }

    public TradeItemTagFilter(TradeItemTagFilter filter) {
        super(filter);
        tagName = filter.tagName;
    }

    @Override
    public boolean canFilter(ItemStack itemStack) {
        return itemStack.getTags().anyMatch(tag -> tag.location().toString().equals(tagName));
    }

    @Override
    public FilterType getFilterType() {
        return Filters.TRADE_ITEM_TAG_FILTER.get();
    }

    @Override
    public TradeItemTagFilter clone() {
        return new TradeItemTagFilter(this);
    }

    @Override
    public void read(CompoundTag nbt) {
        super.read(nbt);
        tagName = nbt.getString("tagName");
    }

    @Override
    public void read(FriendlyByteBuf data) {
        super.read(data);
        tagName = data.readUtf();
    }

    @Override
    public CompoundTag write(CompoundTag nbt) {
        super.write(nbt);
        nbt.putString("tagName", tagName);
        return nbt;
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        super.write(buffer);
        buffer.writeUtf(tagName);
    }

    @Override
    public int hashCode() {
        int code = 1;
        code = 31 * code + tagName.hashCode();
        return code;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TradeItemTagFilter filter && filter.tagName.equals(tagName);
    }

    @Override
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String getTagName() {
        return this.tagName;
    }
}
