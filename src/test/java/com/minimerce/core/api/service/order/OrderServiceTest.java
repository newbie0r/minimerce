package com.minimerce.core.api.service.order;

import com.minimerce.builder.FindOrderRequestBuilder;
import com.minimerce.builder.OrderRequestBuilder;
import com.minimerce.core.api.component.order.OrderFinder;
import com.minimerce.core.api.component.order.OrderMaker;
import com.minimerce.core.api.support.exception.NotExistOrderException;
import com.minimerce.core.api.support.exception.UnsaleableProductException;
import com.minimerce.core.api.support.exception.UnsupportedItemTypeException;
import com.minimerce.core.api.support.object.order.FindOrderRequest;
import com.minimerce.core.api.support.object.order.OrderRequest;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by gemini on 20/04/2017.
 */
public class OrderServiceTest {
    private OrderService orderService;
    private OrderMaker mockOrderMaker;
    private OrderFinder mockOrderFinder;

    @Before
    public void setup() {
        mockOrderMaker = mock(OrderMaker.class);
        mockOrderFinder = mock(OrderFinder.class);
        orderService = new OrderService(mockOrderMaker, mockOrderFinder);
    }

    @Test
    public void testShouldBeCallOrder() throws UnsaleableProductException, UnsupportedItemTypeException {
        OrderRequest request = OrderRequestBuilder.anOrderRequest().build();
        orderService.order(1L, request);
        verify(mockOrderMaker, times(1)).make(1L, request);
    }

    @Test
    public void testShouldBeCallFindOrders() throws UnsupportedItemTypeException, UnsaleableProductException, NotExistOrderException {
        FindOrderRequest request = FindOrderRequestBuilder.aFindOrderRequest().build();
        orderService.findOrder(1L, request);
        verify(mockOrderFinder, times(1)).findOrder(1L, request);
    }

    @Test
    public void testShouldBeCallFindOrder() throws UnsupportedItemTypeException, UnsaleableProductException, NotExistOrderException {
        orderService.findOrders(1L, 5L);
        verify(mockOrderFinder, times(1)).findOrders(1L, 5L);
    }
}