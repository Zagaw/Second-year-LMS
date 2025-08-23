package com.example.lms.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizSubmissionResponse {
    private Long submissionId;
    private int score;
    private int totalQuestions;
}

