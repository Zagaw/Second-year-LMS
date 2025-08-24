package com.example.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubmissionDTO {
    private Long submissionId;
    private String quizTitle;
    private String materialTitle;
    private String courseName;
    private int score;
    private int totalQuestions;
    private LocalDateTime submittedAt;
}
