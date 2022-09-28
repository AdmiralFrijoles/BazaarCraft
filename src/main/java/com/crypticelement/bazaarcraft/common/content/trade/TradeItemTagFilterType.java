package com.crypticelement.bazaarcraft.common.content.trade;

import com.crypticelement.bazaarcraft.common.content.filter.FilterType;
import com.crypticelement.bazaarcraft.common.content.filter.IFilter;

public final class TradeItemTagFilterType extends FilterType {
    @Override
    public IFilter<?> create() {
        return new TradeItemTagFilter();
    }
}