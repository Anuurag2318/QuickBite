package com.quickbite.ai;

import com.quickbite.dto.FoodRecommendationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;
    @GetMapping("/recommend")
    public String recommend(@RequestParam String prompt){
        return aiService.recommendFood(prompt);
    }
    @GetMapping("/recommend/{userId}")
    public List<FoodRecommendationResponse> recommend(@PathVariable Long userId){
        return aiService.recommendFoodById(userId);
    }
}
