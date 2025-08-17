package com.example.lms.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class StudentQuizSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer submissionId;

    private Integer studentId; // You may want to make this a relation to a Student entity

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private BigDecimal score;
    private LocalDateTime submittedAt;

    @OneToMany(mappedBy = "studentQuizSubmission", cascade = CascadeType.ALL)
    private List<StudentQuizAnswer> answers;

    @OneToOne(mappedBy = "studentQuizSubmission", cascade = CascadeType.ALL)
    private QuizResult quizResult;

    // getters and setters
}