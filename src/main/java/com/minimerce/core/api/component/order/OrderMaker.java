package com.minimerce.core.api.component.order;

import com.minimerce.core.api.domain.order.Order;
import com.minimerce.core.api.domain.order.option.OrderOption;
import com.minimerce.core.api.support.exception.MinimerceException;
import com.minimerce.core.api.support.object.order.OrderRequest;
import com.minimerce.core.api.support.object.response.ErrorCode;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by gemini on 01/04/2017.
 */
@Component
public class OrderMaker {
    private final OrderOptionMaker orderDetailMaker;

    @Inject
    public OrderMaker(OrderOptionMaker orderDetailMaker) {
        this.orderDetailMaker = orderDetailMaker;
    }

    public Order make(Long clientId, OrderRequest request) {
        List<OrderOption> options = orderDetailMaker.make(clientId, request.getDetails());
        if(request.getPrice() != getPrice(options)) throw new MinimerceException(ErrorCode.NOT_EQUAL_ORDER_PRICE);

        Order order = new Order();
        order.setClientId(clientId);
        order.setClientOrderId(request.getClientOrderId());
        order.setCustomerId(request.getCustomerId());
        order.setOrderedAt(request.getOrderedAt());
        order.setPrice(request.getPrice());
        order.setDealIds(buildDealIds(options));
        order.setTitle(buildTitle(options));
        order.addOptions(options);
        return order;
    }

    private int getPrice(List<OrderOption> details) {
        return details.stream().mapToInt(e -> e.getPrice()).sum();
    }

    private String buildTitle(List<OrderOption> details) {
        return null;
    }

    private String buildDealIds(List<OrderOption> details) {
        return null;
    }
}
