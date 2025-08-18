// dto/CreateQuestionRequest.java
package com.example.lms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateQuestionRequest {
    @NotBlank private String questionText;
    @NotBlank private String optionA;
    @NotBlank private String optionB;
    @NotBlank private String optionC;
    @NotBlank private String optionD;
    /** "A", "B", "C", or "D" */
    @NotBlank private String correctAnswer;
}

