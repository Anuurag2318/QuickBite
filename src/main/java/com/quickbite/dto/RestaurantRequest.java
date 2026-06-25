package com.quickbite.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RestaurantRequest {
    @NotBlank(message = "Restaurant name is required")
    private String name;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Cuisine Type is required")
    private String cuisineType;
    @NotNull(message="Rating is required")
    @DecimalMin(value="0.0",message="Rating cannot be less than 0")
    @DecimalMax(value="5.0",message="Rating cannot be greater than 5")
    private Double rating;
}
