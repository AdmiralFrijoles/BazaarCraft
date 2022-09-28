package com.crypticelement.bazaarcraft.common.content.filter;

public interface ITagFilter<FILTER extends ITagFilter<FILTER>> extends IFilter<FILTER> {
    void setTagName(String tagName);

    String getTagName();

    @Override
    default boolean isValidFilter() {
        var tagName = getTagName();
        return tagName != null && !tagName.isEmpty();
    }
}
