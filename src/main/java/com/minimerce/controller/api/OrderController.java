package com.minimerce.controller.api;

import com.minimerce.controller.BaseController;
import com.minimerce.core.api.domain.client.Client;
import com.minimerce.core.api.domain.order.Order;
import com.minimerce.core.api.service.order.OrderService;
import com.minimerce.core.api.support.object.order.FindOrderRequest;
import com.minimerce.core.api.support.object.order.OrderRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by gemini on 28/04/2017.
 */
@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController extends BaseController {
    private final OrderService orderService;

    @Inject
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order order(@AuthenticationPrincipal Client client, OrderRequest request) {
        return orderService.order(client.getId(), request);
    }
    @GetMapping
    public Order findOrder(@AuthenticationPrincipal Client client, @RequestParam(required = false) Long orderId, @RequestParam(required = false) Long clientOrderId)  {
        return orderService.findOrder(client.getId(), new FindOrderRequest(orderId, clientOrderId));
    }
}
