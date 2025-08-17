package com.example.lms.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultId;

    @OneToOne
    @JoinColumn(name = "submission_id")
    private StudentQuizSubmission studentQuizSubmission;

    private Integer totalQuestions;
    private Integer correctAnswers;
    private BigDecimal scorePercentage;
    private String feedback;

    // getters and setters
}