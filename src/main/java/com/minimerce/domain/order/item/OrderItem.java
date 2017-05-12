package com.minimerce.domain.order.item;

import com.minimerce.domain.BaseDomain;
import com.minimerce.domain.order.option.OrderOption;
import com.minimerce.object.order.CancelStatus;
import com.minimerce.object.order.OrderStatus;
import com.minimerce.object.type.DealType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by gemini on 25/03/2017.
 */
@Setter
@Getter
@Entity
public class OrderItem extends BaseDomain {
    @Column
    private Long clientId;
    @ManyToOne(fetch = FetchType.LAZY)
    public OrderOption option;
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
}
