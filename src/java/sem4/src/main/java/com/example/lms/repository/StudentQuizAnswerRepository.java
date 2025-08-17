package com.example.lms.repository;

import com.example.lms.entity.StudentQuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentQuizAnswerRepository extends JpaRepository<StudentQuizAnswer, Long> {
}
