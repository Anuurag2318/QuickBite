package com.quickbite.ai;

import com.quickbite.dto.AIRecommendation;
import com.quickbite.dto.FoodItemResponse;
import com.quickbite.dto.FoodRecommendationResponse;
import com.quickbite.entity.Order;
import com.quickbite.entity.OrderItem;
import com.quickbite.repository.OrderRepository;
import com.quickbite.service.FoodItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AIServiceImpl implements AIService {
    private final ChatClient chatClient;
    private final FoodItemService foodItemService;
    private final OrderRepository orderRepository;
    @Override
    public String recommendFood(String userPrompt) {
        List<FoodItemResponse> foodItems = foodItemService.getAllFoodItems();
        StringBuilder menu = new StringBuilder();
        for (FoodItemResponse item : foodItems) {
            menu.append("- ")
                    .append(item.getName())
                    .append(" | ")
                    .append(item.getDescription())
                    .append(" | ₹")
                    .append(item.getPrice())
                    .append("\n");
        }
        String prompt = """
            You are an intelligent food recommendation assistant for QuickBite.
            
            Below is the complete restaurant menu.
            
            %s
            
            Based ONLY on this menu, answer the user's request.
            
            User Request:
            %s
            
            Rules:
            1. Recommend only items from the menu.
            2. Recommend at most 3 items.
            3. Explain why each recommendation is suitable.
            4. Keep the response concise.
            """.formatted(menu.toString(), userPrompt);



        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    @Override
    public List<FoodRecommendationResponse> recommendFoodById(Long userId) {
        List<Order> orders=orderRepository.findByUser_Id(userId);
        List<FoodItemResponse> foodItems =foodItemService.getAllFoodItems();
        StringBuilder menu = new StringBuilder();
        for (FoodItemResponse item : foodItems) {
            menu.append("- ")
                    .append(item.getName())
                    .append(" | ")
                    .append(item.getDescription())
                    .append(" | ₹")
                    .append(item.getPrice())
                    .append("\n");
        }
        String prompt="";
        if (orders.isEmpty()) {
            prompt = """
                You are an AI Food Recommendation Assistant.
                Current Menu:
                %s
                Recommend exactly 3 popular food items.
                Return ONLY valid JSON.
                Example:
                [
                  {
                    "food":"Veg Paneer Pizza",
                    "reason":"Popular among customers."
                  }
                ]
                """.formatted(menu);
        } else {
            StringBuilder previousOrders = new StringBuilder();
            for (Order order : orders) {
                for (OrderItem item : order.getOrderItems()) {
                    previousOrders
                            .append("- ")
                            .append(item.getFoodItem().getName())
                            .append("\n");
                }
            }
            prompt = """
                You are an AI Food Recommendation Assistant.
                User Previous Orders:
                %s
                Current Menu:
                %s
                Recommend exactly 3 food items.
                Return ONLY valid JSON.
                Example:
                [
                  {
                    "food":"Veg Paneer Pizza",
                    "reason":"User frequently orders pizza."
                  }
                ]
                """.formatted(previousOrders, menu);
        }
        List<AIRecommendation> recommendations=chatClient.prompt()
                .user(prompt)
                .call()
                .entity(new ParameterizedTypeReference<List<AIRecommendation>>() {});
        List<FoodRecommendationResponse> result=new ArrayList<>();
        for(AIRecommendation ai:recommendations){
            FoodItemResponse food=foodItems.stream()
                    .filter(f->f.getName().equalsIgnoreCase(ai.getFood()))
                    .findFirst()
                    .orElse(null);
            if(food!=null){
                result.add(FoodRecommendationResponse.builder()
                        .id(food.getId())
                        .name(food.getName())
                        .description(food.getDescription())
                        .price(food.getPrice())
                        .restaurantName(food.getRestaurantName())
                        .reason(ai.getReason()).build());
            }
        }
        return result;
    }
}
