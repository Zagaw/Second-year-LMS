package com.example.lms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateQuizRequest {
    @NotBlank
    private String title;
}
