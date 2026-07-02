package com.quickbite.controller;

import com.quickbite.dto.RestaurantRequest;
import com.quickbite.dto.RestaurantResponse;
import com.quickbite.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public RestaurantResponse createRestaurant(@Valid  @RequestBody RestaurantRequest request){
        return restaurantService.createRestaurant(request);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public List<RestaurantResponse> getAllRestaurant(){
        return restaurantService.getAllRestaurant();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RestaurantResponse getRestauranById(@PathVariable Long id){
        return restaurantService.getRestauranById(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteRestaurant(@PathVariable  Long id){
        restaurantService.deleteRestaurant(id);
        return "Restaurant deleted successfully";
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RestaurantResponse updateRestaurant(@PathVariable  Long id,@Valid @RequestBody  RestaurantRequest request) {
        return restaurantService.updateRestaurant(id,request);
    }
}
