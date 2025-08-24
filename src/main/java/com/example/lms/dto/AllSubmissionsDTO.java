package com.example.lms.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllSubmissionsDTO {
    private Long submissionId;
    private Long quizId;
    private String quizTitle;
    private Long studentId;
    private String studentUsername;
    private int score;
    private int totalQuestions;
    private LocalDateTime submittedAt;
}
