package com.example.lms.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuizSubmissionRequest {
    private Long studentId;
    private List<AnswerDTO> answers;

    @Data
    public static class AnswerDTO {
        private Long questionId;
        private String selectedAnswer;  // A, B, C, or D
    }
}
