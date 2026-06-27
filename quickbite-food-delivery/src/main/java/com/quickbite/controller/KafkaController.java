package com.quickbite.controller;

import com.quickbite.kafka.event.OrderPlacedEvent;
import com.quickbite.kafka.producer.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaProducerService producerService;
    @PostMapping("/publish")
    public String publish(@RequestBody OrderPlacedEvent event){
        producerService.sendMessage(event);
        return "Event published Successfully";
    }
}
