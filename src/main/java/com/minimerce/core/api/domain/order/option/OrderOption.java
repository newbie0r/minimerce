package com.minimerce.core.api.domain.order.option;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.minimerce.core.api.domain.BaseDomain;
import com.minimerce.core.api.domain.deal.Deal;
import com.minimerce.core.api.domain.deal.option.DealOption;
import com.minimerce.core.api.domain.order.Order;
import com.minimerce.core.api.domain.order.item.OrderItem;
import com.minimerce.core.api.support.object.order.CancelStatus;
import com.minimerce.core.api.support.object.order.OrderStatus;
import com.minimerce.core.api.support.object.type.DealType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by gemini on 25/03/2017.
 */
@Setter
@Getter
@Entity
public class OrderOption extends BaseDomain {
    @Column
    private Long clientId;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public Order order;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public Deal deal;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    public DealOption dealOption;
    @Column
    private String title;
    @Column
    @Enumerated(EnumType.STRING)
    private DealType type;
    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Column
    @Enumerated(EnumType.STRING)
    private CancelStatus cancelStatus;
    @Column
    private int price;

    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "option")
    public List<OrderItem> items = Lists.newArrayList();

    public void addItem(OrderItem item) {
        item.setOption(this);
        this.items.add(item);
    }
    public void addItems(List<OrderItem> items) {
        items.forEach(e -> addItem(e));
    }

    public void updateStatus() {
        if(type == DealType.USABLE) status = items.stream().filter(e -> e.getStatus() == OrderStatus.USED).count() > 0 ? OrderStatus.USED : OrderStatus.ORDERED;
    }
}
