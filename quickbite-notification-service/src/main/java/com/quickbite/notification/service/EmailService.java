package com.quickbite.notification.service;
import com.quickbite.notification.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendOrderConfirmation(OrderPlacedEvent event) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(event.getEmail());
        message.setSubject("QuickBite Order Confirmation");

        message.setText(
                "Hello " + event.getName() + ",\n\n" +
                        "Your order has been placed successfully.\n\n" +
                        "Order ID : " + event.getOrderId() + "\n" +
                        "Amount   : ₹" + event.getTotalAmount() + "\n" +
                        "Status   : " + event.getStatus() + "\n\n" +
                        "Thank you for choosing QuickBite!"
        );

        mailSender.send(message);
    }
}
