package com.minimerce.builder;

import com.minimerce.domain.deal.option.DealOption;
import com.minimerce.domain.deal.option.item.DealOptionItem;
import com.minimerce.support.util.Yn;

import java.time.LocalDateTime;

/**
 * Created by gemini on 12/04/2017.
 */
public final class DealOptionItemBuilder {
    public DealOption option;
    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    private Long itemId;
    private Yn deleted = Yn.N;

    private DealOptionItemBuilder() {
    }

    public static DealOptionItemBuilder aDealOptionItem() {
        return new DealOptionItemBuilder();
    }

    public DealOptionItemBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public DealOptionItemBuilder withOption(DealOption option) {
        this.option = option;
        return this;
    }

    public DealOptionItemBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public DealOptionItemBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public DealOptionItemBuilder withItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public DealOptionItemBuilder withDeleted(Yn deleted) {
        this.deleted = deleted;
        return this;
    }

    public DealOptionItem build() {
        DealOptionItem dealOptionItem = new DealOptionItem();
        dealOptionItem.setId(id);
        dealOptionItem.setOption(option);
        dealOptionItem.setCreatedAt(createdAt);
        dealOptionItem.setUpdatedAt(updatedAt);
        dealOptionItem.setItemId(itemId);
        dealOptionItem.setDeleted(deleted);
        return dealOptionItem;
    }
}
