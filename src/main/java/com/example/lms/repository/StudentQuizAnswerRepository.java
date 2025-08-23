package com.example.lms.repository;

import com.example.lms.entity.StudentQuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentQuizAnswerRepository extends JpaRepository<StudentQuizAnswer, Long> {
    boolean existsByQuestion_QuestionId(Long questionId);
}
