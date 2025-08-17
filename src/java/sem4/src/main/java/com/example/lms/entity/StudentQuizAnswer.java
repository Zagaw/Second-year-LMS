package com.example.lms.entity;


import jakarta.persistence.*;

@Entity
public class StudentQuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private StudentQuizSubmission studentQuizSubmission;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuizQuestion quizQuestion;

    private String answerText;
    private Boolean isCorrect;

    // getters and setters
}