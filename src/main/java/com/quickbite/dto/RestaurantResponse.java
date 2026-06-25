package com.quickbite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RestaurantResponse {
    private Long id;
    private String name;
    private String address;
    private String cuisineType;
    private Double rating;
}
