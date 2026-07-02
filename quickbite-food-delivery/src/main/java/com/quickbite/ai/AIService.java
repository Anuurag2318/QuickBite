package com.quickbite.ai;

import com.quickbite.dto.FoodRecommendationResponse;

import java.util.List;

public interface AIService {


    public String recommendFood(String userPrompt);

    public List<FoodRecommendationResponse> recommendFoodById(Long userId);
}
