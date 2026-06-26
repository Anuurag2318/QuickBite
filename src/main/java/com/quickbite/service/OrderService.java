package com.quickbite.service;

import com.quickbite.dto.OrderRequest;
import com.quickbite.dto.OrderResponse;
import com.quickbite.dto.OrderTrackingResponse;
import com.quickbite.dto.UpdateOrderStatusRequest;
import org.springframework.data.domain.Page;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest request);
    Page<OrderResponse> getOrdersByUserId(Long userId,int page,int limit);
    OrderResponse updateOrderStatus(Long id, UpdateOrderStatusRequest request);
    OrderResponse cancelOrder(Long orderId);
    OrderTrackingResponse trackOrder(Long orderId);
}
