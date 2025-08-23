package com.example.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentQuizAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    @JsonIgnore
    private StudentQuizSubmission submission;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuizQuestion question;

    private String selectedAnswer;   // A/B/C/D
    private boolean correct;
}

