package com.example.lms.repository;

import com.example.lms.entity.StudentQuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentQuizSubmissionRepository extends JpaRepository<StudentQuizSubmission, Long> {
    List<StudentQuizSubmission> findByStudent_Id(Long studentId);
    List<StudentQuizSubmission> findByQuiz_Id(Long quizId);
}
