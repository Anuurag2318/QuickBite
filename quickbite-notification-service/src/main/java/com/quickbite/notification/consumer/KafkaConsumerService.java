package com.quickbite.notification.consumer;

import com.quickbite.notification.event.OrderPlacedEvent;
import com.quickbite.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final EmailService emailService;
    @KafkaListener(topics = "order-created",groupId = "notification-group")
    public void consume(OrderPlacedEvent event){
        System.out.println("Time: "+ LocalDateTime.now());
        System.out.println("================================");

        System.out.println("Notification Service");

        System.out.println("Order Id : " + event.getOrderId());

        System.out.println("User Id : " + event.getUserId());

        System.out.println("Total : " + event.getTotalAmount());

        System.out.println("Status : " + event.getStatus());

        System.out.println("Order confirmation notification sent.");

        System.out.println("================================");
        emailService.sendOrderConfirmation(event);
    }
}
