package com.quickbite.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AIRecommendation {
    private String food;
    private String reason;
}
