package com.quickbite.notification.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedEvent {
    private Long orderId;
    private Long userId;
    private Double totalAmount;
    private String status;
}

